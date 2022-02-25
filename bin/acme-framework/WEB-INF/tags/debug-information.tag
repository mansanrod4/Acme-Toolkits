<%--
- debug-information.tag
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@tag language="java" body-content="empty" import="java.util.Enumeration, java.util.Map, java.util.HashMap"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<%@attribute name="full" required="false" type="java.lang.Boolean"%>

<jstl:if test="${full == null}">
	<jstl:set var="full" value="false"/>
</jstl:if>

<jstl:if test="${pageContext.request.serverName == 'localhost' || pageContext.request.serverName == '127.0.0.1' || param.debug != null}">
	<div class="panel mt-5 mb-5" style="word-wrap: break-word; font-family: monospace; background-color: LightGray; padding: 2em; border-radius: 0.25rem;">
		<div class="panel-header">
			<h1>Debug information</h1>
		</div>
		<div class="panel-body mb-3">
			<acme:debug-principal/>						
			<acme:debug-model full="${full}"/>					
		</div>
	</div>
</jstl:if>