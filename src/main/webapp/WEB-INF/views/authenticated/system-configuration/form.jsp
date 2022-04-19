<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.money-exchange.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="authenticated.money-exchange.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	<h2>
	<acme:message code="authenticated.money-exchange.form.title.exchange"/>
	</h2>
	<acme:input-textbox code="authenticated.money-exchange.form.label.USDexchange" path="USDexchange"/>
	<acme:input-textbox code="authenticated.money-exchange.form.label.GBPexchange" path="GBPexchange"/>
</acme:form>