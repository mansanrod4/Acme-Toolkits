package acme.features.authenticated.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnnouncementRepository extends AbstractRepository{
	
	@Query("select a from Announcement a where a.moment >= :deadline")
	Collection<Announcement> findRecentAnnouncements(Date deadline);


}