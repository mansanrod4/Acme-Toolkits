
package acme.features.authenticated.userAccount;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.entities.UserAccountStatus;
import acme.framework.roles.Authenticated;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedUserAccountListInventorService implements AbstractListService<Authenticated, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedUserAccountRepository2 repository;

	// AbstractListService<Inventor, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<UserAccount> request) {

		assert request != null;

		return true;
	}

	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;

		Collection<UserAccount> result;
		result = this.repository.findAllUserAccounts();
		for (final UserAccount userAccount : result) {
			userAccount.getRoles().forEach(r -> {
				r.getAuthorityName().equals("Inventor");
			});
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
