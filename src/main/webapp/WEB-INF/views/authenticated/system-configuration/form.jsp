<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.system-configuration.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	<h2>
	<acme:message code="authenticated.system-configuration.form.title.exchange"/>
	</h2>
	<acme:input-textbox code="authenticated.system-configuration.form.label.USDexchange" path="USDexchange"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.GBPexchange" path="GBPexchange"/>
</acme:form>