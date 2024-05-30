package com.screenmatch.screenmatch.dto;

import com.screenmatch.screenmatch.model.Category;
import com.screenmatch.screenmatch.model.Serie;

public record SerieDTO(
        Long id,
        String title,
        Integer totalSeasons,
        Double rating,
        Category genre,
        String plot,
        String actors,
        String poster
        )
{

        public SerieDTO(Serie serie){
                this(
                        serie.getId(),
                        serie.getTitle(),
                        serie.getTotalSeasons(),
                        serie.getRating(),
                        serie.getGenre(),
                        serie.getPlot(),
                        serie.getActors(),
                        serie.getPoster()
                        );
        }

}
