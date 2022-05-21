<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib prefix = "c"  uri = "http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-integer code="inventor.item-toolkit.form.label.quantity" path="quantity"/>
	<jstl:choose>
		<jstl:when test="${command=='create'}">		 
			<acme:input-select code="inventor.item-toolkit.form.label.item" path="itemId">
				<c:forEach items="${items}" var="item">
					<acme:input-option code="${item.name}" value="${item.id}"/>
				</c:forEach>
			</acme:input-select>
			
			<acme:submit code="inventor.item-toolkit.form.button.add-item" action="/inventor/item-toolkit/create?toolkitId=${toolkit.id}"/>
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(command, 'show, delete, update')}">
			<acme:input-textbox readonly="true" code="inventor.item-toolkit.form.label.item" path="itemName"/>
			<br>
			<br>
			<acme:submit code="inventor.item-toolkit.form.button.update-item" action="/inventor/item-toolkit/update"/>
			<acme:submit code="inventor.item-toolkit.form.button.delete-item" action="/inventor/item-toolkit/delete"/>
		</jstl:when>
		
	</jstl:choose>
</acme:form>

