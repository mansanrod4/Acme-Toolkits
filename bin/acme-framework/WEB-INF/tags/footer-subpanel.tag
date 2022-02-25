<%--
- footer-subpanel.tag
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@tag language="java" 
	import="java.util.Collection, java.util.ArrayList, java.util.Map, javax.servlet.jsp.tagext.JspFragment" 
%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<%@attribute name="code" required="true" type="java.lang.String"%>

<div class="col">
	<h6>
		<acme:message code="${code}"/>
	</h6>
	<ul class="list-unstyled">
		<jsp:doBody/>
	</ul>
</div>
