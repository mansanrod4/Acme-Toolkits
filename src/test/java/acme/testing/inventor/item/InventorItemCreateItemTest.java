package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemCreateItemTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/create-component-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveComponentTest(final int recordIndex,final String code, final String name, final String technology, final String price, final String info) {
		
		super.signIn("inventor3", "inventor3");
		
		super.clickOnMenu("Inventor", "My Components");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("Code", code);
		super.fillInputBoxIn("Name", name);
		super.fillInputBoxIn("Technology", technology);
		super.fillInputBoxIn("Description", technology);
		super.fillInputBoxIn("Price", price);
		super.fillInputBoxIn("Further Information", info);
		
	}
	
}
