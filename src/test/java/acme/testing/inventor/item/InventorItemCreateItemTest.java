package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemCreateItemTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/create-component-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveComponentTest(final int recordIndex,final String code, final String name, final String technology,final String description, final String price, final String info) {
		
		super.signIn("inventor3", "inventor3");
		
		super.clickOnMenu("Inventor", "My Components");
		super.checkListingExists();
		
		this.checkCreateItem(code, name, technology, description, price, info);
		
		this.checkListAllItemsNShow(recordIndex, code, name, technology, price, description, info);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/create-tool-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolTest(final int recordIndex,final String code, final String name, final String technology,final String description, final String price, final String info) {
		super.signIn("inventor3", "inventor3");
		
		super.clickOnMenu("Inventor", "My Tools");
		super.checkListingExists();
		this.checkCreateItem(code, name, technology, description, price, info);
		
		this.checkListAllItemsNShow(recordIndex, code, name, technology, price, description, info);
		super.signOut();
		
	}
	
	protected void checkCreateItem(final String code, final String name, final String technology, final String description, final String price, final String info) {
		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", price);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Create");
	}
	
	protected void checkListAllItemsNShow(final int recordIndex, final String code, final String name, final String technology, final String retailPrice, final String description, final String info) {
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("info", info);
	}
	
}
