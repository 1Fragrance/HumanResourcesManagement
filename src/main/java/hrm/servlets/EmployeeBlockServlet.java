package hrm.servlets;

import hrm.entities.Employee;
import hrm.helpers.AuthHelper;
import hrm.infrastructure.Constants;
import hrm.infrastructure.EmployeeStatuses;
import hrm.repositories.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employeeBlock")
public class EmployeeBlockServlet extends HttpServlet {
    private EmployeeRepository employeeRepository;

    public void init() {
        employeeRepository = new EmployeeRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!AuthHelper.ValidateAdminPermission(request)) {
            response.sendRedirect("/");
        }

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Employee employee = employeeRepository.GetEmployeeById(id);

            if(employee.getAdmin()) {
                response.sendRedirect("employee");
            }

            employee.setStatus(EmployeeStatuses.Blocked);
            employeeRepository.UpdateEmployee(employee);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("employee");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
