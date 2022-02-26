<%--
- page.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" 
	contentType="text/html; charset=utf-8" 
	trimDirectiveWhitespaces="true"
	import="org.springframework.context.ApplicationContext, 
			org.springframework.web.servlet.support.RequestContextUtils,
			acme.framework.helpers.JspHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<jstl:set var="locale" value="${pageContext.response.locale.language}"/>

<!DOCTYPE html>
<html lang="${locale}">
	<head>
		<base href="<%=JspHelper.getBaseUrl(request)%>"/>
				
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=yes">
		
		<link rel="shortcut icon" href="images/favicon.ico"/>
		
		<link rel="stylesheet" href="libraries/fontawesome/5.2.0/css/all.min.css"/>
		<link rel="stylesheet" href="libraries/bootstrap/4.1.3/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="libraries/datatables/1.10.18/css/datatables.min.css"/>
		
		<script type="text/javascript" src="libraries/jquery/3.3.1/js/jquery.min.js"></script>
	 	<script type="text/javascript" src="libraries/popper.js/1.14.4/js/popper.min.js"></script>
		<script type="text/javascript" src="libraries/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="libraries/datatables/1.10.18/js/jquery.datatables.min.js"></script>
	 	<script type="text/javascript" src="libraries/datatables/1.10.18/js/datatables.min.js"></script> 
		<script type="text/javascript" src="libraries/chart.js/2.7.2/js/chart.bundle.min.js"></script>
	 	<script type="text/javascript" src="libraries/areyousure/1.9.0/js/areyousure.min.js"></script>
	 		
		<!-- Custom libraries and styles -->
		
		<link rel="stylesheet" href="libraries/acme/css/acme.css"/>
		<script type="text/javascript" src="libraries/acme/js/acme.js"></script>
		
		<%@include file="../fragments/custom-styles.jsp"%>
		<%@include file="../fragments/custom-scripts.jsp"%>
		
		<title><%@include file="../fragments/title.jsp"%></title>
	</head>
	
	<body class="container-fluid mt-3">
		<%@include file="../fragments/banner.jsp"%>
		<%@include file="../fragments/menu.jsp"%>
		<div class="panel mt-5 mb-5">
			<div class="panel-body">
				<jstl:if test="${globalSuccessMessage != null}">
					<acme:alert-success>
						<acme:message code="${globalSuccessMessage}"/>
					</acme:alert-success>
				</jstl:if>
				<jstl:if test="${globalErrorMessage != null}">
					<acme:alert-error>
						<acme:message code="${globalErrorMessage}"/>
					</acme:alert-error>
				</jstl:if>				
				<tiles:insertAttribute name="body"/>
				<div style="clear: both"></div>
			</div>
		</div>
		<%@include file="../fragments/footer.jsp"%>
		<acme:debug-information full="false"/>		
	</body>
</html>

