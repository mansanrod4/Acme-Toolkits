

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
		
		<acme:input-textbox code="inventor.patronage.label.patron" path="patronId"/>
		<acme:input-textbox code="inventor.patronage.label.inventor" path="inventorId"/>
	</jstl:if>	
	<acme:input-textarea code="inventor.patronage.label.legalStuff" path="legalStuff"/>
	<acme:input-textarea code="inventor.patronage.label.budget" path="budget"/>
	<acme:input-textarea code="inventor.patronage.label.info" path="info"/>			
</acme:form>

