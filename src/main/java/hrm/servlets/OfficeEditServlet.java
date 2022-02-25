package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Office;
import hrm.helpers.AuthHelper;
import hrm.models.validators.OfficeValidator;
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

        Office office = parseForm(request);
        int id = Integer.parseInt(request.getParameter("id"));
        office.setId(id);

        ValidationResult validationResult = officeValidator.Validate(office);
        if(!validationResult.isSuccess()) {
            try {
                populateDropDowns(request);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("errorString", validationResult.getError());
            request.setAttribute("office", office);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/office-form.jsp");
            dispatcher.forward(request, response);

            return;
        }

        try {
            officeRepository.UpdateOffice(office);
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


    private Office parseForm(HttpServletRequest request) {
        Office office = new Office();

        office.setCity(request.getParameter("city"));
        office.setCountry(request.getParameter("country"));
        office.setInternalName(request.getParameter("internalName"));
        office.setStreetAddress(request.getParameter("streetAddress"));
        office.setPostalCode(request.getParameter("postalCode"));

        return office;
    }
}