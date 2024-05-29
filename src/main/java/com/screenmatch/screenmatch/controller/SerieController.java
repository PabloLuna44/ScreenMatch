package com.screenmatch.screenmatch.controller;

import com.screenmatch.screenmatch.model.Serie;
import com.screenmatch.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {

    @Autowired
    SerieRepository repository;


    @GetMapping("/series")
    public List<Serie> getSeries() {

        return repository.findAll();

    }

}
