

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:hidden-data path="patronageId"/>

	<acme:input-textbox code="inventor.patronage.label.code" path="code" readonly="true"/>
	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="inventor.patronage.label.creationDate" path="creationDate"/>
		<acme:input-textbox code="inventor.patronage.label.startDate" path="startDate"/>
		<acme:input-textbox code="inventor.patronage.label.endDate" path="endDate"/>
		<acme:input-textbox code="inventor.patronage.label.status" path="status"/>
		
		
	</jstl:if>	
	<acme:input-textarea code="inventor.patronage.label.legalStuff" path="legalStuff"/>
	<acme:input-textarea code="inventor.patronage.label.budget" path="budget"/>
	<acme:input-textarea code="inventor.patronage.label.info" path="info"/>			
	
	<acme:input-textbox code="inventor.patronage.label.patronFullName" path="patronFullName"/>
	<acme:input-textbox code="inventor.patronage.label.patronEmail" path="patronEmail"/>
	<acme:input-textbox code="inventor.patronage.label.patronCompany" path="patronCompany"/>
	<acme:input-textbox code="inventor.patronage.label.patronStatement" path="patronStatement"/>
	<acme:input-textbox code="inventor.patronage.label.patronInfo" path="patronInfo"/>
	
	<acme:button code="inventor.patronage.form.button.patronage-reports" action="/inventor/patronage-report/list?patronageId=${id}"/>			
	
	
</acme:form>

