<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"
		readonly="true" />
	<acme:input-textbox code="inventor.chimpum.form.label.moment"
		path="moment" readonly="true" />
	<acme:input-textbox code="inventor.chimpum.form.label.title"
		path="title" />
	<acme:input-textbox code="inventor.chimpum.form.label.description"
		path="description" />
	<acme:input-textbox code="inventor.chimpum.form.label.startDate"
		path="startDate" />
	<acme:input-textbox code="inventor.chimpum.form.label.endDate"
		path="endDate" />
	<acme:input-textbox code="inventor.chimpum.form.label.budget"
		path="budget" />
	<acme:input-textbox code="inventor.chimpum.form.label.link" path="link" />
	
<%-- 	<acme:input-select code="inventor.item.label.inventor" path="itemId">
		<jstl:forEach items="${tools}" var="item">
			<acme:input-option code="${item.name}" value="${item.id}"
				selected="false" />
		</jstl:forEach>
	</acme:input-select> --%>


	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.chimpum.form.button.create"
				action="/inventor/chimpum/create?itemId=${itemId}" />
		</jstl:when>
		<jstl:when
			test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="inventor.chimpum.button.update"	action="/inventor/chimpum/update" />
			<acme:submit code="inventor.chimpum.form.button.delete" action="/inventor/chimpum/delete" />
		</jstl:when>
	</jstl:choose>

</acme:form>