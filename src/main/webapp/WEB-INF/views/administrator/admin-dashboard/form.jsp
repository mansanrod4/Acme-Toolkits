<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h1>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h1>

<%--PATRONAGE DATA --%>

<h3>
	<acme:message code="administrator.dashboard.form.title.patronage-statuses"/>
</h3>

<table class="table table-sm">
	<tr>
		<th><acme:message code="administrator.dashboard.form.title.patronages-type"/></th>
		<th><acme:message code="administrator.dashboard.form.label.num"/></th>
		<th><acme:message code="administrator.dashboard.form.label.avg"/></th>
		<th><acme:message code="administrator.dashboard.form.label.desv"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max"/></th>
	</tr>
	<tr>
		<td style="color:#58D68D"><b><acme:message code="administrator.dashboard.form.title.accepted-patronages"/></b></td>
		<td><acme:print value="${numPatronageAccepted}"/></td>
		<td><acme:print value="${dataAcceptedEUR.average}"/>/ <acme:print value="${dataAcceptedUSD.average}"/>/ <acme:print value="${dataAcceptedGBP.average}"/></td>
		<td><acme:print value="${dataAcceptedEUR.desviation}"/>/ <acme:print value="${dataAcceptedUSD.desviation}"/>/ <acme:print value="${dataAcceptedGBP.desviation}"/></td>
		<td><acme:print value="${dataAcceptedEUR.minimum}"/>/ <acme:print value="${dataAcceptedUSD.minimum}"/>/ <acme:print value="${dataAcceptedGBP.minimum}"/></td>
		<td><acme:print value="${dataAcceptedEUR.maximum}"/>/ <acme:print value="${dataAcceptedUSD.maximum}"/>/ <acme:print value="${dataAcceptedGBP.maximum}"/></td>
	</tr>
	<tr>
		<td style="color:#C0392B"><b><acme:message code="administrator.dashboard.form.title.denied-patronages"/></b></td>
		<td><acme:print value="${numPatronageDenied}"/></td>
		<td><acme:print value="${dataDeniedEUR.average}"/>/ <acme:print value="${dataDeniedUSD.average}"/>/ <acme:print value="${dataDeniedGBP.average}"/></td>
		<td><acme:print value="${dataDeniedEUR.desviation}"/>/ <acme:print value="${dataDeniedUSD.desviation}"/>/ <acme:print value="${dataDeniedGBP.desviation}"/></td>
		<td><acme:print value="${dataDeniedEUR.minimum}"/>/ <acme:print value="${dataDeniedUSD.minimum}"/>/ <acme:print value="${dataDeniedGBP.minimum}"/></td>
		<td><acme:print value="${dataDeniedEUR.maximum}"/>/ <acme:print value="${dataDeniedUSD.maximum}"/>/ <acme:print value="${dataDeniedGBP.maximum}"/></td>
	</tr>
	<tr>
		<td style="color:#616A6B"><b><acme:message code="administrator.dashboard.form.title.pending-patronages"/></b></td>
		<td><acme:print value="${numPatronageRequested}"/></td>
		<td><acme:print value="${dataPendingEUR.average}"/>/ <acme:print value="${dataPendingUSD.average}"/>/ <acme:print value="${dataPendingGBP.average}"/></td>
		<td><acme:print value="${dataPendingEUR.desviation}"/>/ <acme:print value="${dataPendingUSD.desviation}"/>/ <acme:print value="${dataPendingGBP.desviation}"/></td>
		<td><acme:print value="${dataPendingEUR.minimum}"/>/ <acme:print value="${dataPendingUSD.minimum}"/>/ <acme:print value="${dataPendingGBP.minimum}"/></td>
		<td><acme:print value="${dataPendingEUR.maximum}"/>/ <acme:print value="${dataPendingUSD.maximum}"/>/ <acme:print value="${dataPendingGBP.maximum}"/></td>
	</tr>

</table>

<br><br><br>

<%--COMPS AND TOOLS MEANS--%>

<h2>
	<acme:message code="administrator.dashboard.form.title.comps-tools-data"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numComponents"/>
		</th>
		<td>
			<acme:print value="${numComponents}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numTools"/>
		</th>
		<td>
			<acme:print value="${numTools}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.desviationComp"/>
		</th>
		<td>
			<acme:print value="${dataCompEUR.desviation}"/>/
			<acme:print value="${dataCompUSD.desviation}"/>/
			<acme:print value="${dataCompGBP.desviation}"/>			
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.desviationTool"/>
		</th>
		<td>		
			<acme:print value="${dataToolEUR.desviation}"/>/
			<acme:print value="${dataToolUSD.desviation}"/>/
			<acme:print value="${dataToolGBP.desviation}"/>
		</td>
	</tr>
</table>
<br><br>
<h3>
	<acme:message code="administrator.dashboard.form.title.avg-data"/>
</h3>
<br>

<div>
	<canvas id="canvas2"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var color = ["#BD66D4", "#BD66D4","#BD66D4","#BD66D4","#BD66D4","#BD66D4"]
		var data = {
		
			labels : [
					"COMP-EUR","COMP-USD","COMP-GBP","TOOL-EUR", "TOOL-USD","TOOL-GBP"
			],
			datasets : [
				{	
					backgroundColor: color,
					data : [
						<jstl:out value="${dataCompEUR.average.amount}"/>,
						<jstl:out value="${dataCompUSD.average.amount}"/>, 
						<jstl:out value="${dataCompGBP.average.amount}"/>, 
						<jstl:out value="${dataToolEUR.average.amount}"/>,
						<jstl:out value="${dataToolUSD.average.amount}"/>, 
						<jstl:out value="${dataToolGBP.average.amount}"/>,
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
							suggestedMax : 70.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas2, context;
	
		canvas2 = document.getElementById("canvas2");
		context = canvas2.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
<br><br><br>

<%--COMPS MAXS AND MINS --%>

<h3>
	<acme:message code="administrator.dashboard.form.title.min-max-dataComp"/>
</h3>

<div>
	<canvas id="canvas3"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var color = ["#BD66D4", "#BD66D4","#BD66D4","#BD66D4", "#BD66D4","#BD66D4"]
		var data = {
		
			labels : [
					"MIN-EUR-COMP","MAX-EUR-COMP","MIN-USD-COMP", "MAX-USD-COMP","MIN-GBP-COMP","MAX-GBP-COMP"
			],
			datasets : [
				{	
					backgroundColor: color,
					data : [
						<jstl:out value="${dataCompEUR.minimum.amount}"/>,
						<jstl:out value="${dataCompEUR.maximum.amount}"/>,
						<jstl:out value="${dataCompUSD.minimum.amount}"/>,
						<jstl:out value="${dataCompUSD.maximum.amount}"/>, 
						<jstl:out value="${dataCompGBP.minimum.amount}"/>,
						<jstl:out value="${dataCompGBP.maximum.amount}"/>
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
							suggestedMax : 7.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas3, context;
	
		canvas3 = document.getElementById("canvas3");
		context = canvas3.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
<br>

<%--TOOLS MAXS AND MINS --%>

<h3>
	<acme:message code="administrator.dashboard.form.title.min-max-dataTool"/>
</h3>

<div>
	<canvas id="canvas4"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var color = ["#BD66D4", "#BD66D4","#BD66D4","#BD66D4", "#BD66D4","#BD66D4"]
		var data = {
		
			labels : [
				"MIN-EUR-TOOL","MAX-EUR-TOOL","MIN-USD-TOOL", "MAX-USD-TOOL","MIN-GBP-TOOL","MAX-GBP-TOOL"
			],
			datasets : [
				{	
					backgroundColor: color,
					data : [
						<jstl:out value="${dataToolEUR.minimum.amount}"/>,
						<jstl:out value="${dataToolEUR.maximum.amount}"/>,
						<jstl:out value="${dataToolUSD.minimum.amount}"/>, 
						<jstl:out value="${dataToolUSD.maximum.amount}"/>,
						<jstl:out value="${dataToolGBP.minimum.amount}"/>,
						<jstl:out value="${dataToolGBP.maximum.amount}"/>
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
							suggestedMax : 100.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas4, context;
	
		canvas4 = document.getElementById("canvas4");
		context = canvas4.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<h3>
	<acme:message code="administrator.dashboard.form.title.comps-data-tech"/>
</h3>

<table class="table table-sm">
	<tr>
		<th><acme:message code="administrator.dashboard.form.title.tech"/></th>
		<th><acme:message code="administrator.dashboard.form.label.avg"/></th>
		<th><acme:message code="administrator.dashboard.form.label.desv"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max"/></th>
	</tr>
	
	<jstl:forEach items="${componentsDataByTechnology}" var="data">
			<tr>
				<td><acme:print value="${data['key'].getFirst()} (${data['key'].getSecond() })"></acme:print></td>
				<td><acme:print value="${data['value'].average}"></acme:print></td>
				<td><acme:print value="${data['value'].desviation}"></acme:print></td>
				<td><acme:print value="${data['value'].minimum}"></acme:print></td>
				<td><acme:print value="${data['value'].maximum}"></acme:print></td>
			</tr>
			

	</jstl:forEach>

</table>

<h3>
	<acme:message code="administrator.dashboard.form.title.tools-data-tech"/>
</h3>

<table class="table table-sm">
	<tr>
		<th><acme:message code="administrator.dashboard.form.title.tech"/></th>
		<th><acme:message code="administrator.dashboard.form.label.avg"/></th>
		<th><acme:message code="administrator.dashboard.form.label.desv"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max"/></th>
	</tr>
	
	<jstl:forEach items="${toolsDataByTechnology}" var="data">
			<tr>
				<td><acme:print value="${data['key'].getFirst()} (${data['key'].getSecond() })"></acme:print></td>
				<td><acme:print value="${data['value'].average}"></acme:print></td>
				<td><acme:print value="${data['value'].desviation}"></acme:print></td>
				<td><acme:print value="${data['value'].minimum}"></acme:print></td>
				<td><acme:print value="${data['value'].maximum}"></acme:print></td>
			</tr>
			

	</jstl:forEach>

</table>


<acme:return/>