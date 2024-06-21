package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import model.LoginForm;
import model.User;
import service.UserService;

public class LoginAction extends Action {
    private UserService userService;

    public LoginAction() {
        userService = new UserService();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        LoginForm loginForm = (LoginForm) form;
        User user = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            Cookie roleCookie = new Cookie("MS", user.getRole());
            roleCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(roleCookie);

            return mapping.findForward("success");
        } else {
            return mapping.findForward("failure");
        }
    }
}
