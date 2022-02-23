package hrm.helpers;

import hrm.infrastructure.Constants;
import hrm.entities.Employee;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CookieHelper {
    public static void storeCurrentUser(HttpSession session, Employee loginedUser) {
        session.setAttribute("currentUser", loginedUser);
    }
    public static Employee getCurrentUser(HttpSession session) {
        return (Employee) session.getAttribute("currentUser");
    }

    public static void storeUserCookie(HttpServletResponse response, Employee user) {
        Cookie cookieUserName = new Cookie(Constants.CookieConstants.ATT_NAME_USER_NAME, user.getUserName());
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constants.CookieConstants.ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(Constants.CookieConstants.ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
