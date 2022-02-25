package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Office;
import hrm.helpers.AuthHelper;
import hrm.models.OfficeViewModel;
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


@WebServlet("/officeCreate")
public class OfficeCreateServlet extends HttpServlet {
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
            officeRepository.InsertOffice(office);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("office");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("office-form.jsp");
        dispatcher.forward(request, response);
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