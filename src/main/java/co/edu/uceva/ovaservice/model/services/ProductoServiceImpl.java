package co.edu.uceva.ovaservice.model.services;


import co.edu.uceva.ovaservice.model.entities.Producto;
import co.edu.uceva.ovaservice.model.repositories.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    IProductoRepository productoRepository;

    public ProductoServiceImpl(IProductoRepository productoRepository) {this.productoRepository = productoRepository;}

    @Override
    public Producto save(Producto producto) {return productoRepository.save(producto); }

    @Override
    public void delete(Producto producto) {
        productoRepository.delete(producto);
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto update(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }
}
