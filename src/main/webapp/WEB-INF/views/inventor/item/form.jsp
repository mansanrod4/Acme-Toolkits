<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form readonly="${readonly}">
	<acme:input-textbox code="inventor.component.list.label.code"
		path="code" placeholder="XXX-123, XXX-235-X" />
	<acme:input-textbox code="inventor.component.list.label.name"
		path="name" />
	<acme:input-textbox code="inventor.component.list.label.tech"
		path="technology" />
	<acme:input-textbox code="inventor.component.list.label.description"
		path="description" />
	<acme:input-money code="inventor.component.list.label.price"
		path="retailPrice" />
	<acme:input-textarea code="inventor.component.list.label.info"
		path="info" placeholder="URL" />


	<jstl:choose>
		<jstl:when
			test="${acme:anyOf(command, 'show, update, delete') && published == false}">
			<acme:submit code="inventor.item.form.button.delete"
				action="/inventor/item/delete" />
			<acme:submit code="inventor.item.form.button.publish"
				action="/inventor/item/publish" />
		</jstl:when>

	</jstl:choose>

</acme:form>

