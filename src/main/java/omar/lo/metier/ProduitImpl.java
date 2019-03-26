package omar.lo.metier;

import omar.lo.dao.ProduitRepository;
import omar.lo.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProduitImpl implements ICrudService<Produit, String> {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public List<Produit> getAll() {
        return produitRepository.findAll();
    }

    @Override
    public Produit getOne(String id) {
        return produitRepository.findById(id).get();
    }

    @Override
    public Produit save(Produit entity) {
        return produitRepository.save(entity);
    }

    @Override
    public Produit update(Produit entity) {
        return produitRepository.save(entity);
    }

    @Override
    public void deleteOne(String id) {
        produitRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        produitRepository.deleteAll();
    }

    @Override
    public Page<Produit> chercherParMotCle(String motCle, int page, int size) {
        return produitRepository.findByNomLikeOrderByNom(motCle, PageRequest.of(page, size));
    }
}
