<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.dashboard.form.title"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.number-of-patronages-by-status"/>
		</th>
		<td>
			<div>
				<canvas id="patronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.average-number-of-budgets-by-currency-and-status"/>
		</th>
		<td>
			<div>
				<canvas id="averageNumberOfBudgetsByCurrencyAndStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.deviation-of-budgets-by-currency-and-status"/>
		</th>
		<td>
			<div>
				<canvas id="deviationOfBudgetsByCurrencyAndStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.min-budget-by-currency-and-status"/>
		</th>
		<td>
			<div>
				<canvas id="minBudgetByCurrencyAndStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.max-budget-by-currency-and-status"/>
		</th>
		<td>
			<div>
				<canvas id="maxBudgetByCurrencyAndStatus"></canvas>
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
	let ACCEPTEDEUR = "ACCEPTED <-> EUR";
	let ACCEPTEDGBP = "ACCEPTED <-> GBP";
	let ACCEPTEDUSD = "ACCEPTED <-> USD";
	let DENIEDEUR = "DENIED <-> EUR";
	let DENIEDGBP = "DENIED <-> GBP";
	let DENIEDUSD = "DENIED <-> USD";
	let PROPOSEDEUR = "PROPOSED <-> EUR";
	let PROPOSEDGBP = "PROPOSED <-> GBP";
	let PROPOSEDUSD = "PROPOSED <-> USD";
	
	function createChart(labelList, dataList, id){
		var barColors = ["#2F4F4F", "#008080","#3CB371","#11cf43","#004d14", "#769146", "#a5ad97", "#b1f046", "#53574c"];
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
							suggestedMax : 1.0
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
		<jstl:forEach items="${numberOfPatronagesByStatus}" var="numberOfPatronagesByStatus">
			<acme:print value="${numberOfPatronagesByStatus['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data1 = [
		<jstl:forEach items="${numberOfPatronagesByStatus}" var="numberOfPatronagesByStatus">
			<acme:print value="${numberOfPatronagesByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels1, data1, "patronagesByStatus");
	
	//AVERAGE_NUMBER_OF_BUDGETS_BY_CURRENCY_AND_STATUS
	let labels2 = [
		<jstl:forEach items="${averageNumberOfBudgetsByCurrencyAndStatus}" var="averageNumberOfBudgetsByCurrencyAndStatus">
			<acme:print value="${averageNumberOfBudgetsByCurrencyAndStatus['key'].getFirst()}${averageNumberOfBudgetsByCurrencyAndStatus['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data2 = [
		<jstl:forEach items="${averageNumberOfBudgetsByCurrencyAndStatus}" var="averageNumberOfBudgetsByCurrencyAndStatus">
			<acme:print value="${averageNumberOfBudgetsByCurrencyAndStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels2, data2, "averageNumberOfBudgetsByCurrencyAndStatus");
	
	//DESVIATION_OF_BUDGETS_BY_CURRENCY_AND_STATUS
	let labels3 = [
		<jstl:forEach items="${deviationOfBudgetsByCurrencyAndStatus}" var="deviationOfBudgetsByCurrencyAndStatus">
			<acme:print value="${deviationOfBudgetsByCurrencyAndStatus['key'].getFirst()}${deviationOfBudgetsByCurrencyAndStatus['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data3 = [
		<jstl:forEach items="${deviationOfBudgetsByCurrencyAndStatus}" var="deviationOfBudgetsByCurrencyAndStatus">
			<acme:print value="${deviationOfBudgetsByCurrencyAndStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels3, data3, "deviationOfBudgetsByCurrencyAndStatus");
	
	//MIN_BUDGET_BY_CURRENCY_AND_STATUS
	let labels4 = [
		<jstl:forEach items="${minBudgetByCurrencyAndStatus}" var="minBudgetByCurrencyAndStatus">
			<acme:print value="${minBudgetByCurrencyAndStatus['key'].getFirst()}${minBudgetByCurrencyAndStatus['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data4 = [
		<jstl:forEach items="${minBudgetByCurrencyAndStatus}" var="minBudgetByCurrencyAndStatus">
			<acme:print value="${minBudgetByCurrencyAndStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels4, data4, "minBudgetByCurrencyAndStatus");
	
	//MAX_BUDGET_BY_CURRENCY_AND_STATUS
	let labels5 = [
		<jstl:forEach items="${maxBudgetByCurrencyAndStatus}" var="maxBudgetByCurrencyAndStatus">
			<acme:print value="${maxBudgetByCurrencyAndStatus['key'].getFirst()}${maxBudgetByCurrencyAndStatus['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data5 = [
		<jstl:forEach items="${maxBudgetByCurrencyAndStatus}" var="maxBudgetByCurrencyAndStatus">
			<acme:print value="${maxBudgetByCurrencyAndStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels5, data5, "maxBudgetByCurrencyAndStatus");
	
	});
</script>

<acme:return/>