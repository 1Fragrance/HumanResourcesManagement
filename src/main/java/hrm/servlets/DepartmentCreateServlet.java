package hrm.servlets;

import hrm.entities.Department;
import hrm.repositories.DepartmentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/departmentCreate")
public class DepartmentCreateServlet extends HttpServlet {
    private DepartmentRepository departmentRepository;

    public void init() {
        departmentRepository = new DepartmentRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department = parseForm(request);
        try {
            departmentRepository.InsertDepartment(department);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("department");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("department-form.jsp");
        dispatcher.forward(request, response);
    }

    private Department parseForm(HttpServletRequest request) {
        Department department = new Department();

        department.setName(request.getParameter("name"));

        return department;
    }
}