package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Office;
import hrm.helpers.AuthHelper;
import hrm.models.OfficeViewModel;
import hrm.models.mappers.OfficeMapper;
import hrm.models.validators.OfficeValidator;
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

@WebServlet("/officeEdit")
public class OfficeEditServlet extends HttpServlet {

    private OfficeRepository officeRepository;
    private OfficeValidator officeValidator;
    public void init() {
        officeRepository = new OfficeRepository();
        officeValidator = new OfficeValidator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!AuthHelper.ValidateAdminPermission(request)) {
            response.sendRedirect("/");
        }

        OfficeViewModel office = parseForm(request);
        int id = Integer.parseInt(request.getParameter("id"));
        office.setId(id);

        ValidationResult validationResult = officeValidator.Validate(office);
        if(!validationResult.isSuccess()) {
            request.setAttribute("errorString", validationResult.getError());
            request.setAttribute("office", office);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/office-form.jsp");
            dispatcher.forward(request, response);

            return;
        }

        try {
            Office entity = OfficeMapper.MapToEntity(office);
            officeRepository.UpdateOffice(entity);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("office");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Office existingOffice = officeRepository.GetOfficeById(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("office-form.jsp");
            request.setAttribute("office", existingOffice);

            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }


    private OfficeViewModel parseForm(HttpServletRequest request) {
        OfficeViewModel office = new OfficeViewModel();

        office.setCity(request.getParameter("city"));
        office.setCountry(request.getParameter("country"));
        office.setInternalName(request.getParameter("internalName"));
        office.setStreetAddress(request.getParameter("streetAddress"));
        office.setPostalCode(request.getParameter("postalCode"));

        return office;
    }
}