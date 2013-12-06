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
						<li><a href="${rc.contextPath}/user/list.do">User</a></li>
						<li class="active"><a>Team & Application</a></li>
					</ul>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<div class="span12">
						<button id="btnCreateAccess" class="btn btn-inverse" type="button">Create</button>
						<button id="btnEditAccess" class="btn btn-inverse" type="button">Edit</button>
						<button id="btnDeleteAccess" class="btn btn-inverse" type="button">Delete</button>
					</div>
					<div class="span12"></div>
					<table class="table">
						<caption>Access</caption>
						<thead>
							<tr>
								<th></th>
								<th>Access</th>
								<th>Code</th>
							</tr>
						</thead>
						<tbody>
							<#if accessList?? && accessList?size &gt; 0> 
							<#list accessList as item>
							<tr>
								<td><input type="radio" name="access" value="${item.id}"></td>
								<td>${item.name!''}</td>
								<td>${item.code!''}</td>
							</tr>
							</#list> 
							<#else>
							<tr>
								<td colspan="3" style="text-align: center;">no any record</td>
							</tr>
							</#if>
						</tbody>
					</table>
				</div>
				<div class="span4">
					<div class="span12">
						<button id="btnCreateAppAccess" class="btn btn-inverse" type="button">Create</button>
						<button id="btnEditAppAccess" class="btn btn-inverse" type="button">Edit</button>
						<button id="btnDeleteAppAccess"class="btn btn-inverse" type="button">Delete</button>
					</div>
					<div class="span12"></div>
					<table class="table">
						<caption>Application & Access</caption>
						<thead>
							<tr>
								<th></th>
								<th>Application</th>
								<th>Access</th>
								<th>Code</th>
							</tr>
						</thead>
						<tbody>
							<#if appAccessList?? && appAccessList?size &gt; 0>
							<#list appAccessList as item>
							<tr>
								<td><input type="radio" name="app_access" value="${item.id}" access_id="${item.access.id}"></td>
								<td>${item.appName!''}</td>
								<td>${item.access.name!''}</td>
								<td>${item.access.code!''}</td>
							</tr>
							</#list> 
							<#else>
							<tr>
								<td colspan="4" style="text-align: center;">no any record</td>
							</tr>
							</#if>
						</tbody>
					</table>
				</div>
				<div class="span4">
					<div class="span12">
						<button id="btnCreateTeam" class="btn btn-inverse" type="button">Create Team</button>
						<button id="btnEditTeam" class="btn btn-inverse" type="button">Edit Team</button>
					</div>
					<div class="span12"></div>
					<table class="table">
						<caption>Team</caption>
						<thead>
							<tr>
								<th></th>
								<th>Team</th>
								<th>Code</th>
							</tr>
						</thead>
						<tbody>
							<#if teamList?? && teamList?size &gt; 0> 
							<#list teamList as item>
							<tr>
								<td><input type="radio" name="team" value="${item.id}"></td>
								<td>${item.name!''}</td>
								<td>${item.code!''}</td>
							</tr>
							</#list> 
							<#else>
							<tr>
								<td colspan="3" style="text-align: center;">no any record</td>
							</tr>
							</#if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="createAccessPopup" class="hide">
		<form>
			<label>access</label> <input type="text" name="name" maxlength="30"/> <label>code</label> <input type="text" name="code" maxlength="10"/>
		</form>
	</div>
	<div id="createTeamPopup" class="hide">
		<form>
			<label>team</label> <input type="text" name="name" maxlength="30"/> <label>code</label> <input type="text" name="code" maxlength="10"/>
		</form>
	</div>
	<div id="createAppAccessPopup" class="hide">
		<form>
			<label>application</label>
			<select name="application">
				<#if appList?? && appList?size &gt; 0> 
				<#list appList as item>
				<option value="${item.name}">${item.name}</option>
				</#list>
				</#if>
			</select>
			<label>access</label>
			<select name="access">
				<#if accessList?? && accessList?size &gt; 0> 
				<#list accessList as item>
				<option value="${item.id}">${item.name}</option>
				</#list>
				</#if>
			</select>
		</form>
	</div>
	<div id="editAppAccessPopup" class="hide">
		<form>
			<input type="hidden" name="id">
			<label>application</label>
			<select name="application">
				<#if appList?? && appList?size &gt; 0> 
				<#list appList as item>
				<option value="${item.name}">${item.name}</option>
				</#list>
				</#if>
			</select>
			<label>access</label>
			<select name="access">
				<#if accessList?? && accessList?size &gt; 0> 
				<#list accessList as item>
				<option value="${item.id}">${item.name}</option>
				</#list>
				</#if>
			</select>
		</form>
	</div>
	<div id="editTeamPopup" class="hide">
		<form>
			<input type="hidden" name="teamId" value=""> 
			<label>team</label> 
			<input type="text" name="name" value="" maxlength="30"/> <label>code</label> <input
				type="text" name="code" value="" maxlength="10"/>
		</form>
	</div>
	<div id="editAccessPopup" class="hide">
		<form>
			<input type="hidden" name="accessId" value=""> 
			<label>access</label> 
			<input type="text" name="name" value="" maxlength="30"/> <label>code</label> <input
				type="text" name="code" value="" maxlength="10"/>
		</form>
	</div>
	<script type="text/javascript" src="${rc.contextPath}/resources/js/app.js?v=${res_version}"></script>
	<#include "../lib/footer.ftl">
</body>
</html>