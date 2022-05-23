package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemListAllnShowTest extends TestHarness{
	
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
		this.checkListAllItemsNShow(recordIndex, code, name, technology, retailPrice, description, info);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/list-all-tools-full.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveInventorHasToolsTest(final int recordIndex, final String username, final String password,
		final String name, final String code, final String technology, final String description, final String retailPrice, final String info) {
		
		super.signIn(username, password);
		super.navigateHome();
		super.clickOnMenu("Inventor", "My Tools");
		this.checkListAllItemsNShow(recordIndex, code, name, technology, retailPrice, description, info);
		
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.signIn("inventor3", "inventor3");
		super.clickOnMenu("Inventor", "My Components");
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.clickOnListingRecord(1);
		final String pathShowedComponent = super.getCurrentPath();
		final String queryComponent = super.getCurrentQuery();
		
		
		super.clickOnMenu("Inventor", "My Tools");
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.clickOnListingRecord(1);
		final String pathShowedTool = super.getCurrentPath();
		final String queryShowedTool = super.getCurrentQuery();
		
		
		super.signOut();
		super.checkNotLinkExists("Inventor");
		this.checkPanicWhenNavigatingToPaths(pathShowedComponent, pathShowedTool);
		
		super.signIn("administrator", "administrator");
		super.checkNotLinkExists("Inventor");
		this.checkPanicWhenNavigatingToPaths(pathShowedComponent, pathShowedTool);
		super.signOut();
		
		super.signIn("patron1", "patron1");
		super.checkNotLinkExists("Inventor");
		this.checkPanicWhenNavigatingToPaths(pathShowedComponent, pathShowedTool);
		super.signOut();
		
		super.signIn("inventor1", "inventor1");
		super.checkLinkExists("Inventor");
		super.navigate(pathShowedComponent, queryComponent);
		super.checkPanicExists();
		super.navigate(pathShowedTool, queryShowedTool);
		super.checkPanicExists();
		super.signOut();
		
	}
	
	protected void checkPanicWhenNavigatingToPaths(final String pathShowedComponent, final String pathShowedTool) {
		super.navigate(pathShowedComponent);
		super.checkPanicExists();
		
		super.navigate(pathShowedTool);
		super.checkPanicExists();
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
