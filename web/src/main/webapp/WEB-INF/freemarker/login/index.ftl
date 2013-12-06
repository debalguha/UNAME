<!DOCTYPE html>
<html lang="en">
<head>
<title>Login Page</title> <#include "../lib/header.ftl">
<style>
	.logout-link{
		display: none;
	}
</style>
</head>
<body>
	<div class="container">
		<#include "../lib/logo-bar.ftl">
		<form class="login-form">
			<h3 class="form-signin-heading">User Management Account Login</h3>
			<div class="text-center">
				<input type="text" placeholder="Username" name="username" value="steven" autofocus="autofocus">
			</div>
			<div class="text-center">
				<input type="password" placeholder="Password" name="password" value="123456">
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-large btn-primary">Log In</button>
			</div>
			<div class="text-center hide">
				<a href="#">Problem with user or password?</a>
			</div>
		</form>
	</div>
	<#include "../lib/footer.ftl">
	<script type="text/javascript" src="${rc.contextPath}/resources/js/account.js"></script>
</body>
</html>