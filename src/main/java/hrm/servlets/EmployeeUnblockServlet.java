package hrm.servlets;

import hrm.entities.Employee;
import hrm.infrastructure.EmployeeStatuses;
import hrm.repositories.EmployeeRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employeeUnblock")
public class EmployeeUnblockServlet extends HttpServlet {
    private EmployeeRepository employeeRepository;

    public void init() {
        employeeRepository = new EmployeeRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Employee employee = employeeRepository.GetEmployeeById(id);

            if(employee.getAdmin()) {
                response.sendRedirect("employee");
            }

            employee.setStatus(EmployeeStatuses.Active);
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
