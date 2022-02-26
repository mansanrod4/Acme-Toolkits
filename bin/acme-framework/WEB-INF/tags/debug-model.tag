<%--
- debug-model.tag
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@tag language="java" body-content="empty"
  		import="java.util.Enumeration, java.util.SortedMap,	java.util.TreeMap, java.lang.StringBuilder,
			acme.framework.helpers.PrinterHelper, acme.framework.components.models.ModelKeyComparator"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<%@attribute name="full" required="false" type="java.lang.Boolean"%>
<%@attribute name="scope" required="false" type="java.lang.Integer"%>

<jstl:if test="${full == null}">
	<jstl:set var="full" value="${false}"/>
</jstl:if>

<jstl:if test="${scope == null}">
	<%-- PAGE_SCOPE = 1, REQUEST_SCOPE = 2, SESSION_SCOPE = 3, APPLICATION_SCOPE = 4 --%>
	<jstl:set var="scope" value="${2}"/>
</jstl:if>

<%
	ModelKeyComparator comparator;
	SortedMap<String, Object[]> model, internalModel, globalModel, indexedModel;
	Enumeration<?> keys;
	boolean full;
	int scope;
	String include, exclude;
	
	comparator = new ModelKeyComparator();
	internalModel = new TreeMap<String, Object[]>(comparator);
	globalModel = new TreeMap<String, Object[]>(comparator);
	indexedModel = new TreeMap<String, Object[]>(comparator);
	
	full = (boolean)jspContext.getAttribute("full");
	scope = ((Long)jspContext.getAttribute("scope")).intValue();
	if (full) {
		include = "^.*$";
		exclude = "^<\\*\\*\\*>$"; // INFO: very unlikely pattern		
	} else {
		include = "^\\w+|\\w+\\[\\d+\\]|\\w+\\.\\w+|\\w+\\.\\w+\\[\\d+\\]$";
		exclude = "^.*(__|\\.[A-Z][A-Z]|[sS][pP][rR][iI][nN][gG]).*$";		
	}
	
	keys = jspContext.getAttributeNamesInScope(scope);
	while (keys.hasMoreElements()) {
		String name;
		Object value;
		StringBuilder buffer;
		boolean selected;
		Object[] pair;
	
		name = (String) keys.nextElement();
		value = jspContext.getAttribute(name, scope);
		selected = (name.matches(include) && !name.matches(exclude));
		if (selected) {
			buffer = new StringBuilder();			
			PrinterHelper.printValue(buffer, value, false);
			pair = new Object[] { buffer.toString(), value.getClass().getName() };
			if (name.contains("$"))
				internalModel.put(name, pair);
			else if (!name.contains("["))
				globalModel.put(name, pair);
			else
				indexedModel.put(name, pair);
		}
	}
	jspContext.setAttribute("internalModel", internalModel);
	jspContext.setAttribute("globalModel", globalModel);
	jspContext.setAttribute("indexedModel", indexedModel);
%>

<div class="alert alert-info" style="font-family: monospace;">
	<h2>Model variables</h2>
	<dl>
		<jstl:forEach var="entry" items="${internalModel.entrySet()}">
			<dt><jstl:out value="${entry.key}: ${entry.value[1]}"/></dt>
			<dd	style="word-wrap: break-word; white-space: pre-wrap; margin-left: 1em;"><jstl:out value="${entry.value[0]}"/></dd>
		</jstl:forEach>
		<jstl:forEach var="entry" items="${globalModel.entrySet()}">
			<dt><jstl:out value="${entry.key}: ${entry.value[1]}"/></dt>
			<dd style="word-wrap: break-word; white-space: pre-wrap; margin-left: 1em;"><jstl:out value="${entry.value[0]}"/></dd>
		</jstl:forEach>
		<jstl:forEach var="entry" items="${indexedModel.entrySet()}">
			<dt><jstl:out value="${entry.key}: ${entry.value[1]}"/></dt>
			<dd style="word-wrap: break-word; white-space: pre-wrap; margin-left: 1em;"><jstl:out value="${entry.value[0]}"/></dd>
		</jstl:forEach>
	</dl>
</div>

