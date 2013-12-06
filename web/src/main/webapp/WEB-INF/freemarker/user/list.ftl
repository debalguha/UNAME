<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin Index Page</title> <#include "../lib/header.ftl">
</head>
<body>
	<div class="container">
		<#include "../lib/logo-bar.ftl">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<ul class="nav nav-tabs">
						<li class="active"><a>User</a></li>
						<li><a href="${rc.contextPath}/app/list.do">Team & Application</a></li>
					</ul>
				</div>
			</div>
			<div>
				<button id="btnCreateUserPage" class="btn btn-inverse" type="button">Create User</button>
				<button id="btnEditUserPage" class="btn btn-inverse" type="button">Edit User</button>
				<button id="btnChangePassword" class="btn btn-inverse" type="button">Change Password</button>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Username</th>
						<th>status</th>
						<th>FirstName</th>
						<th>LastName</th>
						<th>Phone</th>
						<th>Team</th>
						<#if appList?? && appList?size &gt; 0>
						<#list appList as item>
							<th>${item.name}</th>
						</#list>
						</#if>
					</tr>
				</thead>
				<tbody>
				
					<#if dataList?? && dataList?size &gt; 0>
						<#list dataList as item>
							<tr>
								<td><input type="radio" name="userId" value="${item.id}"></td>
								<td>${item.username}</td>
								<td>${item.status}</td>
								<td>${item.firstName!''}</td>
								<td>${item.lastName!''}</td>
								<td>${item.phone!''}</td>
								<td>
									<#if item.team??>
										${item.team.name!''}
									</#if>
								</td>
								<#if appList?? && appList?size &gt; 0>
								<#list appList as app>
									<td>
									<#if item.userAppAccessList?? && item.userAppAccessList?size &gt; 0>
									<#list item.userAppAccessList as uaa>
										<#if uaa.appName == app.name>
											${uaa.access.name!''}
										</#if>
									</#list>
									</#if>
									</td>
								</#list>
								</#if>
							</tr>
						</#list>
					<#else>
						<tr><td colspan="12" style="text-align: center;">no any record</td></tr>						
					</#if>
				</tbody>
			</table>
		</div>
	</div>
	<div id="changePwdPopup" class="hide">
		<form>
			<input type="hidden" name="userId" value=""> 
			<label style="width: 90px">username</label> 
			<input type="text" name="username" value="" disabled="disabled"/> 
			<label style="width: 90px">new password</label>
			<input type="password" name="password" value="" maxlength="30"/>
		</form>
	</div>
	<script type="text/javascript" src="${rc.contextPath}/resources/js/user.js?v=${res_version}"></script>
	<#include "../lib/footer.ftl">
</body>
</html>