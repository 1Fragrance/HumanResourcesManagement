package hrm.servlets;

import hrm.entities.Office;
import hrm.models.OfficeViewModel;
import hrm.models.mappers.OfficeMapper;
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

@WebServlet("/office")
public class OfficeListServlet extends HttpServlet {

    private OfficeRepository officeRepository;

    public void init() {
        officeRepository = new OfficeRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Office> list = officeRepository.GetOffices();

            List<OfficeViewModel> resultList = new ArrayList<>();
            for (Office office : list) {
                OfficeViewModel viewModel = OfficeMapper.MapToModel(office);
                resultList.add(viewModel);
            }

            request.setAttribute("officeList", resultList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("office-list.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
