<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.money.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="authenticated.money.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
</acme:form>
