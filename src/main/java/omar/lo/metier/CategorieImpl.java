package omar.lo.metier;

import omar.lo.dao.CategorieRepository;
import omar.lo.entities.Categorie;
import omar.lo.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategorieImpl implements ICategorie<Categorie, String> {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public List<Categorie> getAll() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getOne(String id) {
        return categorieRepository.findById(id).get();
    }

    @Override
    public Categorie save(Categorie entity) {
        return categorieRepository.save(entity);
    }

    @Override
    public Categorie update(Categorie entity) {
        return categorieRepository.save(entity);
    }

    @Override
    public void deleteOne(String id) {
        //categorieRepository.delete(Categorie c);
        categorieRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        categorieRepository.deleteAll();
    }

    @Override
    public Page<Categorie> chercherParMotCle(String motCle, int page, int size) {
        return categorieRepository.findByNomLikeOrderByNom(motCle, PageRequest.of(page, size));
    }


    @Override
    public void addProduit(Categorie categorie, Produit produit) {
        categorie.getProduits().add(produit);
        categorieRepository.save(categorie);
    }

    @Override
    public void removeProduit(String idCat, String idP) {
        Categorie c = categorieRepository.findById(idCat).get();
        //boolean p = c.getProduits().remove(idP);
        c.getProduits().remove(idP);
    }
}
