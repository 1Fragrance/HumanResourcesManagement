package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Office;
import hrm.entities.Position;
import hrm.helpers.AuthHelper;
import hrm.infrastructure.Constants;
import hrm.infrastructure.EmployeeStatuses;
import hrm.models.DepartmentViewModel;
import hrm.models.LookupViewModel;
import hrm.models.mappers.DepartmentMapper;
import hrm.models.validators.DepartmentValidator;
import hrm.models.validators.ValidationResult;
import hrm.repositories.DepartmentRepository;
import hrm.repositories.OfficeRepository;

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

@WebServlet("/departmentCreate")
public class DepartmentCreateServlet extends HttpServlet {
    private DepartmentRepository departmentRepository;
    private OfficeRepository officeRepository;
    private DepartmentValidator departmentValidator;
    public void init() {
        departmentRepository = new DepartmentRepository();
        officeRepository = new OfficeRepository();
        departmentValidator = new DepartmentValidator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!AuthHelper.ValidateAdminPermission(request)) {
            response.sendRedirect("/");
        }
        DepartmentViewModel department = parseForm(request);

        ValidationResult validationResult = departmentValidator.Validate(department);
        if(!validationResult.isSuccess()) {
            try {
                populateDropDowns(request);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("errorString", validationResult.getError());
            request.setAttribute("department", department);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/department-form.jsp");
            dispatcher.forward(request, response);

            return;
        }

        try {
            Department departmentEntity = DepartmentMapper.MapToEntity(department);

            departmentRepository.InsertDepartment(departmentEntity);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("department");
    }

    private void populateDropDowns(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<LookupViewModel> offices = new ArrayList<>();
        List<Office> officeEntities = officeRepository.GetOffices();
        for(Office office : officeEntities) {
            offices.add(new LookupViewModel(office.getInternalName(), Integer.toString(office.getId())));
        }
        request.setAttribute("offices", offices);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            populateDropDowns(request);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("department-form.jsp");
        dispatcher.forward(request, response);
    }

    private DepartmentViewModel parseForm(HttpServletRequest request) {
        DepartmentViewModel department = new DepartmentViewModel();

        department.setName(request.getParameter("name"));

        if(request.getParameter("officeId") != null && !request.getParameter("officeId").equals(""))
            department.setOfficeId(Integer.parseInt(request.getParameter("officeId")));


        return department;
    }
}