package co.edu.uceva.ovaservice.model.repositories;

import co.edu.uceva.ovaservice.model.entities.OVA;
import org.springframework.data.repository.CrudRepository;

public interface IOVARepository extends CrudRepository<OVA, Long> {
}
