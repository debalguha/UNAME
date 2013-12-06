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
			<div class="row-fluid">
				<div class="span12">
					<form class="base-form" id="addUserForm">
						<fieldset>
							<legend>
								Create User <a class="pull-right" href="${rc.contextPath}/user/list.do">Back</a>
							</legend>
							<div>
								<label>UserName</label><input type="text" name="username"/>
								<span class="required-field">*</span>
							</div>
							<div>
								<label>Password</label><input type="password" name="password"/>
								<span class="required-field">*</span>
							</div>
							<div>
								<label>Email</label><input type="text" name="email"/>
								<span class="required-field">*</span>
							</div>
							<div>
								<label>Status</label><select name="status">
									<option value="active">Active</option>
									<option value="inactive">Inactive</option>
								</select>
							</div>
							<div>
								<label>User Type</label><select name="type">
									<option value="user">application user</option>
									<option value="creator">creator</option>
								</select>
							</div>
							<div>
								<label>FirstName</label><input type="text" name="firstName"/>
								<span class="required-field">*</span>
							</div>
							<div>
								<label>LastName</label><input type="text" name="lastName"/>
								<span class="required-field">*</span>
							</div>
							<div>
								<label>Phone</label><input type="text" name="phone"/>
								<span class="required-field">*</span>
							</div>
							<#if appList?? && appList?size &gt; 0 >
							<#list appList as item>
							<div style="color: green;font-weight:bold;">
								<label>App:${item.name}</label><select name="app:${item.name}">
									<option value="0">no any access</option>
									<#if appAccessList?? && appAccessList?size &gt; 0 >
									<#list appAccessList as appAccessItem>
										<#if appAccessItem.appName == item.name >
										<option value="${appAccessItem.access.id}">${appAccessItem.access.name}</option>
										</#if> 
									</#list>
									</#if>
								</select>
							</div>
							</#list>
							</#if>
							<div>
								<label>Team</label><select name="teamId">
									<#if teamList?? && teamList?size &gt; 0>
									<#list teamList as item>
										<option value="${item.id}">${item.name}</option>
									</#list>
									</#if>
								</select>
							</div>
							<div>
								<label>Approver full name</label><input type="text" name="approverFullname"/>
							</div>
							<div>
								<label>Approver email</label><input type="text" name="approverEmail"/>
							</div>
							<div>
								<label>Approver phone number</label><input type="text" name="approverPhone"/>
							</div>
							<div>
								<label>Request detail</label><textarea rows="4" cols="180" name="requestDetail"></textarea>
							</div>
							<div>
								<label></label><button type="submit" class="btn btn-success">submit</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${rc.contextPath}/resources/js/user.js?v=${res_version}"></script>
	<#include "../lib/footer.ftl">
</body>
</html>