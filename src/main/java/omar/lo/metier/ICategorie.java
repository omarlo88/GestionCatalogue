package omar.lo.metier;


import omar.lo.entities.Produit;

public interface ICategorie<T, ID> extends ICrudService<T, ID> {

    void addProduit(T categorie, Produit produit);
    void removeProduit(ID idCat, ID idP);
}
