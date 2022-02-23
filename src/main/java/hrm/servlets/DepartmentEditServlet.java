package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Office;
import hrm.entities.Position;
import hrm.models.LookupViewModel;
import hrm.repositories.DepartmentRepository;
import hrm.repositories.OfficeRepository;
import hrm.repositories.PositionRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/departmentEdit")
public class DepartmentEditServlet extends HttpServlet {

    private DepartmentRepository departmentRepository;
    private OfficeRepository officeRepository;
    public void init() {
        departmentRepository = new DepartmentRepository();
        officeRepository = new OfficeRepository();
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
            try {
                populateDropDowns(request);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            int id = Integer.parseInt(request.getParameter("id"));
            Department existingDepartment = departmentRepository.GetDepartmentById(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("department-form.jsp");
            request.setAttribute("department", existingDepartment);

            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    private void populateDropDowns(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<LookupViewModel> offices = new ArrayList<>();
        List<Office> officeEntities = officeRepository.GetOffices();
        for(Office office : officeEntities) {
            offices.add(new LookupViewModel(office.getInternalName(), Integer.toString(office.getId())));
        }
        request.setAttribute("offices", offices);
    }

    private Department parseForm(HttpServletRequest request) {
        Department department = new Department();
        department.setName(request.getParameter("name"));

        return department;
    }
}