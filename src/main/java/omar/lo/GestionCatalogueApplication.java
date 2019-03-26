package omar.lo;

import com.fasterxml.jackson.databind.ObjectMapper;
import omar.lo.entities.Categorie;
import omar.lo.entities.Produit;
import omar.lo.metier.CategorieImpl;
import omar.lo.metier.ProduitImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionCatalogueApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionCatalogueApplication.class, args);
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    CommandLineRunner startMyMethode(CategorieImpl categorieImpl, ProduitImpl produitImpl){
        return args -> {
            categorieImpl.deleteAll();
            Stream.of("C1 Ordinateurs", "C2 Imprimantes", "C3 TV").forEach(s -> {
                categorieImpl.save(new Categorie(s.split(" ")[0], s.split(" ")[1], new ArrayList<>()));
            });
            categorieImpl.getAll().forEach(System.out::println);

            produitImpl.deleteAll();
            Categorie ordi = categorieImpl.getOne("C1");
            Stream.of("P1", "P2", "P3").forEach(p -> {
                Produit pt = produitImpl.save(new Produit(null, p, Math.random()*1000, 1, "black", null,  ordi));
                //ordi.getProduits().add(pt);
                //categorieImpl.save(ordi);
                categorieImpl.addProduit(ordi, pt);
            });

            Categorie imprimante = categorieImpl.getOne("C2");
            Stream.of("P4", "P5", "P6").forEach(p -> {
                Produit pt = produitImpl.save(new Produit(null, p, Math.random()*1000, 2, "white", null, imprimante));
                //imprimante.getProduits().add(pt);
                //categorieImpl.save(imprimante);
                categorieImpl.addProduit(imprimante, pt);
            });
            produitImpl.getAll().forEach(System.out::println);
        };

    }
}
