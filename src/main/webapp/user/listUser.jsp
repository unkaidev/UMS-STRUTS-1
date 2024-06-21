<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="list.user" /></title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="container">
			<div class="row justify-content-between">
				<div class="col-sm-6 text-left">
					<a href="<html:rewrite page='/language.html?la=vietnamese' />"
						class="btn btn-primary" style="color: yellow">Tiếng Việt</a> <a
						href="<html:rewrite page='/language.html?la=english' />"
						class="btn btn-primary" style="color: white">English</a>
				</div>
				<div class="col-sm-6 text-right">
					<a href="<html:rewrite page='/logout.html' />"
						class="btn btn-danger" style="color: yellow"><bean:message key="form.logout"/> >></a>
				</div>
			</div>
		</div>

		<h1 class="text-center">
			<bean:message key="list.user" />
		</h1>
		<div class="container py-3">
			<a href="<html:rewrite page='/add-user.html' />"
				class="btn btn-success" style="color: white;"> <bean:message
					key="button.add" />
			</a>
		</div>
		<br>
		<div class="container">
			<logic:empty name="users">
				<p>
					<bean:message key="no.user" />
				</p>
			</logic:empty>
			<logic:notEmpty name="users">
				<table class="table table-hover table-bordered table-striped ">
					<tr>
						<th class="text-center"><bean:message key="user.id" /></th>
						<th class="text-center"><bean:message key="user.name" /></th>
						<th class="text-center"><bean:message key="user.phone" /></th>
						<th class="text-center"><bean:message key="user.username" /></th>
						<th class="text-center"><bean:message key="user.role" /></th>
						<th class="text-center"><bean:message key="option" /></th>
					</tr>
					<logic:iterate id="user" name="users">
						<tr>
							<td><bean:write name="user" property="id" /></td>
							<td><bean:write name="user" property="name" /></td>
							<td><bean:write name="user" property="phone" /></td>
							<td><bean:write name="user" property="username" /></td>
							<td><logic:equal value="ROLE_ADMIN" name="user"
									property="role">
									<bean:message key="user.role.admin" />
								</logic:equal> <logic:equal value="ROLE_USER" name="user" property="role">
									<bean:message key="user.role.user" />
								</logic:equal></td>
							<td class="text-center"><a
								href="<html:rewrite page='/edit-user.html' paramId='userId' paramName='user' paramProperty='id' />"
								class="btn btn-warning" style="color: white;"> <bean:message
										key="button.edit" />
							</a> <a
								href="<html:rewrite page='/delete-user.html' paramId='userId' paramName='user' paramProperty='id' />"
								class="btn btn-danger" style="color: white;"
								onclick="return confirmAction('<bean:message key="user.confirm" />', '<bean:message key="button.delete" />')">
									<bean:message key="button.delete" />
							</a> <a
								href="<html:rewrite page='/view-user.html' paramId='userId' paramName='user' paramProperty='id' />"
								class="btn btn-primary"> <bean:message key="button.view" />
							</a></td>
						</tr>
					</logic:iterate>
				</table>
			</logic:notEmpty>
			<div class="text-center">
				<nav aria-label="Page navigation">
				<ul class="pagination ">
					<logic:iterate id="i" name="pages">
						<li
							class="page-item <%=(i.equals(request.getAttribute("currentPage"))) ? "active" : ""%>">
							<a class="page-link"
							href="list-user.html?page=<bean:write name="i" />&size=<bean:write name="size" />"><bean:write
									name="i" /></a>
						</li>
					</logic:iterate>
				</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script>
	function confirmAction(question, action) {
		return confirm(question + action + "?");
	}
</script>
</html>
