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
					<form class="base-form" id="editUserForm">
						<fieldset>
							<legend>
								Edit User <a class="pull-right" href="${rc.contextPath}/user/list.do">Back</a>
							</legend>
							<input type="hidden" name="userId" value="${userInfo.id}">
							<div>
								<label>UserName</label><input type="text" disabled="disabled" name="username" value="${userInfo.username}"/>
								<span class="required-field">*</span>
							</div>
							<div>
								<label>Email</label><input type="text" disabled="disabled" name="email" value="${userInfo.email!''}"/>
								<span class="required-field">*</span>
							</div>
							<div>
								<label>User Type</label><select name="type">
									<option value="user" <#if (userInfo.type == 'user')>selected</#if> >application user</option>
									<option value="creator" <#if (userInfo.type == 'creator')>selected</#if> >creator</option>
								</select>
							</div>
							<div>
								<label>Status</label><select name="status">
									<option value="active" <#if (userInfo.status == 'active')>selected</#if>>Active</option>
									<option value="inactive" <#if (userInfo.status == 'inactive')>selected</#if> >Inactive</option>
								</select>
							</div>
							<div>
								<label>FirstName</label><input type="text" name="firstName" value="${userInfo.firstName!''}"/>
								<span class="required-field">*</span>
							</div>
							<div>
								<label>LastName</label><input type="text" name="lastName" value="${userInfo.lastName!''}"/>
								<span class="required-field">*</span>
							</div>
							<div>
								<label>Phone</label><input type="text" name="phone" value="${userInfo.phone!''}"/>
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
											<#assign sltStr = ''>
											<#if userInfo.userAppAccessList?? && userInfo.userAppAccessList?size &gt; 0>
											<#list userInfo.userAppAccessList as uaa>
												<#if item.name == uaa.appName && appAccessItem.access.id == uaa.access.id>
													<#assign sltStr = 'selected'>
												</#if>
											</#list>
											</#if>
											<option value="${appAccessItem.access.id}" ${sltStr}>${appAccessItem.access.name}</option>
										</#if> 
									</#list>
									</#if>
								</select>
							</div>
							</#list>
							</#if>
							<div>
							 	<#assign userTeamId = ''>
							 	<#if userInfo.team??>
							 	<#assign userTeamId = userInfo.team.id>
							 	</#if>
								<label>Team</label><select name="teamId">
									<#if teamList?? && teamList?size &gt; 0>
									<#list teamList as item>
										<option value="${item.id}" <#if userTeamId== item.id>selected</#if>  >${item.name}</option>
									</#list>
									</#if>
								</select>
							</div>
							<div>
								<label>Approver full name</label><input type="text" name="approverFullname" value="${userInfo.approverFullname!''}"/>
							</div>
							<div>
								<label>Approver email</label><input type="text" name="approverEmail" value="${userInfo.approverEmail!''}"/>
							</div>
							<div>
								<label>Approver phone number</label><input type="text" name="approverPhone" value="${userInfo.approverPhone!''}"/>
							</div>
							<div>
								<label>Request detail</label><textarea rows="4" cols="180" name="requestDetail" value="${userInfo.requestDetail!''}"></textarea>
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