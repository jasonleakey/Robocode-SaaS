<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style type="text/css">
div#container {
	width: 500px
}

div#header {
	background-color: #99bbbb;
}

div#robotlist {
	background-color: #ffff99;
	height: 200px;
	width: 300px;
	float: left;
}

div#content {
	background-color: #EEEEEE;
	height: 200px;
	width: 600px;
	float: left;
}

div#robotbuttons {
	background-color: #ffff99;
	clear: both;
	float: left;
}

div#footer {
	background-color: #99bbbb;
	clear: both;
	text-align: center;
}

h1 {
	margin-bottom: 0;
}

h2 {
	margin-bottom: 0;
	font-size: 14px;
}

ul {
	margin: 0;
}

li {
	list-style: none;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>
		<label for="User Id"><strong>User Id: </strong></label>
		<%
		    String id = request.getParameter("id");
		    out.println(id);
		%>
	</p>
	<div class="container">
		<fieldset>
			<legend>Your Robot List</legend>
			<div id="robotlist" style="overflow: scroll; border: 1px solid;">
				<ol>
					<c:forEach items="${robotList}" var="robot">
						<li>${robot.name}</li>
					</c:forEach>
				</ol>
			</div>
			
			<div id="robotbuttons">
				<div style="float: left">
					<form action="join" method="get">
						<input type="text" name="robot_name">
						<input type="submit" class="btn btn-danger btn-mini"
							value="Join" />
					</form>
				</div>
			</div>
		</fieldset>
	</div>
</body>
</html>