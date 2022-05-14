package music.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import music.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

}
