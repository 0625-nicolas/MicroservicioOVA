package co.edu.uceva.ovaservice.model.services;

import co.edu.uceva.ovaservice.model.entities.Producto;

import java.util.List;

public interface IProductoService {
    Producto save(Producto producto);
    void delete(Producto producto);
    Producto findById(Long id);
    Producto update(Producto producto);
    List<Producto> findAll();
}
