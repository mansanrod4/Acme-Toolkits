<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="inventor.patronage-report.form.label.moment" path="moment"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.info" path="info"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.number" path="number"/>

</acme:form>