package omar.lo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    private String id;
    //@Indexed(unique = true)
    private String nom;
    private double price;
    private int nb;
    private String colorName;
    private byte[] photo;
    @DBRef
    private Categorie categorie;

    @Override
    public String toString() {
        return "Produit{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", price=" + price +
                ", nb=" + nb +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}// Produit
