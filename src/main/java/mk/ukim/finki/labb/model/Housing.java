package mk.ukim.finki.labb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import lombok.Data;
import mk.ukim.finki.labb.model.enumerations.Category;

import java.util.List;

@Data
@Entity
public class Housing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Host host;
    private Integer numRooms;

    @OneToMany(mappedBy = "housing", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviews;


    public Housing(){
    }

    public Housing(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }
}
