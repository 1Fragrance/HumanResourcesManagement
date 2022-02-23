package hrm.servlets;

import hrm.entities.Position;
import hrm.repositories.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/positionEdit")
public class PositionEditServlet extends HttpServlet {

    private PositionRepository positionRepository;

    public void init() {
        positionRepository = new PositionRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Position position = parseForm(request);
        int id = Integer.parseInt(request.getParameter("id"));
        position.setId(id);

        try {
            positionRepository.UpdatePosition(position);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("position");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Position existingPosition = positionRepository.GetPositionById(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("position-form.jsp");
            request.setAttribute("position", existingPosition);

            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }


    private Position parseForm(HttpServletRequest request) {
        Position position = new Position();
        position.setTitle(request.getParameter("title"));
        position.setMaxSalary(Float.parseFloat(request.getParameter("maxSalary")));
        position.setMinSalary(Float.parseFloat(request.getParameter("minSalary")));

        return position;
    }
}