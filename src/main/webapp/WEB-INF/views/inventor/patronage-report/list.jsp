<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="inventor.patronage-report.list.label.patronageId" path="patronageId" width="20%"/> 
 	<acme:list-column code="inventor.patronage-report.list.label.memorandum" path="memorandum" width="20%"/>
	<acme:list-column code="inventor.patronage-report.list.label.info" path="info" width="20%"/>
	
</acme:list>

	<acme:submit  code="inventor.patronage-report.list.button.create" action="/inventor/patronage-report/list?patrongeId=${patronageId}"/>		