package com.restapi.superhero.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.superhero.entity.Superhero;
import com.restapi.superhero.repository.SuperheroRepository;

@Service
public class SuperheroService {

    @Autowired
    private SuperheroRepository repository;

    public Superhero saveSuperhero(Superhero superhero) {
        // @Todo validate input
        return repository.save(superhero);
    }

    public Superhero findById(Long id) {

        // @Todo validate input
        Optional<Superhero> sh = repository.findById(id);

        if (sh.isEmpty()) {
            return new Superhero();
        } else {
            return sh.get();
        }
    }

    public List<Superhero> findAll() {
        return repository.findAll();
    }

    public Superhero updateSuperhero(Superhero newSuperhero) {

        // @Todo validate input

        Optional<Superhero> dbsuperhero = repository.findById(newSuperhero.getId());

        if (dbsuperhero.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Superhero existingSuperhero = dbsuperhero.get();

        // Modify db record with changes here.
        existingSuperhero.setHeroData(newSuperhero.getHeroData());

        return repository.save(existingSuperhero);
    }

    public boolean deleteSuperhero(Superhero newSuperhero) {
        // @Todo validate input

        Optional<Superhero> dbsuperhero = repository.findById(newSuperhero.getId());

        if (dbsuperhero.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Superhero existingSuperhero = dbsuperhero.get();
        repository.delete(existingSuperhero);

        boolean success = true;
        return success;
    }

}
