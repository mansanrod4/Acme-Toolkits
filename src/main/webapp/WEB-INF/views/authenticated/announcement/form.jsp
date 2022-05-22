<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>
	<acme:input-textbox code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-textbox code="authenticated.announcement.form.label.critical" path="critical"/>
	<acme:input-textbox code="authenticated.announcement.form.label.link" path="link"/>		
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="administrator.announcement.form.label.confirmation" path="confirmation"/>
			<acme:submit code="administrator.announcement.form.button.create" 
			action="/administrator/announcement/create"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>
