package hrm.pipeline;

import hrm.helpers.CookieHelper;
import hrm.entities.Employee;
import hrm.repositories.EmployeeRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

    EmployeeRepository employeeRepository;
    public CookieFilter() {
        employeeRepository = new EmployeeRepository();
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        Employee userInSession = CookieHelper.getCurrentUser(session);
        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }

        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null) {
            String userName = CookieHelper.getUserNameInCookie(req);
            try {
                Employee user = employeeRepository.GetEmployeeByCredentials(userName);
                CookieHelper.storeCurrentUser(session, user);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }


            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }

        chain.doFilter(request, response);
    }

}
