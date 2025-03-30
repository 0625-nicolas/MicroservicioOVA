package co.edu.uceva.ovaservice.model.services;


import co.edu.uceva.ovaservice.model.entities.OVA;
import co.edu.uceva.ovaservice.model.repositories.IOVARepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OVAServiceImpl implements IOVAService {

    IOVARepository productoRepository;

    public OVAServiceImpl(IOVARepository productoRepository) {this.productoRepository = productoRepository;}

    @Override
    public OVA save(OVA producto) {return productoRepository.save(producto); }

    @Override
    public void delete(OVA producto) {
        productoRepository.delete(producto);
    }

    @Override
    public OVA findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public OVA update(OVA producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<OVA> findAll() {
        return (List<OVA>) productoRepository.findAll();
    }
}
