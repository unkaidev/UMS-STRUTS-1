package action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.upload.FormFile;

import dao.UserDao;
import model.User;
import service.UserService;

public class UserAction extends MappingDispatchAction {
	private static Logger logger = Logger.getLogger(UserAction.class);
	private UserService userService;

	public UserAction() {
		userService = new UserService();
	}

	public ActionForward addUserPost(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		User user = (User) form;
		String username = user.getUsername();
		if(!userService.checkUserExist(username)) {
			String password = user.getPassword();
			String hashedPassword = userService.hashPassword(password);
			user.setPassword(hashedPassword);
			FormFile file = user.getFile();

			if (file != null) {
				String base64Image = Base64.getEncoder().encodeToString(file.getFileData());
				user.setAvatar(base64Image);
				userService.addUser(user);
			}

			return mapping.findForward("success");
		}
		else {
			return null;
		}
		
	}

	public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("userId"));
		userService.deleteUser(id);
		return mapping.findForward("success");
	}

	public ActionForward editUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("userId"));
		User user = userService.getUserById(id);
		request.setAttribute("user", user);
		return mapping.findForward("editUser");
	}

	public ActionForward editUserPost(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) form;
		FormFile file = user.getFile();

		if (file != null) {
			String base64Image = Base64.getEncoder().encodeToString(file.getFileData());
			user.setAvatar(base64Image);
			userService.editUser(user);
		}

		return mapping.findForward("success");
	}

	public ActionForward viewUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("userId"));
		User user = userService.getUserById(id);

		request.setAttribute("user", user);
		return mapping.findForward("viewUser");
	}

	public ActionForward listUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int page = 1;
		int size = 5;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameter("size") != null) {
			size = Integer.parseInt(request.getParameter("size"));
		}

		int start = (page - 1) * size;
		int totalUsers = userService.getTotalUsers();
		List<User> users = userService.getUsers(start, size);

		int totalPages = (int) Math.ceil((double) totalUsers / size);
		List<Integer> pages = new ArrayList<>();
		for (int i = 1; i <= totalPages; i++) {
			pages.add(i);
		}

		request.setAttribute("users", users);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("size", size);
		request.setAttribute("pages", pages);

		return mapping.findForward("listUser");
	}

//	public ActionForward downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		response.setContentType("application/octet-stream");
//		response.setHeader("Content-Disposition", "attachment;filename=anh.jfif");
//		URL url = getServlet().getServletContext().getResource("images/avatar.jfif");
//		InputStream in = url.openStream();
//		ServletOutputStream out = response.getOutputStream();
//		byte[] outputByte = new byte[4096];
//		while (in.read(outputByte, 0, 4096) != -1) {
//			out.write(outputByte, 0, 4096);
//		}
//		in.close();
//		out.flush();
//		out.close();
//
//		return null;
//	}
}
