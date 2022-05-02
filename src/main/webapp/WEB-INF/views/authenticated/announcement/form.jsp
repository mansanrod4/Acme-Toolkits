<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-select code="authenticated.announcement.form.label.critical" path="critical">
		<acme:input-option code="authenticated.announcement.form.label.TRUE" value="TRUE"/>
		<acme:input-option code="authenticated.announcement.form.label.FALSE" value="FALSE"/>
	</acme:input-select>
	<acme:input-url code="authenticated.announcement.form.label.link" path="link"/>	
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="authenticated.announcement.form.button.create" 
			action="/authenticated/announcement/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>	
	
</acme:form>
