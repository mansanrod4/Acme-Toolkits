<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.numPatronageProposed"/>
		</th>
		<td>
			<acme:print value="${numPatronageProposed}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.numPatronageAccepted"/>
		</th>
		<td>
			<acme:print value="${numPatronageAccepted}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.numPatronageDenied"/>
		</th>
		<td>
			<acme:print value="${numPatronageDenied}"/>
		</td>
	</tr>	
</table>

<h2>
	<acme:message code="patron.dashboard.form.title.application-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"PROPOSED", "ACCEPTED", "DENIED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${numPatronageProposed}"/>, 
						<jstl:out value="${numPatronageAccepted}"/>, 
						<jstl:out value="${numPatronageDenied}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : "${maximo}"
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<acme:return/>

