<#macro page urlPrefix urlPostfix pageInfo> 
	<#if pageInfo.pageCount &gt; 0 >
	<div class="mod-page-box clearfix">
		<div class="mod-page">
			<#assign pageCount=pageInfo.pageCount pageIndex=pageInfo.pageIndex liststep=4>
			<#assign bar=liststep/2 listend=pageIndex+bar>  
			<#if pageIndex-bar &lt; 1>  
				<#assign listbegin=1>   
			<#else>
				<#assign listbegin=pageIndex-bar>
			</#if>			
		 	<#if listend &gt; pageCount>
		 		<#assign listend=pageCount> 
		 	</#if>
			
			<#if pageIndex &gt; 1>
				<a href="${urlPrefix}${pageInfo.getPrevious()}${urlPostfix!''}"><i class="prev"></i></a>
			</#if>
			<#if listbegin &gt; 1>
				<a href="${urlPrefix}1${urlPostfix!''}">1</a>
			</#if>
			<#if listbegin &gt; 2>
				<a style="border: 0px none;">...</a>
			</#if>
			<#list listbegin .. listend as i>
				<#if i!=pageIndex>
					<a href="${urlPrefix}${i}${urlPostfix!''}" title="page ${i}">${i}</a>
				<#elseif listend &gt;= 1>
					<a class="current">${i}</a>
				</#if>
			</#list>
	 		<#if listend &lt; pageCount>
	 			<#if listend+1 &lt; pageCount>
	 				<a style="border: 0px none;">...</a>
	 			</#if>
	 			<a href="${urlPrefix}${pageCount}${urlPostfix!''}" title="page ${pageCount}">${pageCount}</a>
			</#if>
			<#if pageIndex!=pageCount>
				<a href="${urlPrefix}${pageInfo.getNext()}${urlPostfix!''}"><i class="next"></i></a>
			</#if>
		</div>
	</div>
	</#if>
</#macro>
<#--
<div class="mod-page-box clearfix">
	<div class="mod-page">
		<a href="#" ><i class="prev"></i></a>
		<a class="current">1</a>
		<a href="">2</a>
		<a href="">3</a>
		<a href=""><i class="next"></i></a>
		<select>
			<option>5</option>
		</select>
	</div>
</div>
-->