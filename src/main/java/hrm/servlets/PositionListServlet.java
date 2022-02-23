package hrm.servlets;

import hrm.entities.Position;
import hrm.models.PositionViewModel;
import hrm.models.mappers.PositionMapper;
import hrm.repositories.DepartmentRepository;
import hrm.repositories.EmployeeRepository;
import hrm.repositories.PositionRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/position")
public class PositionListServlet extends HttpServlet {

    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private DepartmentRepository departmentRepository;

    public void init() {
        employeeRepository = new EmployeeRepository();
        positionRepository = new PositionRepository();
        departmentRepository = new DepartmentRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Position> list = positionRepository.GetPositions();

            List<PositionViewModel> resultList = new ArrayList<>();
            for (Position position : list) {
                PositionViewModel viewModel = PositionMapper.MapToModel(position);
                resultList.add(viewModel);
            }

            request.setAttribute("positionList", resultList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("position-list.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}