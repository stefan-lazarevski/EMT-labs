package mk.ukim.finki.labb.service.impl;

import mk.ukim.finki.labb.model.Housing;
import mk.ukim.finki.labb.model.dto.HousingDto;
import mk.ukim.finki.labb.model.enumerations.Category;
import mk.ukim.finki.labb.repository.CountryRepository;
import mk.ukim.finki.labb.repository.HostRepository;
import mk.ukim.finki.labb.repository.HousingRepository;
import mk.ukim.finki.labb.service.HousingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HousingServiceImpl implements HousingService {

    private final HousingRepository housingRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;


    public HousingServiceImpl(HousingRepository housingRepository, HostRepository hostRepository, CountryRepository countryRepository) {
        this.housingRepository = housingRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Housing> findall() {
        return this.housingRepository.findAll();
    }

    @Override
    public Optional<Housing> findById(Long id) {
        return this.housingRepository.findById(id);
    }

    @Override
    public Optional<Housing> update(Long id, HousingDto housing) {
        return housingRepository.findById(id)
                .map(existingHousing -> {
                    if(housing.getName() != null) {
                        existingHousing.setName(housing.getName());
                    }
                    if (housing.getHost() != null && hostRepository.findById(housing.getHost()).isPresent()) {
                        existingHousing.setHost(hostRepository.findById(housing.getHost()).get());
                    }

//                    if(housing.getHost() != null) {
//                        existingHousing.setHost(housing.getHost());
//                    }

                    if (housing.getCategory() != null) {
                        try {
                            // If your enum has a method to get by name
                            Category categoryEnum = Category.valueOf(housing.getCategory());
                            existingHousing.setCategory(categoryEnum);
                        } catch (IllegalArgumentException e) {
                            // Handle invalid enum name
                        }
                    }

//                    if(housing.getCategory() != null) {
//                        existingHousing.setCategory(housing.getCategory());
//                    }
                    if(housing.getNumRooms() != null) {
                        existingHousing.setNumRooms(housing.getNumRooms());
                    }
                    return housingRepository.save(existingHousing);
                });
    }

    @Override
    public Optional<Housing> save(HousingDto housing) {
        Housing housing1 = new Housing();
        housing1.setName(housing.getName());
//        housing1.setHost(housing.getHost());

        if (housing.getHost() != null) {
            hostRepository.findById(housing.getHost())
                    .ifPresent(housing1::setHost);
        }

        // For category: Convert String to Category enum
        if (housing.getCategory() != null) {
            try {
                // Directly convert string to enum value
                Category categoryEnum = Category.valueOf(housing.getCategory());
                housing1.setCategory(categoryEnum);
            } catch (IllegalArgumentException e) {
                // Handle case where string doesn't match any enum value
                // You might want to log this or use a default category
            }
        }

//        housing1.setCategory(housing.getCategory());
        housing1.setNumRooms(housing.getNumRooms());

        return Optional.of(housingRepository.save(housing1));
    }

    @Override
    public void deleteById(Long id) {
        housingRepository.deleteById(id);
    }
}
