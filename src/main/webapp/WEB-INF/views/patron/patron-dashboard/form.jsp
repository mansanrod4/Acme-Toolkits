<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

	<h2>
		<acme:message code="patron.dashboard.form.title.dashboard"/>
	</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.num-patronages-by-status"/>
		</th>
		<td>
			<div>
				<canvas id="numPatronages"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.average-budgets-by-status"/>
		</th>
		<td>
			<div>
				<canvas id="averageBudgets"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.deviation-budgets-by-status"/>
		</th>
		<td>
			<div>
				<canvas id="deviationBudgets"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.min-budget-by-status"/>
		</th>
		<td>
			<div>
				<canvas id="minBudget"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.max-budget-by-status"/>
		</th>
		<td>
			<div>
				<canvas id="maxBudget"></canvas>
			</div>
		</td>
	</tr>
</table>

<script type="text/javascript">
$(document).ready(function() {
	//LABELS
	let ACCEPTED = "ACCEPTED";
	let DENIED = "DENIED";
	let PROPOSED = "PROPOSED";
	let ACCEPTEDEUR = "ACCEPTED (EUR)";
	let ACCEPTEDGBP = "ACCEPTED (GBP)";
	let ACCEPTEDUSD = "ACCEPTED (USD)";
	let DENIEDEUR = "DENIED (EUR)";
	let DENIEDGBP = "DENIED (GBP)";
	let DENIEDUSD = "DENIED (USD)";
	let PROPOSEDEUR = "PROPOSED (EUR)";
	let PROPOSEDGBP = "PROPOSED (GBP)";
	let PROPOSEDUSD = "PROPOSED (USD)";
	
	function createChart(labelList, dataList, id){
		/* var barColors = [""]; */
		var data = {
			labels : labelList,
			datasets : [
				{
					backgroundColor: barColors,
					data : dataList
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 10.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
		var canvas, context;
		canvas = document.getElementById(id);
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
		   
	}
	
	//PATRONAGES_BY_STATUS
	let labels1 = [
		<jstl:forEach items="${numPatronagesByStatus}" var="numPatronagesByStatus">
			<acme:print value="${numPatronagesByStatus['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data1 = [
		<jstl:forEach items="${numPatronagesByStatus}" var="numPatronagesByStatus">
			<acme:print value="${numPatronagesByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels1, data1, "numPatronages");
	
	//AVERAGE_NUMBER_OF_BUDGETS_BY_CURRENCY_AND_STATUS
	let labels2 = [
		<jstl:forEach items="${averageBudgetsByStatus}" var="averageBudgetsByStatus">
			<acme:print value="${averageBudgetsByStatus['key'].getFirst()}${averageBudgetsByStatus['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data2 = [
		<jstl:forEach items="${averageBudgetsByStatus}" var="averageBudgetsByStatus">
			<acme:print value="${averageBudgetsByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels2, data2, "averageBudgets");
	
	//DESVIATION_OF_BUDGETS_BY_CURRENCY_AND_STATUS
	let labels3 = [
		<jstl:forEach items="${deviationBudgetsByStatus}" var="deviationBudgetsByStatus">
			<acme:print value="${deviationBudgetsByStatus['key'].getFirst()}${deviationBudgetsByStatus['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data3 = [
		<jstl:forEach items="${deviationBudgetsByStatus}" var="deviationBudgetsByStatus">
			<acme:print value="${deviationBudgetsByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels3, data3, "deviationBudgets");
	
	//MIN_BUDGET_BY_CURRENCY_AND_STATUS
	let labels4 = [
		<jstl:forEach items="${minBudgetByStatus}" var="minBudgetByStatus">
			<acme:print value="${minBudgetByStatus['key'].getFirst()}${minBudgetByStatus['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data4 = [
		<jstl:forEach items="${minBudgetByStatus}" var="minBudgetByStatus">
			<acme:print value="${minBudgetByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels4, data4, "minBudget");
	
	//MAX_BUDGET_BY_CURRENCY_AND_STATUS
	let labels5 = [
		<jstl:forEach items="${maxBudgetByStatus}" var="maxBudgetByStatus">
			<acme:print value="${maxBudgetByStatus['key'].getFirst()}${maxBudgetByStatus['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data5 = [
		<jstl:forEach items="${maxBudgetByStatus}" var="maxBudgetByStatus">
			<acme:print value="${maxBudgetByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels5, data5, "maxBudget");
	
	});
</script>

<acme:return/>