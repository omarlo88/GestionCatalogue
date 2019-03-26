package omar.lo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Categorie {

    @Id
    private String id;
    //@Indexed(unique = true)
    private String nom;
    @DBRef
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Produit> produits;

}// Categorie
