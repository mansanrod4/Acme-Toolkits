
package acme.features.any.userAccount;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.entities.UserAccountStatus;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountListInventorService implements AbstractListService<Any, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyUserAccountRepository repository;

	// AbstractListService<Inventor, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<UserAccount> request) {

		assert request != null;

		return true;
	}

	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;

		final Collection<UserAccount> result = new ArrayList<UserAccount>();
		final Collection<UserAccount> allUserAccounts = this.repository.findAllUserAccounts();
		for (final UserAccount userAccount : allUserAccounts) {
			final String authorityString = userAccount.getAuthorityString();
			if (!(authorityString.contains("Administrator")||authorityString.contains("Anonymous"))&&authorityString.contains("Inventor"))
				result.add(userAccount);
		}
		return result;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final StringBuilder buffer;
		final Collection<UserRole> roles;

		request.unbind(entity, model, "username", "identity.name", "identity.surname", "identity.email");

		roles = entity.getRoles();
		buffer = new StringBuilder();
		for (final UserRole role : roles) {
			buffer.append(role.getAuthorityName());
			buffer.append(" ");
		}

		model.setAttribute("roleList", buffer.toString());

		if (entity.isEnabled()) {
			model.setAttribute("status", UserAccountStatus.ENABLED);
		} else {
			model.setAttribute("status", UserAccountStatus.DISABLED);
		}

	}

}
