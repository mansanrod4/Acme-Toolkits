
package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Item;
import acme.features.inventor.item.component.InventorComponentListAllService;
import acme.features.inventor.item.tool.InventorToolListAllService;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item> {

	@Autowired
	protected InventorComponentListAllService	inventorComponentListAllService;

	@Autowired
	protected InventorToolListAllService		inventorToolListAllService;

	@Autowired
	protected InventorItemShowService			inventorItemShowService;
	
	@Autowired
	protected InventorItemDeleteService 		inventorItemDeleteService;
	
	@Autowired
	protected InventorItemPublishService 		inventorItemPublishService;


	@PostConstruct
	protected void initialize() {
		super.addCommand("list-component", "list", this.inventorComponentListAllService);
		super.addCommand("list-tool", "list", this.inventorToolListAllService);
		super.addCommand("show", this.inventorItemShowService);
		super.addCommand("delete", this.inventorItemDeleteService);
		super.addCommand("publish", this.inventorItemPublishService);

		

	}

}
