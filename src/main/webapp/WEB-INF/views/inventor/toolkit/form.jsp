<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="inventor.toolkit.form.label.assemblyNotes" path="assemmblyNotes"/>
	<acme:input-money code="inventor.toolkit.form.label.price" path="price"/>
	<acme:input-url code="inventor.toolkit.form.label.info" path="info"/>
</acme:form>

<acme:list>
	<acme:list-column code="inventor.toolkit.form.label.code" path="code" width="20%"/>
	<acme:list-column code="inventor.toolkit.form.label.name" path="name" width="10%"/>
	<acme:list-column code="inventor.toolkit.form.label.technology" path="technology" width="20%"/>
	<acme:list-column code="inventor.toolkit.form.label.retailPrice" path="retailPrice" width="20%"/>
</acme:list>
