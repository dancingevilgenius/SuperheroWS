package com.restapi.superhero.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.superhero.entity.HeroData;
import com.restapi.superhero.entity.Superhero;
import com.restapi.superhero.service.SuperheroService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class SuperheroController {

    @Autowired
    private SuperheroService service;

    @PostMapping("/")
    public ResponseObject save(@RequestBody HeroData heroData) {

        ResponseObject ro;
        List<String> errorList = new ArrayList<String>();
        boolean success = false;

        Superhero tmpSuperhero = new Superhero();
        // @TODO validate input parameters
        if (heroData == null) {
            success = false;
            errorList.add("heroData from RequestBody is null");
            ro = new ResponseObject(success, errorList, tmpSuperhero);
            return ro;
        }

        if (heroData.getHeroName() == null || heroData.getHeroName().isEmpty()) {
            success = false;
            errorList.add("heroName from RequestBody is null or empty");
            ro = new ResponseObject(success, errorList, null);
            return ro;
        }

        tmpSuperhero.setHeroData(heroData);
        tmpSuperhero = service.saveSuperhero(tmpSuperhero);
        success = true;
        ro = new ResponseObject(success, errorList, tmpSuperhero);

        return ro;
    }

    // Tested. Works!
    @GetMapping("findAll")
    public ResponseList findAll() {

        boolean success = false;

        List<String> errorMsgs = new ArrayList<String>();

        List<Superhero> list = service.findAll();

        success = true;
        return new ResponseList(success, errorMsgs, list);
    }

    // Tested. Works!
    @GetMapping("/{id}")
    public ResponseObject findById(@PathVariable Long id) {

        boolean success = false;
        ResponseObject ro;
        List<String> errorMsgs = new ArrayList<String>();
        if (id == null || id < 0) {
            errorMsgs.add("id parameter is null or negative");
            ro = new ResponseObject(success, errorMsgs, null);
            return ro;
        }

        Superhero sh = service.findById(id);
        if (sh == null || sh.getId() == null || sh.getHeroData() == null) {
            success = false;
            errorMsgs.add("No record found for id:" + id);
            ro = new ResponseObject(success, errorMsgs, sh);
            return ro;
        }

        success = true;
        ro = new ResponseObject(success, errorMsgs, sh);
        return ro;
    }

    @PutMapping("/{id}")
    public ResponseObject update(@RequestBody HeroData heroData, @PathVariable Long id) {

        ResponseObject ro;
        List<String> errorList = new ArrayList<String>();
        boolean success = false;

        if (id == null) {
            errorList.add("input param id is null.");
            success = false;
            ro = new ResponseObject(success, errorList, null);
            return ro;
        }

        Superhero existingSuperhero = service.findById(id);
        if (existingSuperhero == null) {
            errorList.add("No data found for id:" + id);
            success = false;
            ro = new ResponseObject(success, errorList, null);
            return ro;
        }

        existingSuperhero.setHeroData(heroData);

        Superhero newSuperhero = service.updateSuperhero(existingSuperhero);
        success = true;
        ro = new ResponseObject(success, errorList, newSuperhero);

        return ro;
    }

    @DeleteMapping("/{id}")
    public ResponseObject deleteById(@PathVariable Long id) {

        List<String> errorList = new ArrayList<String>();
        boolean success = false;

        if (id == null || id < 0) {
            success = false;
            errorList.add("input param id is null or negative");
            return new ResponseObject(success, errorList, null);
        }

        Superhero existingSuperhero = service.findById(id);
        if (existingSuperhero == null || existingSuperhero.getId() == null || existingSuperhero.getHeroData() == null) {
            errorList.add("No record found for id:" + id);
            success = false;
            return new ResponseObject(success, errorList, null);
        }

        Superhero tmpHero = new Superhero();
        tmpHero.setId(id);
        service.deleteSuperhero(tmpHero);

        success = true;
        return new ResponseObject(success, errorList, null);
    }

}
