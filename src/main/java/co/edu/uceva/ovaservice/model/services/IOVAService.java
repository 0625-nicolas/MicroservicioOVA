package co.edu.uceva.ovaservice.model.services;

import co.edu.uceva.ovaservice.model.entities.OVA;

import java.util.List;

public interface IOVAService {
    OVA save(OVA producto);
    void delete(OVA producto);
    OVA findById(Long id);
    OVA update(OVA producto);
    List<OVA> findAll();
}
