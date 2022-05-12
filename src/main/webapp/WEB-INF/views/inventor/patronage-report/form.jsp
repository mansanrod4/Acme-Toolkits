<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<%-- 	<acme:hidden-data path="patronageId"/>
 --%>
	<acme:input-textbox
		code="inventor.patronage-report.form.label.patronageId"
		path="patronageId" />
	<acme:input-textbox code="inventor.patronage-report.form.label.moment"
		path="moment" />
	<acme:input-textbox
		code="inventor.patronage-report.form.label.memorandum"
		path="memorandum" />
	<acme:input-textbox code="inventor.patronage-report.form.label.info"
		path="info" />
	<acme:input-textbox
		code="inventor.patronage-report.form.label.sequenceNumber"
		path="sequenceNumber" />
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.patronage-report.form.button.create"
				action="/inventor/patronage-report/create?patronageReportId=${id}" />
		</jstl:when>
	</jstl:choose>

</acme:form>