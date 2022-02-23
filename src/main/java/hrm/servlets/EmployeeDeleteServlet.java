package hrm.servlets;

import hrm.helpers.AuthHelper;
import hrm.repositories.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/deleteEmployee")
public class EmployeeDeleteServlet extends HttpServlet {
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
            employeeRepository.DeleteEmployee(id);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("employee");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}