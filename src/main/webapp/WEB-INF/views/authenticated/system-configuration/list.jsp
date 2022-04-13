<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.systemConfiguration.list.label.systemCurrency" path="systemCurrency" width="40%"/>
	<acme:list-column code="authenticated.systemConfiguration.list.label.acceptedCurrencies" path="acceptedCurrencies" width="60%"/>
</acme:list>