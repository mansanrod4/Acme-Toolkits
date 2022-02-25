<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.user-account.form.label.username" path="username" readonly="true"/>
	<acme:input-textbox code="administrator.user-account.form.label.name" path="identity.name" readonly="true"/>
	<acme:input-textbox code="administrator.user-account.form.label.surname" path="identity.surname" readonly="true"/>
	<acme:input-email code="administrator.user-account.form.label.email" path="identity.email" readonly="true"/>
	<acme:input-textbox code="administrator.user-account.form.label.role-list" path="roleList" readonly="true"/>
	<acme:input-textbox code="administrator.user-account.form.label.status" path="status" readonly="true"/>
	
	<jstl:if test="${canUpdate}">
		<acme:input-select code="administrator.user-account.form.label.new-status" path="newStatus">
			<acme:input-option code="KEEP" value="KEEP" selected="true"/>
			<acme:input-option code="ENABLED" value="ENABLED"/>
			<acme:input-option code="DISABLED" value="DISABLED"/>		
		</acme:input-select>		
		<acme:submit code="administrator.user-account.form.button.update" action="/administrator/user-account/update"/>
	</jstl:if>
</acme:form>
