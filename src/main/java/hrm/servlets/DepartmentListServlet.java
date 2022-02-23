package hrm.servlets;

import hrm.entities.Department;
import hrm.entities.Office;
import hrm.entities.Position;
import hrm.models.DepartmentViewModel;
import hrm.models.mappers.DepartmentMapper;
import hrm.models.mappers.OfficeMapper;
import hrm.models.mappers.PositionMapper;
import hrm.repositories.DepartmentRepository;
import hrm.repositories.OfficeRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/department")
public class DepartmentListServlet extends HttpServlet {

    private DepartmentRepository departmentRepository;
    private OfficeRepository officeRepository;

    public void init() {
        departmentRepository = new DepartmentRepository();
        officeRepository = new OfficeRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Department> list = departmentRepository.GetDepartments();

            List<DepartmentViewModel> resultList = new ArrayList<>();
            for (Department department : list) {
                DepartmentViewModel viewModel = DepartmentMapper.MapToModel(department);

                Office office = officeRepository.GetOfficeById(department.getOfficeId());
                if (office != null) {
                    viewModel.setOffice(OfficeMapper.MapToModel(office));
                }

                resultList.add(viewModel);
            }

            request.setAttribute("departmentList", resultList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("department-list.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
