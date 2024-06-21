<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="button.add.user" /></title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container col-4">
		<div class="container">
			<a href="<html:rewrite page='/list-user.html' />"
				class="btn btn-warning" style="color: white;"> << <bean:message
					key="list.user" />
			</a>
		</div>

		<h1 class="text-center">
			<bean:message key="button.add.user" />
		</h1>

		<html:form styleClass="container" action="/add-user-post.html"
			method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label class="form-label"> <bean:message key="user.name" />
				</label>
				<html:text styleClass="form-control" property="name" name="user"></html:text>
				<html:messages id="name_error" property="user.name.required">
					<p style="color: red">
						<bean:write name="name_error" />
					</p>
				</html:messages>
			</div>
			<div class="mb-3">
				<label class="form-label"> <bean:message key="user.phone" />
				</label>
				<html:text styleClass="form-control" property="phone" name="user"></html:text>
			</div>
			<div class="mb-3">
				<label class="form-label"> <bean:message key="user.username" />
				</label>
				<html:text styleClass="form-control" property="username" name="user"></html:text>
				<html:messages id="username_error" property="user.username.required">
					<p style="color: red">
						<bean:write name="username_error" />
					</p>
				</html:messages>
			</div>
			<div class="mb-3">
				<label class="form-label"> <bean:message key="user.password" />
				</label>
				<html:password styleClass="form-control" property="password"
					name="user"></html:password>
				<html:messages id="password_error" property="user.password.required">
					<p style="color: red">
						<bean:write name="password_error" />
					</p>
				</html:messages>
			</div>
			<div class="mb-3">
				<label class="form-label"> <bean:message key="user.about" />
				</label>
				<html:textarea styleClass="form-control" property="about"
					name="user"></html:textarea>
			</div>
			<div class="mb-3">
				<label class="form-label"> <bean:message key="user.favorite" />
				</label>
				<html:text styleClass="form-control" property="favorite" name="user"></html:text>
			</div>
			<br>
			<div class="mb-3">
				<label class="form-label"> <bean:message key="user.role" />
				</label>
				<html:radio property="role" name="user" value="ROLE_ADMIN">
					<bean:message key="user.role.admin" />
				</html:radio>
				<html:radio property="role" name="user" value="ROLE_USER">
					<bean:message key="user.role.user" />
				</html:radio>
				<html:messages id="role_error" property="user.role.required">
					<p style="color: red">
						<bean:write name="role_error" />
					</p>
				</html:messages>
			</div>
			<br>
			<div class="mb-3">
				<label for="formFile" class="form-label"> <bean:message
						key="user.avatar" />
				</label>
				<html:file property="file" name="user" accept="image/*"></html:file>
			</div>
			<br>
			<button type="submit" class="btn btn-primary">
				<bean:message key="button.submit" />
			</button>


		</html:form>
	</div>

</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</html>