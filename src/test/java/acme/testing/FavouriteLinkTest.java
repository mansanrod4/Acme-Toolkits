/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class FavouriteLinkTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void favouriteLink1() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "49236025V: Sánchez Rodríguez, Manuel");		
		super.checkCurrentUrl("https://defonic.com");
	}
	@Test
	@Order(10)
	public void favouriteLink2() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "20060486R: Díaz López, Diego Jesús");		
		super.checkCurrentUrl("https://www.sanfransentinel.com/youtube998.html");
	}
	
	@Test
	@Order(10)
	public void favouriteLink4() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "77866123Z: Fernández Rodríguez, Manuel");		
		super.checkCurrentUrl("http://www.gol.gg");
	}
	@Test
	@Order(10)
	public void favouriteLink5() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "77976716T: Qazza Cevallos, Aisha Doris");		
		super.checkCurrentUrl("https://www.netflix.com/es-en/title/70136120?tctx=-97%2C-97%2C%2C%2C%2C%2C%2C&trackId=14277283");
	}
	@Test
	@Order(10)
	public void favouriteLink6() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "47538886W: Sánchez Rodríguez, Oliva");		
		super.checkCurrentUrl("https://www.google.com");
	}
	// Ancillary methods ------------------------------------------------------ 
}
