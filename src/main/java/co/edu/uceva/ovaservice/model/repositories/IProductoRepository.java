package co.edu.uceva.ovaservice.model.repositories;

import co.edu.uceva.ovaservice.model.entities.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoRepository extends CrudRepository<Producto, Long> {
}
