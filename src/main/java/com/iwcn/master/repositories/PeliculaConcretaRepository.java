package com.iwcn.master.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iwcn.master.model.PeliculaConcreta;

public interface PeliculaConcretaRepository extends CrudRepository<PeliculaConcreta, Integer> {
	
	PeliculaConcreta findByImdbID (String id);
	
	@Transactional
	@Modifying
	@Query("UPDATE PeliculaConcreta p SET p.Plot=?1, p.Released=?2, p.Director=?3, p.Actors=?4, p.Poster=?5, "
			+ "p.imdbRating=?6, p.Runtime=?7, p.Video=?8 WHERE p.imdbID=?9")
	int setNewPelicula(String Plot, String Released, String Director, String Actors, String Poster, String imdbRating, String Runtime,
			String Video, String id);
}
