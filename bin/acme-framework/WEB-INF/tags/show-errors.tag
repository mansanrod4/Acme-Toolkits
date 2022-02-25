<%--
- show-errors.tag
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@tag language="java" body-content="empty"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<%@attribute name="path" required="true" type="java.lang.String"%>

<jstl:set var="error_path" value="${path}$error"/>
<jstl:set var="error" value="${requestScope[error_path]}"/>

<jstl:if test="${error != null}">
	<jstl:if test="${path == '*'}">
		<acme:alert-error>
			<jstl:out value="${error}"/>
		</acme:alert-error>
	</jstl:if>
	<jstl:if test="${path != '*'}">
		<div class="text-danger">
			<jstl:out value="${error}"/>
		</div>
	</jstl:if>
</jstl:if>
