package com.screenmatch.screenmatch.service;

import com.screenmatch.screenmatch.dto.EpisodeDTO;
import com.screenmatch.screenmatch.dto.SerieDTO;
import com.screenmatch.screenmatch.model.Category;
import com.screenmatch.screenmatch.model.Episode;
import com.screenmatch.screenmatch.model.Serie;
import com.screenmatch.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> getSeries() {
        return serieToSerieDTO(repository.findAll());
    }

    public List<SerieDTO> getTop5(){
        return serieToSerieDTO(repository.findTop5ByOrderByRatingDesc());
    }

    public List<SerieDTO> getMostRecentSeries(){
        return serieToSerieDTO(repository.mostRecentSeries());
    }

    public SerieDTO getSerieById(Long id){
        Optional<Serie> serie= repository.findById(id);
        if(serie.isPresent()){
            return new SerieDTO(serie.get());
        }else{
            return null;
        }

    }

    public List<EpisodeDTO> getEpisodesBySerieId(Long id){
        Optional<Serie> serie= repository.findById(id);

        if(serie.isPresent()){
            return serie.get().getEpisodes().stream()
                    .map(e-> new EpisodeDTO(e))
                    .collect(Collectors.toList());
        }
        return null;

    }

    public List<EpisodeDTO> getEpisodesBySeason(Long serieId,Long seasonId ){

        List<Episode> episodes=repository.episodesBySeason(serieId,seasonId);

        if(!episodes.isEmpty()){
            return episodes.stream()
                    .map(e->new EpisodeDTO(e))
                    .collect(Collectors.toList());
        }
        return null;

    }

    public List<SerieDTO> getSerieByGenre(String genre){

        Category category=Category.fromString(genre);
        List<Serie> series =repository.findByGenre(category);

        if(!series.isEmpty()){
            return serieToSerieDTO(series);
        }
        return null;
    }

    public List<EpisodeDTO> getTopEpisodesBySerie(Long id){
        List<Episode> episodes=repository.TopEpisodesBySerieId(id);

        if (!episodes.isEmpty()){
            return episodes.stream()
                    .map(e->new EpisodeDTO(e))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<SerieDTO> serieToSerieDTO(List<Serie> series){

        return series.stream()
                .map(s->new SerieDTO(s))
                .collect(Collectors.toList());

    }



}
