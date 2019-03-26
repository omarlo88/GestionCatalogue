package omar.lo.webServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import omar.lo.entities.Categorie;
import omar.lo.entities.Produit;
import omar.lo.metier.CategorieImpl;
import omar.lo.metier.ProduitImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/GestionCatalogue")
@CrossOrigin("*")
//@CrossOrigin(origins = "*")
//@CrossOrigin(value = "*")
public class CatalogueRestController {

    @Autowired
    private CategorieImpl categorieImpl;
    @Autowired
    private ProduitImpl produitImpl;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/Categories")
    public List<Categorie> getCategories(){
        return categorieImpl.getAll();
    }

    @PostMapping("/Categories")
    public Categorie saveCategorie(@RequestBody Categorie categorie){
        return categorieImpl.save(categorie);
    }

    @GetMapping("/Categories/RechercherCategories")
    public Page<Categorie> chercherCatParMotCle(
            @RequestParam(value = "motCle", defaultValue = "") String motCle,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size){
        return categorieImpl.chercherParMotCle(motCle, page, size);
    }

    @GetMapping("/Categories/{id}")
    public Categorie getCategorie(@PathVariable String id){
        return categorieImpl.getOne(id);
    }

    @DeleteMapping("/Categories/{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable String id){
        categorieImpl.deleteOne(id);
        return new ResponseEntity<>("Suppression réussie!!", HttpStatus.OK);
    }

    @DeleteMapping("/Categorie/{idCat}/Produits/{idProd}")
    public ResponseEntity<String> removeProduit(@PathVariable("idCat") String idCat,
                                                @PathVariable("idProd") String idProd){
        categorieImpl.removeProduit(idCat, idProd);
        return new ResponseEntity<>("Suppression réussie!!", HttpStatus.OK);
    }

    @DeleteMapping("/Categories")
    public ResponseEntity<String> deleteAll(){
        categorieImpl.deleteAll();
        return new ResponseEntity<>("Suppression réussie!!", HttpStatus.OK);
    }

    @PutMapping("/Categories/{id}")
    public Categorie getCategorie(@PathVariable String id, @RequestBody Categorie categorie){
        categorie.setId(id);
        return categorieImpl.save(categorie);
    }

    @GetMapping("/Categories/{id}/Produits")
    public List<Produit> getPrdsCat(@PathVariable String id){
        return categorieImpl.getOne(id).getProduits();
    }

    @PostMapping("/Categories/{id}/Produits")
    public Produit saveProduit(@RequestParam("file") MultipartFile file,
                               @RequestParam("produit") String produit,
                               @PathVariable String id) throws IOException {
        Produit p = objectMapper.readValue(produit, Produit.class);
        p.setPhoto(file.getBytes());
        p.setColorName(file.getOriginalFilename());
        Categorie c = categorieImpl.getOne(id);
        categorieImpl.addProduit(c, p);
        return produitImpl.save(p);
    }

    @PutMapping("/Produits/UpdateInfo/{id}")
    public Produit updateInfoProduit(@PathVariable String id, @RequestBody @Valid Produit produit){
        produit.setId(id);
        return produitImpl.save(produit);
    }

    @PutMapping("/Produits/UpdatePhoto/{id}")
    public Produit updatePhotoProduit(@PathVariable String id,
                                      @RequestParam("file") MultipartFile file) throws IOException {
        Produit p = produitImpl.getOne(id);
        p.setColorName(file.getOriginalFilename());
        p.setPhoto(file.getBytes());
        return produitImpl.save(p);
    }

    @GetMapping("/Produits")
    public List<Produit> getProduits(){
        return produitImpl.getAll();
    }

    @GetMapping("/Produits/{id}")
    public Produit getProduit(@PathVariable String id){
        return produitImpl.getOne(id);
    }

    @DeleteMapping("/Produits/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable String id){
        produitImpl.deleteOne(id);
        return new ResponseEntity<>("Suppression réussie!!", HttpStatus.OK);
    }

    @DeleteMapping("/Produits")
    public ResponseEntity<String> deleteProduits(){
        produitImpl.deleteAll();
        return new ResponseEntity<>("Suppression réussie!!", HttpStatus.OK);
    }

    @GetMapping("/Produits/RechercherProduits")
    public Page<Produit> chercherProdParMotCle(
            @RequestParam(value = "motCle", defaultValue = "") String motCle,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size){
        return produitImpl.chercherParMotCle(motCle, page, size);
    }

}// CatalogueRestController
