package acme.testing.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class InventorItemListAllnShowTest extends TestItem{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/list-all-components-empty.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveInventorHasNoComponentsTest(final int recordIndex, final String username, final String password) {
		
		super.signIn(username, password);
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Components");
		
		super.checkListingExists();
		super.checkListingEmpty();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/list-all-tools-empty.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveInventorHasNoToolsTest(final int recordIndex, final String username, final String password) {
		
		super.signIn(username, password);
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Tools");
		
		super.checkListingExists();
		super.checkListingEmpty();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/list-all-components-full.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveInventorHasComponentsTest(final int recordIndex, final String username, final String password,
		final String name, final String code, final String technology, final String description, final String retailPrice, final String info) {
		
		super.signIn(username, password);
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Components");
		super.checkListAllItemsNShow(recordIndex, code, name, technology, retailPrice, description, info);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/list-all-tools-full.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveInventorHasToolsTest(final int recordIndex, final String username, final String password,
		final String name, final String code, final String technology, final String description, final String retailPrice, final String info) {
		
		super.signIn(username, password);
		super.navigateHome();
		super.clickOnMenu("Inventor", "My Tools");
		super.checkListAllItemsNShow(recordIndex, code, name, technology, retailPrice, description, info);
		
	}

}
