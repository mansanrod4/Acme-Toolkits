<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-textbox code="inventor.toolkit.form.label.inventor" path="inventor"/>
	<acme:input-money code="inventor.toolkit.form.label.price" path="price"/>
	<acme:input-url code="inventor.toolkit.form.label.info" path="info"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="inventor.tooolkit.form.button.delete" action="/inventor/toolkit/delete"/>
			<acme:submit code="inventor.tooolkit.form.button.publish" action="/inventor/toolkit/publish"/>
		</jstl:when>
	
	</jstl:choose>
</acme:form>
<br>
<br>
<acme:button code="inventor.toolkit.form.label.content" action="/any/item-toolkit/list?masterId=${id}"/>
