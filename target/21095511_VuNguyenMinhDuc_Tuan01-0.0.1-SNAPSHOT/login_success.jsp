<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login Success Page</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f2f5;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.success-container {
	background-color: #fff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
	width: 400px;
	text-align: center;
}

.success-container h3 {
	color: #333;
	margin-bottom: 20px;
}

.success-container p {
	font-size: 14px;
	color: #555;
}

.success-container input[type="submit"] {
	padding: 10px 20px;
	background-color: #ff4b5c;
	border: none;
	border-radius: 5px;
	color: white;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.success-container input[type="submit"]:hover {
	background-color: #e0444f;
}

.success-container .welcome-message {
	font-size: 18px;
	color: #4CAF50;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<%
	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userIdCookie")) {
		userName = cookie.getValue();
			}

			if (cookie.getName().equals("JSESSIONID")) {
		sessionID = cookie.getValue();
			}
		}
	}
	%>
	<div class="success-container">
		<h3 class="welcome-message">
			Hi
			<%=(userName != null) ? userName : "Guest"%>, welcome back!
		</h3>
		<p>Your Session ID=<%=sessionID%></p>
		
		<p>You're successfully logged in. Ready to checkout?</p>
		<form action="logout" method="post">
			<input type="submit" value="Logout">
		</form>
	</div>
</body>
</html>
