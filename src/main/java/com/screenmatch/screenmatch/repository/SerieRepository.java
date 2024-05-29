package com.screenmatch.screenmatch.repository;

import com.screenmatch.screenmatch.model.Category;
import com.screenmatch.screenmatch.model.Episode;
import com.screenmatch.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {

    Optional<Serie> findByTitleContainsIgnoreCase(String title);

    List<Serie> findTop5ByOrderByRaitingDesc();

    List<Serie> findByGenre(Category genre);

    List<Serie> findByTotalSeasonsLessThanEqualAndRaitingGreaterThan(Integer season, Double rating);

    @Query(value = "SELECT * FROM SERIES WHERE total_seasons<=3 AND raiting>=7.8",nativeQuery = true)
    List<Serie> SerieBySeasonAndRaitingNative();

    @Query("SELECT s FROM Serie s WHERE s.totalSeasons<=:totalSeason AND s.raiting>=:raiting")
    List<Serie> SerieBySeasonAndRaiting(Integer totalSeason, Double raiting);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE e.title ILIKE %:title")
    List<Episode> EpisodeByTitle(String title);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s=:serie ORDER BY e.raiting DESC LIMIT 5")
    List<Episode> Top5EpisodesBySerie(Serie serie);



}
