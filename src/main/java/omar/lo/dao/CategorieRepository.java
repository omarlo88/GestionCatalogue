package omar.lo.dao;

import omar.lo.entities.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface CategorieRepository extends MongoRepository<Categorie, String> {

    Page<Categorie> findByNomLikeOrderByNom(String motCle, Pageable pageable);

    /*@Query("select c from Categorie c where c.nom like :x ORDER BY c.nom DESC")
    Page<Categorie> chercherCatParMotCle(@Param("x") String motCle, Pageable pageable);*/
}
