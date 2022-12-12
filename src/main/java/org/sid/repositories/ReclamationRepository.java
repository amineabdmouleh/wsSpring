package org.sid.repositories;
import java.util.List;

import org.sid.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
	List<Reclamation> findBytraité(boolean traité);
	List<Reclamation> findByTitleContaining(String title);

}
