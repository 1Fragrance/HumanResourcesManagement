package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Position;
import hrm.repositories.DepartmentRepository;
import hrm.repositories.PositionRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/departmentEdit")
public class DepartmentEditServlet extends HttpServlet {

    private DepartmentRepository departmentRepository;

    public void init() {
        departmentRepository = new DepartmentRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department = parseForm(request);
        int id = Integer.parseInt(request.getParameter("id"));
        department.setId(id);

        try {
            departmentRepository.UpdateDepartment(department);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("department");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Department existingDepartment = departmentRepository.GetDepartmentById(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("department-form.jsp");
            request.setAttribute("department", existingDepartment);

            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }


    private Department parseForm(HttpServletRequest request) {
        Department department = new Department();
        department.setName(request.getParameter("name"));

        return department;
    }
}