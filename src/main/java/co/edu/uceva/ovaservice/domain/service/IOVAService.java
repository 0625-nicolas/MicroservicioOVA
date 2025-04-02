package co.edu.uceva.ovaservice.domain.service;

import co.edu.uceva.ovaservice.domain.model.OVA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOVAService {
    OVA save(OVA ova);
    void delete(OVA ova);
    OVA findById(Long id);
    OVA update(OVA ova);
    List<OVA> findAll();
    Page<OVA> findAll(Pageable pageable);
}
