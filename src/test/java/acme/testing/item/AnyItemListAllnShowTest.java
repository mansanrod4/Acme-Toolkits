
package acme.testing.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AnyItemListAllnShowTest extends TestItem {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/list-all-components.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveComponentTest(final int recordIndex, final String itemType, final String name, final String code, final String technology, final String description, final String retailPrice, final String inventor, final String info) {
		super.navigateHome();
		super.clickOnMenu("Components and tools", "Components");

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/list-all-tools.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolTest(final int recordIndex, final String itemType, final String name, final String code, final String technology, final String description, final String retailPrice, final String inventor, final String info) {
		super.navigateHome();

		super.clickOnMenu("Components and tools", "Tools");

		this.checkListAllItemsNShow(recordIndex, code, name, technology, retailPrice, description, info);
	}


}
