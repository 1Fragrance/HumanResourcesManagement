package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Employee;
import hrm.entities.Position;
import hrm.entities.PositionHistory;
import hrm.models.EmployeeViewModel;
import hrm.models.PositionHistoryViewModel;
import hrm.models.mappers.DepartmentMapper;
import hrm.models.mappers.EmployeeMapper;
import hrm.models.mappers.PositionHistoryMapper;
import hrm.models.mappers.PositionMapper;
import hrm.repositories.DepartmentRepository;
import hrm.repositories.EmployeeRepository;
import hrm.repositories.PositionHistoryRepository;
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

@WebServlet("/positionHistory")
public class PositionHistoryListServlet  extends HttpServlet {

    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private DepartmentRepository departmentRepository;
    private PositionHistoryRepository positionHistoryRepository;

    public void init() {
        employeeRepository = new EmployeeRepository();
        positionRepository = new PositionRepository();
        departmentRepository = new DepartmentRepository();
        positionHistoryRepository = new PositionHistoryRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<PositionHistory> list = positionHistoryRepository.GetPositionHistories();

            List<PositionHistoryViewModel> resultList = new ArrayList<>();
            for (PositionHistory history : list) {

                PositionHistoryViewModel viewModel = PositionHistoryMapper.MapToModel(history);

                Department department = departmentRepository.GetDepartmentById(history.getDepartmentId());
                if (department != null) {
                    viewModel.setDepartment(DepartmentMapper.MapToModel(department));
                }
                Position position = positionRepository.GetPositionById(history.getPositionId());
                if (position != null) {
                    viewModel.setPosition(PositionMapper.MapToModel(position));
                }

                Employee employee = employeeRepository.GetEmployeeById(history.getEmployeeId());
                if (position != null) {
                    viewModel.setEmployee(EmployeeMapper.MapToModel(employee));
                }

                resultList.add(viewModel);
            }

            request.setAttribute("historyList", resultList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("history-list.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }


}
