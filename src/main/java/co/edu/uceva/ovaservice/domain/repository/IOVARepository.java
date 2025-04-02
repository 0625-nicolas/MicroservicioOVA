package co.edu.uceva.ovaservice.domain.repository;

import co.edu.uceva.ovaservice.domain.model.OVA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOVARepository extends JpaRepository<OVA, Long> {
}
