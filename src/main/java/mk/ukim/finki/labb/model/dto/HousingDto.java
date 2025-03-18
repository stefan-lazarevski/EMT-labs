package mk.ukim.finki.labb.model.dto;

import lombok.Data;
import mk.ukim.finki.labb.model.Host;
import mk.ukim.finki.labb.model.enumerations.Category;

@Data
public class HousingDto {

    private String name;
    private String category;
    private Long host;
    private Integer numRooms;

    public HousingDto(){
    }

    public HousingDto(String name, String category, Long host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getHost() {
        return host;
    }

    public void setHost(Long host) {
        this.host = host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }
}
