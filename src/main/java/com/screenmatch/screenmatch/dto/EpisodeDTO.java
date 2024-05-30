package com.screenmatch.screenmatch.dto;

import com.screenmatch.screenmatch.model.Episode;

public record EpisodeDTO(
        String title,
        Integer season,
        Integer episode) {

    public EpisodeDTO(Episode episode){
        this(
                episode.getTitle(),
                episode.getSeason(),
                episode.getEpisode()
        );
    }
}

