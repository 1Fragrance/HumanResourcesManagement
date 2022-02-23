package hrm.helpers;

import hrm.entities.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthHelper {

    public static boolean ValidateAdminPermission(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Employee employee = (Employee) session.getAttribute("user");

        if(employee == null) {
            return false;
        }

        return employee.getAdmin();
    }
}
