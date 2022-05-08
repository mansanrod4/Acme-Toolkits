
package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemToolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorItemDeleteService implements AbstractDeleteService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;


	@Override
	public boolean authorise(final Request<Item> request) {

		assert request != null;
		boolean res;
		int inventorId;
		int id;
		Item item;
		Inventor inventor;

		inventorId = request.getPrincipal().getActiveRoleId();
		id = request.getModel().getInteger("id");
		item = this.repository.findOneItemByIdFromInventor(id, inventorId);
		inventor = item.getInventor();

		res = item.isDraftMode() && request.isPrincipal(inventor);

		return res;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "itemType", "name", "technology", "description", "retailPrice", "info");

	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

//		List<Money> prices;
//		prices = new ArrayList<>();
//
//		final SystemConfiguration sc = this.repository.findSystemConfiguration();
//		for (final String curr : sc.getAcceptedCurrencies().trim().split(",")) {
//			final Money price = new Money();
//			price.setCurrency(curr);
//			price.setAmount(this.repository.getItemPricesByIdAndCurrency(entity.getId(), curr));
//			prices.add(price);
//		}
//
//		MoneyExchange moneyExchange;
//		List<Money> pricesFix;
//
//		moneyExchange = new MoneyExchange();
//		pricesFix = moneyExchange.convertMoney(prices, sc.getSystemCurrency());
//
//		Money money;
//		Double amount;
//		money = new Money();
//		amount = pricesFix.stream().mapToDouble(Money::getAmount).sum();

		request.unbind(entity, model, "code", "itemType", "name", "technology", "description", "info", "draftMode");
//		money.setAmount(amount);
//		money.setCurrency(sc.getSystemCurrency());
//
//		model.setAttribute("retailPrice", money);
//		model.setAttribute("inventor", entity.getInventor().getIdentity().getFullName());
//		model.setAttribute("draftMode", entity.isDraftMode());

	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		int id;
		Item result;
		id = request.getModel().getInteger("id");
		final int inventorId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findOneItemByIdFromInventor(id, inventorId);

		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		final Collection<ItemToolkit> toolkitContent = this.repository.findItemToolkitByItemId(entity.getId());
		for (final ItemToolkit itemtoolkit : toolkitContent) {
			this.repository.delete(itemtoolkit);
		}

		this.repository.delete(entity);
	}

}
