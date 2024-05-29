package com.screenmatch.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public record DataSerie(

        @JsonAlias("Title")String title,
        @JsonAlias("totalSeasons") Integer totalSeasons,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Genre") String genre,
        @JsonAlias("Plot") String plot,
        @JsonAlias("Actors") String actors,
        @JsonAlias("Poster") String poster
)

{

}
