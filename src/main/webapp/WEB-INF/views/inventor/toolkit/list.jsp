<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>Your Toolkits</h2>

<acme:list>
	<acme:list-column code="inventor.toolkit.form.label.title" path="title" width="20%"/>
	<acme:list-column code="inventor.toolkit.form.label.description" path="description" width="10%"/>
	<acme:list-column code="inventor.toolkit.form.label.price" path="price" width="20%"/>
</acme:list>