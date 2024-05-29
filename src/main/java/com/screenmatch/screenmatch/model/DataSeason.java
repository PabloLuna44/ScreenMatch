package com.screenmatch.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeason(
        @JsonAlias("Title") String title,
        @JsonAlias("Season") Integer season,
        @JsonAlias("Episodes") ArrayList<DataEpisode> episodes

) {



}
