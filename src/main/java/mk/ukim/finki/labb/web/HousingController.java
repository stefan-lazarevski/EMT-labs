package mk.ukim.finki.labb.web;


import mk.ukim.finki.labb.model.Housing;
import mk.ukim.finki.labb.model.dto.HousingDto;
import mk.ukim.finki.labb.service.HousingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housing")
public class HousingController {

    private final HousingService housingService;

    public HousingController(HousingService housingService) {
        this.housingService = housingService;
    }

    @GetMapping
    public List<Housing> findall(){
        return this.housingService.findall();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Housing> findById(@PathVariable Long id) {
        return housingService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Housing> save(@RequestBody HousingDto housing) {
        return housingService.save(housing)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Housing> update(@PathVariable Long id,@RequestBody HousingDto housing) {
        return housingService.update(id, housing)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (housingService.findById(id).isPresent()) {
            housingService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
