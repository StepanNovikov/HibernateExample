package hibernate.dao;

import hibernate.entity.City;
import hibernate.entity.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Stepan Novikov
 */

@Repository
@Transactional
public class CountryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Country> getAll() {
        return entityManager.createQuery("from Country c order by c.id desc", Country.class).getResultList();
    }

    public Country getById(int id) {
        return entityManager.find(Country.class, id);
    }

    public Country create(Country country) {
        for(City city : country.getCities()) {
            city.setCountry(country);
        }

        entityManager.persist(country);
        return country;
    }

    public Country update(int id, Country country) {
        Country original = entityManager.find(Country.class, id);
        if (original != null) {
            original.setName(country.getName());
            for (City city : original.getCities()) {
                entityManager.remove(city);
            }
            original.getCities().clear();
            for (City city : country.getCities()) {
                city.setCountry(original);
                original.getCities().add(city);
                entityManager.persist(city);
            }
            entityManager.merge(original);
        }
        return original;
    }

    public void delete(int id) {
        Country country = entityManager.find(Country.class,id);
        if(country != null) {
            entityManager.remove(country);
        }
    }
}
