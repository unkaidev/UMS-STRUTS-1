package model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

public class User extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3473142019064898563L;
	private int id;
	private String name;
	private String phone;
	private String username;
	private String password;
	private String about;
	private String favorite;
	private String role;
	private String avatar;
	private FormFile file;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		if (getName() == null || getName().length() == 0) {
			actionErrors.add("user.name.required", new ActionMessage("error.user.name.required"));
		}
		if (getUsername() == null || getUsername().length() == 0) {
			actionErrors.add("user.username.required", new ActionMessage("error.user.username.required"));
		}
		if (getPassword() == null || getPassword().length() == 0) {
			actionErrors.add("user.password.required", new ActionMessage("error.user.password.required"));
		}
		if (getRole() == null || getRole().length() == 0) {
			actionErrors.add("user.role.required", new ActionMessage("error.user.role.required"));
		}
		if (getFile() == null || getFile().getFileSize() == 0) {
			actionErrors.add("file.empty", new ActionMessage("error.file.empty"));
		}
		
		return actionErrors;
	}

}
