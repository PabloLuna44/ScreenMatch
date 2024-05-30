package com.screenmatch.screenmatch.controller;

import com.screenmatch.screenmatch.dto.EpisodeDTO;
import com.screenmatch.screenmatch.dto.SerieDTO;

import com.screenmatch.screenmatch.model.Serie;
import com.screenmatch.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping()
    public List<SerieDTO> getSeries() {
        return serieService.getSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> top5() {
        return serieService.getTop5();
    }

    @GetMapping("/releases")
    public List<SerieDTO> releases() {
        return serieService.getMostRecentSeries();
    }

    @GetMapping("/{id}")
    public SerieDTO getSerie(@PathVariable Long id) {
        return serieService.getSerieById(id);
    }

    @GetMapping("/{id}/season/all")
    public List<EpisodeDTO> getEpisode(@PathVariable Long id) {
        return serieService.getEpisodesBySerieId(id);
    }

    @GetMapping("/{serieId}/season/{seasonId}")
    public List<EpisodeDTO> getEpisodesBySeason(@PathVariable Long serieId, @PathVariable Long seasonId) {
        return serieService.getEpisodesBySeason(serieId, seasonId);
    }

    @GetMapping("/category/{genre}")
    public List<SerieDTO> getSerieByGenre(@PathVariable String genre){
        return serieService.getSerieByGenre(genre);
    }

    @GetMapping("/{id}/seasons/top")
    public List<EpisodeDTO> getTopEpisodesBySerie(@PathVariable Long id){
        return serieService.getTopEpisodesBySerie(id);
    }
}
