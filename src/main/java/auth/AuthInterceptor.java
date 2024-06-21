package auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.util.MessageResources;

public class AuthInterceptor extends MappingDispatchAction {

	public ActionForward execute(ActionMapping mapping, org.apache.struts.action.ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("test");
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("USER") == null) {
			ActionMessages errors = new ActionMessages();
			MessageResources resources = getResources(request);
			errors.add(ActionMessages.GLOBAL_MESSAGE,
					new org.apache.struts.action.ActionMessage("error.authentication.required"));
			saveErrors(request, errors);
			return mapping.findForward("login");
		}

		return super.execute(mapping, form, request, response);
	}
}