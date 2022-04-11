<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.money.list.label.systemCurrency" path="systemCurrency" width="20%"/>
	<acme:list-column code="authenticated.money.list.label.acceptedCurrencies" path="acceptedCurrencies" width="40%"/>
	<acme:list-column code="authenticated.money.list.label.strongSpamTerms" path="strongSpamTerms" width="10%"/>
	<acme:list-column code="authenticated.money.list.label.strongSpamThreshold" path="strongSpamThreshold" width="10%"/>
	<acme:list-column code="authenticated.money.list.label.weakSpamTerms" path="weakSpamTerms" width="10%"/>
	<acme:list-column code="authenticated.money.list.label.weakSpamThreshold" path="weakSpamThreshold" width="10%"/>
</acme:list>