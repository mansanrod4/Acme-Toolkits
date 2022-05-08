
package acme.features.administrator.dashboard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.datatypes.StatData;
import acme.entities.patronages.PatronageStatus;
import acme.entities.toolkits.ItemType;
import acme.forms.AdminDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdminDashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<AdminDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdminDashboard findOne(final Request<AdminDashboard> request) {
		assert request != null;
		final AdminDashboard result;

		Integer numComponents;
		Integer numTools;
		Integer numPatronageRequested;
		Integer numPatronageAccepted;
		Integer numPatronageDenied;

		numComponents = this.repository.getNumComps();
		numTools = this.repository.getNumTools();
		numPatronageRequested = this.repository.getNumPatronage(PatronageStatus.PROPOSED);
		numPatronageAccepted = this.repository.getNumPatronage(PatronageStatus.ACCEPTED);
		numPatronageDenied = this.repository.getNumPatronage(PatronageStatus.DENIED);

		final Map<String, StatData> componentsDataByCurrency = new HashMap<>();
		final Map<Pair<String, String>, StatData> componentsDataByTechnology = new HashMap<>();
		final Map<String, StatData> toolsDataByCurrency = new HashMap<>();
		final Map<Pair<String, String>, StatData> toolsDataByTechnology = new HashMap<>();
		final Map<Pair<PatronageStatus, String>, StatData> patronageBudgetData = new HashMap<>();

		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		final List<String> acceptedCurrencies = Arrays.asList(sc.getAcceptedCurrencies().trim().split(","));

		//---------Components and Tools Data By Currency--------------

		acceptedCurrencies.forEach(x -> componentsDataByCurrency.put(x, StatData.of(this.repository.getItemDataByCurr(ItemType.COMPONENT, x), x)));
		acceptedCurrencies.forEach(x -> toolsDataByCurrency.put(x, StatData.of(this.repository.getItemDataByCurr(ItemType.TOOL, x), x)));

		//---------Components and Tools Data By Currency and Technology--------------

		final List<String> technologiesAvaliables = this.repository.getTechnologies();
		for (final String technology : technologiesAvaliables) {
			acceptedCurrencies.forEach(x -> componentsDataByTechnology.put(Pair.of(technology, x), StatData.of(this.repository.getItemDataByTechnologyAndCurrency(ItemType.COMPONENT, technology, x), x)));
		}

		for (final String technology : technologiesAvaliables) {
			acceptedCurrencies.forEach(x -> toolsDataByTechnology.put(Pair.of(technology, x), StatData.of(this.repository.getItemDataByTechnologyAndCurrency(ItemType.TOOL, technology, x), x)));
		}

		//---------Patronage Data By Status and Currency-----------------------

		for (final PatronageStatus status : PatronageStatus.values()) {
			acceptedCurrencies.forEach(x -> patronageBudgetData.put(Pair.of(status, x), StatData.of(this.repository.getPatronageBudgetDataByStatusAndCurrency(status, x), x)));
		}

		result = new AdminDashboard();

		result.setNumComponents(numComponents);
		result.setNumTools(numTools);
		result.setNumPatronageRequested(numPatronageRequested);
		result.setNumPatronageAccepted(numPatronageAccepted);
		result.setNumPatronageDenied(numPatronageDenied);
		result.setComponentsDataByCurrency(componentsDataByCurrency);
		result.setComponentsDataByTechnology(componentsDataByTechnology);
		result.setToolsDataByTechnology(toolsDataByTechnology);
		result.setToolsDataByCurrency(toolsDataByCurrency);
		result.setPatronageBudgetData(patronageBudgetData);

		return result;
	}

	@Override
	public void unbind(final Request<AdminDashboard> request, final AdminDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numComponents", "numTools", "numPatronageRequested", "numPatronageAccepted", "numPatronageDenied", "componentsDataByTechnology", "toolsDataByTechnology");
		model.setAttribute("dataCompEUR", entity.getComponentsDataByCurrency().get("EUR"));
		model.setAttribute("dataCompUSD", entity.getComponentsDataByCurrency().get("USD"));
		model.setAttribute("dataCompGBP", entity.getComponentsDataByCurrency().get("GBP"));

		model.setAttribute("dataToolEUR", entity.getToolsDataByCurrency().get("EUR"));
		model.setAttribute("dataToolUSD", entity.getToolsDataByCurrency().get("USD"));
		model.setAttribute("dataToolGBP", entity.getToolsDataByCurrency().get("GBP"));

		model.setAttribute("dataAcceptedEUR", entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.ACCEPTED, "EUR")));
		model.setAttribute("dataAcceptedUSD", entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.ACCEPTED, "USD")));
		model.setAttribute("dataAcceptedGBP", entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.ACCEPTED, "GBP")));
		model.setAttribute("dataPendingEUR", entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.PROPOSED, "EUR")));
		model.setAttribute("dataPendingUSD", entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.PROPOSED, "USD")));
		model.setAttribute("dataPendingGBP", entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.PROPOSED, "GBP")));
		model.setAttribute("dataDeniedEUR", entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.DENIED, "EUR")));
		model.setAttribute("dataDeniedUSD", entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.DENIED, "USD")));
		model.setAttribute("dataDeniedGBP", entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.DENIED, "GBP")));
	}

}
