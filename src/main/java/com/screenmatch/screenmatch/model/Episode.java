package com.screenmatch.screenmatch.model;

import jakarta.persistence.*;

import java.time.DateTimeException;
import java.time.LocalDate;

@Entity
@Table(name="episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private Integer season;
    private Integer episode;
    private Double raiting;
    private LocalDate released;
    @ManyToOne
    private Serie serie;

    public Episode(){

    }

    public Episode(Integer season,DataEpisode episode){
        this.season=season;
        this.title=episode.title();
        this.episode=episode.episode();
        try {
            this.raiting= Double.valueOf(episode.raiting());
            this.released= LocalDate.parse(episode.released());
        }catch (NumberFormatException e){
            this.raiting=0.0;
        }catch ( DateTimeException e){
            this.released= LocalDate.parse("0000-00-00");
        }



    }

    public Serie getSerie() {
        return serie;
    }
    public void setSerie(Serie serie){
        this.serie=serie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Double getRaiting() {
        return raiting;
    }

    public void setRaiting(Double raiting) {
        this.raiting = raiting;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "title='" + title + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                ", raiting=" + raiting +
                ", released=" + released +
                '}';
    }
}
