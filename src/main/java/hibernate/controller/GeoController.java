package hibernate.controller;

import hibernate.dao.CountryDao;
import hibernate.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Stepan Novikov
 */

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeoController {

    @Autowired
    private CountryDao countryDao;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryDao.getAll();
    }

    @GetMapping("/countries/{countryId}")
    public Country getCountryById(@PathVariable("countryId") int id) {
        return countryDao.getById(id);
    }

    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    public Country createCountry(@RequestBody Country country) {
        return countryDao.create(country);
    }

    @PutMapping("/countries/{countryId}")
    public Country updateCountry(@PathVariable("countryId") int id, @RequestBody Country country) {
        return countryDao.update(id, country);
    }

    @DeleteMapping("/countries/{countryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable("countryId") int id) {
        countryDao.delete(id);
    }

}
