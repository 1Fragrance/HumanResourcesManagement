package hrm.servlets;

import hrm.entities.Employee;
import hrm.repositories.EmployeeRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private EmployeeRepository employeeRepository;

    public void init() {
        employeeRepository = new EmployeeRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Employee employee = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Логин и пароль обязательны к заполнению";
        } else {

            try {
                employee = employeeRepository.GetEmployeeByCredentials(userName, password);

                if (employee == null) {
                    hasError = true;
                    errorString = "Неправильный логин или пароль";
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {
            employee = new Employee();
            employee.setUserName(userName);
            employee.setPassword(password);

            request.setAttribute("errorString", errorString);
            request.setAttribute("employee", employee);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            session.setAttribute("user", employee);
            session.setMaxInactiveInterval(30*60);

            response.sendRedirect("index");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
}
