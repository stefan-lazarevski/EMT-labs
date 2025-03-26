package mk.ukim.finki.labb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private String comment;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "housing_id")

    @JsonBackReference
    @ToString.Exclude
    private Housing housing;

    public Review(String comment, Double rating, Housing housing) {
        this.comment = comment;
        this.rating = rating;
        this.housing = housing;
    }

    public Review(){
    }
}
