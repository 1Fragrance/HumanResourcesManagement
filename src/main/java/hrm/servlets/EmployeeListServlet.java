package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Position;
import hrm.helpers.DateHelper;
import hrm.infrastructure.EmployeeStatuses;
import hrm.entities.Employee;
import hrm.models.DepartmentViewModel;
import hrm.models.EmployeeViewModel;
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

@WebServlet("/employee")
public class EmployeeListServlet extends HttpServlet {

    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private DepartmentRepository departmentRepository;

    public void init() {
        employeeRepository = new EmployeeRepository();
        positionRepository = new PositionRepository();
        departmentRepository = new DepartmentRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Employee> employeeList = employeeRepository.GetEmployees();

            List<EmployeeViewModel> resultList = new ArrayList<>();
            for (Employee employee : employeeList) {

                EmployeeViewModel viewModel = EmployeeMapper.MapToModel(employee);
                Department department = departmentRepository.GetDepartmentById(employee.getDepartmentId());
                if (department != null) {
                    viewModel.setDepartment(DepartmentMapper.MapToModel(department));
                }
                Position position = positionRepository.GetPositionById(employee.getPositionId());
                if (position != null) {
                    viewModel.setPosition(PositionMapper.MapToModel(position));
                }

                resultList.add(viewModel);
            }


            request.setAttribute("employeeList", resultList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("employee-list.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

