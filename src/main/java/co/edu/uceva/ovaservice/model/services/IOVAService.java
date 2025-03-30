package co.edu.uceva.ovaservice.model.services;

import co.edu.uceva.ovaservice.model.entities.OVA;

import java.util.List;

public interface IOVAService {
    OVA save(OVA ova);
    void delete(OVA ova);
    OVA findById(Long id);
    OVA update(OVA ova);
    List<OVA> findAll();
}
