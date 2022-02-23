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

@WebServlet("/employeeCreate")
public class EmployeeCreateServlet extends HttpServlet {

    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private DepartmentRepository departmentRepository;
    private OfficeRepository officeRepository;
    private PositionHistoryRepository positionHistoryRepository;

    public void init() {
        employeeRepository = new EmployeeRepository();
        positionRepository = new PositionRepository();
        departmentRepository = new DepartmentRepository();
        officeRepository = new OfficeRepository();
        positionHistoryRepository = new PositionHistoryRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!AuthHelper.ValidateAdminPermission(request)) {
            response.sendRedirect("/");
        }

        Employee employee = parseForm(request);
        try {
            employeeRepository.InsertEmployee(employee);

            Date currentDate = DateHelper.getUTCdatetimeAsDate();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            positionHistoryRepository.InsertPositionHistory(new PositionHistory(sqlDate, null, employee.getId(), employee.getPositionId(), employee.getDepartmentId()));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        response.sendRedirect("employee");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            populateDropDowns(request);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
        dispatcher.forward(request, response);
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

    private Employee parseForm(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setPatronymic(request.getParameter("patronymic"));
        employee.setPhoneNumber(request.getParameter("phoneNumber"));
        Date currentDate = DateHelper.getUTCdatetimeAsDate();
        employee.setHireDate(new java.sql.Date(currentDate.getTime()));
        employee.setSalary(Float.parseFloat(request.getParameter("salary")));
        employee.setEmail(request.getParameter("email"));
        employee.setUserName(request.getParameter("userName"));
        employee.setPassword(request.getParameter("password"));
        employee.setAdmin(false);
        employee.setStatus(EmployeeStatuses.Active);
        employee.setPositionId(Integer.parseInt(request.getParameter("positionId")));
        employee.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

        return employee;
    }
}

