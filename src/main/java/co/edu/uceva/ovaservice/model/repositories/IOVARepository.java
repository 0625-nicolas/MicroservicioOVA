package co.edu.uceva.ovaservice.model.repositories;

import co.edu.uceva.ovaservice.model.entities.OVA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOVARepository extends JpaRepository<OVA, Long> {
}
