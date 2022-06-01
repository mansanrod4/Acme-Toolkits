<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="inventor.chimpum.list.label.code" path="code" width="20%"/> 
 	<acme:list-column code="inventor.chimpum.list.label.title" path="title" width="20%"/>
	<acme:list-column code="inventor.chimpum.list.label.budget" path="budget" width="20%"/>
	
</acme:list>
<%-- <acme:button code="inventor.chimpum.list.button.create" action="/inventor/chimpum/create"/>	 --%>	