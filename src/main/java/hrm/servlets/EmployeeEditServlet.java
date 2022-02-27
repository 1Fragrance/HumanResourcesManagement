package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Employee;
import hrm.entities.Position;
import hrm.entities.PositionHistory;
import hrm.helpers.AuthHelper;
import hrm.helpers.DateHelper;
import hrm.infrastructure.Constants;
import hrm.infrastructure.EmployeeStatuses;
import hrm.models.EmployeeViewModel;
import hrm.models.LookupViewModel;
import hrm.models.mappers.DepartmentMapper;
import hrm.models.mappers.EmployeeMapper;
import hrm.models.mappers.PositionMapper;
import hrm.models.validators.EmployeeValidator;
import hrm.models.validators.ValidationResult;
import hrm.repositories.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/employeeEdit")
public class EmployeeEditServlet extends HttpServlet {

    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private DepartmentRepository departmentRepository;
    private OfficeRepository officeRepository;
    private PositionHistoryRepository positionHistoryRepository;
    private EmployeeValidator employeeValidator;
    public void init() {
        employeeRepository = new EmployeeRepository();
        positionRepository = new PositionRepository();
        departmentRepository = new DepartmentRepository();
        officeRepository = new OfficeRepository();
        positionHistoryRepository = new PositionHistoryRepository();
        employeeValidator = new EmployeeValidator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!AuthHelper.ValidateAdminPermission(request)) {
            response.sendRedirect("/");
        }

        EmployeeViewModel employee = parseForm(request);
        int id = Integer.parseInt(request.getParameter("id"));
        employee.setId(id);

        ValidationResult validationResult = null;
        try {
            validationResult = employeeValidator.Validate(employee);
        } catch (SQLException | ClassNotFoundException throwables) {
            request.setAttribute("errorString", throwables.getMessage());
            request.setAttribute("employee", employee);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/employee-form.jsp");
            dispatcher.forward(request, response);

            return;
        }

        if(!validationResult.isSuccess()) {
            try {
                populateDropDowns(request);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("errorString", validationResult.getError());
            request.setAttribute("employee", employee);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/employee-form.jsp");
            dispatcher.forward(request, response);

            return;
        }


        try {
            Employee dbEmployee = employeeRepository.GetEmployeeById(id);

            boolean needToLog = false;
            if(dbEmployee.getPositionId() != employee.getPositionId() ||
               dbEmployee.getDepartmentId() != employee.getDepartmentId())
            {
                needToLog = true;
            }

            Employee entity = EmployeeMapper.MapToEntity(employee);
            employeeRepository.UpdateEmployee(entity);
            if(needToLog) {
                Date currentDate = DateHelper.getUTCdatetimeAsDate();
                java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

                PositionHistory lastHistory = positionHistoryRepository.GetLastPositionHistory(employee.getId(), dbEmployee.getDepartmentId(), dbEmployee.getPositionId());
                if(lastHistory != null) {
                    lastHistory.setEndDate(sqlDate);
                    positionHistoryRepository.UpdatePositionHistory(lastHistory);
                }

                positionHistoryRepository.InsertPositionHistory(new PositionHistory(sqlDate, null, employee.getId(), employee.getPositionId(), employee.getDepartmentId()));
            }


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("employee");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Employee existingEmployee = employeeRepository.GetEmployeeById(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
            request.setAttribute("employee", existingEmployee);
            populateDropDowns(request);

            dispatcher.forward(request, response);
        }
        catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    private void populateDropDowns(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<LookupViewModel> statuses = new ArrayList<>();
        statuses.add(new LookupViewModel(Constants.ResourceStrings.ActiveStatus, Integer.toString(EmployeeStatuses.Active)));
        statuses.add(new LookupViewModel(Constants.ResourceStrings.BlockedStatus, Integer.toString(EmployeeStatuses.Blocked)));
        request.setAttribute("statuses", statuses);

        List<LookupViewModel> departments = new ArrayList<>();
        List<Department> departmentEntities = departmentRepository.GetDepartments();
        for(Department department : departmentEntities) {
            departments.add(new LookupViewModel(department.getName(), Integer.toString(department.getId())));
        }
        request.setAttribute("departments", departments);

        List<LookupViewModel> positions = new ArrayList<>();
        List<Position> positionEntities = positionRepository.GetPositions();
        for(Position position : positionEntities) {
            positions.add(new LookupViewModel(position.getTitle(), Integer.toString(position.getId())));
        }
        request.setAttribute("positions", positions);
    }

    private EmployeeViewModel parseForm(HttpServletRequest request) {
        EmployeeViewModel employee = new EmployeeViewModel();
        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setPatronymic(request.getParameter("patronymic"));
        employee.setPhoneNumber(request.getParameter("phoneNumber"));
        if(request.getParameter("salary") != null && !request.getParameter("salary").equals(""))
            employee.setSalary(Float.parseFloat(request.getParameter("salary")));
        employee.setEmail(request.getParameter("email"));
        employee.setUserName(request.getParameter("userName"));
        employee.setPassword(request.getParameter("password"));

        employee.setStatus(Integer.parseInt(request.getParameter("status")));
        employee.setPositionId(Integer.parseInt(request.getParameter("positionId")));
        employee.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

        return employee;
    }
}

