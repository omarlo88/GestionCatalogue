package omar.lo.dao;

import omar.lo.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface ProduitRepository extends MongoRepository<Produit, String> {

    Page<Produit> findByNomLikeOrderByNom(String motCle, Pageable pageable);

    /*@Query("select p from Produit p where p.nom like :x ORDER BY p.nom DESC")
    Page<Produit> chercherPdtParMotCle(String motCle, Pageable pageable);*/
}
