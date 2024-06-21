<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title><bean:message key="form.login" /></title>
<style type="text/css">
body {
    font-family: Arial, sans-serif;
}

.container {
    text-align: center;
}

h2 {
    margin-bottom: 20px;
}

html form {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}

html form label {
    display: block;
    margin-top: 10px;
    font-weight: bold;
}

html form input[type="text"],
html form input[type="password"] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

html form button[type="submit"] {
    margin-top: 10px;
}
</style>
</head>
<body>

	<div class="container text-center
	col-lg-4 col-lg-offset-4 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2
	">

		<h2><bean:message key="form.login" /></h2>
        <html:form action="/login.html" method="post">
            <label><bean:message key="user.username" /></label>
            <html:text property="username" name="loginForm" />
            <label>Password:</label>
            <html:password property="password" name="loginForm" />
            <button class="btn btn-primary" type="submit">
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
