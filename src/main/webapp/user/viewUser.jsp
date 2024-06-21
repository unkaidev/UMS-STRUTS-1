<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="user.information" /></title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<a href="<html:rewrite page='/list-user.html' />"
			class="btn btn-warning" style="color: white;"> << <bean:message
				key="list.user" />
		</a>
		<h1>
			<bean:message key="user.information" />
		</h1>
		<div class="card" style="width: 18rem;">
			<img class="card-img-top"
				src="data:image/jpeg;base64,<bean:write name='user' property='avatar' filter='false' />" />
			<div class="card-body">
				<h5 class="card-title">
					<bean:message key="user.name" />
					:
					<bean:write name="user" property="name" />
				</h5>
				<h5 class="card-title">
					<bean:message key="user.phone" />
					:
					<bean:write name="user" property="phone" />
				</h5>
				<h5 class="card-title">
					<bean:message key="user.username" />
					:
					<bean:write name="user" property="username" />
				</h5>
				<p class="card-text">
					<bean:message key="user.about" />
					:
					<bean:write name="user" property="about" />
				</p>
				<p class="card-text">
					<bean:message key="user.favorite" />
					:
					<bean:write name="user" property="favorite" />
				</p>
			</div>
		</div>
	</div>


</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</html>