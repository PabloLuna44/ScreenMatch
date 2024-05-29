package com.screenmatch.screenmatch.model;

import com.screenmatch.screenmatch.service.ChatgptAPI;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity //Indicamos que es una entidad en la base de datos
@Table(name="series") //Indicamos el nombre que va a tener la tabla en la base de datos
public class Serie {

    @Id //Indicamos que este campo va a ser el ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indicamos que ese ID va a ser autoinremental
    private long id;
    @Column(unique=true) // Indicamos que esa columna va a ser unica que no tenga otras filas con ese mismo valor en esa columna
    private String title;
    private Integer totalSeasons;
    private Double raiting;
    @Enumerated(EnumType.STRING) // Indicamos que va a ser un ENUM esa columna
    private Category genre;
    private String plot;
    private String actors;
    private String poster;

    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Episode> episodes;



    public Serie(){}

    public Serie(DataSerie dataSerie) {
        this.title =dataSerie.title();
        this.totalSeasons =dataSerie.totalSeasons();
        this.raiting = OptionalDouble.of(Double.valueOf(dataSerie.raiting())).orElse(0);
        this.genre = Category.fromString(dataSerie.genre().split(",")[0].trim());
        this.plot=dataSerie.plot();
//        this.Plot= ChatgptAPI.getTranslate(dataSerie.Plot());
        this.actors =dataSerie.actors();
        this.poster =dataSerie.poster();
    }


    @Override
    public String toString() {
        return "Serie{" +
                "Title='" + title + '\'' +
                ", TotalSeasons=" + totalSeasons +
                ", Raiting=" + raiting +
                ", Genre=" + genre +
                ", Plot='" + plot + '\'' +
                ", Actors='" + actors + '\'' +
                ", Poster='" + poster + '\'' +
                ", Episodes=" + episodes +'\''+
                '}';
    }
    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        episodes.forEach(e -> e.setSerie(this));
        this.episodes = episodes;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        totalSeasons = totalSeasons;
    }

    public Double getRaiting() {
        return raiting;
    }

    public void setRaiting(Double raiting) {
        raiting = raiting;
    }

    public Category getGenre() {
        return genre;
    }

    public void setGenre(Category genre) {
        genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        plot = plot;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        poster = poster;
    }


}
