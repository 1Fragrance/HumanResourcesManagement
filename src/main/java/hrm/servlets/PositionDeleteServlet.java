package hrm.servlets;

import hrm.helpers.AuthHelper;
import hrm.repositories.PositionRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deletePosition")
public class PositionDeleteServlet extends HttpServlet {
    private PositionRepository repository;

    public void init() {
        repository = new PositionRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!AuthHelper.ValidateAdminPermission(request)) {
            response.sendRedirect("/");
        }

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            repository.DeletePosition(id);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("position");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}