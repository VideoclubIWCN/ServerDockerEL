package com.iwcn.master.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwcn.master.model.PeliculaConcreta;
import com.iwcn.master.repositories.PeliculaConcretaRepository;

@Service
public class DatabaseServices {
	
	@Autowired
	private PeliculaConcretaRepository peliculaConcretaRepository;
	
	public ArrayList<PeliculaConcreta> getPeliculas () {
		ArrayList<PeliculaConcreta> peliculas = new ArrayList<>();
		for (PeliculaConcreta p : peliculaConcretaRepository.findAll()) {
			peliculas.add(p);
		}
		return peliculas;
	}
	
	public ArrayList<PeliculaConcreta> getPeliculasTitle (String optio) {
		ArrayList<PeliculaConcreta> peliculas = new ArrayList<>();
		String option = optio.toLowerCase();
		for (PeliculaConcreta p : peliculaConcretaRepository.findAll())
			if (p.getTitle().toLowerCase().contains(option))
				peliculas.add(p);
		return peliculas;
	}
	
	public ArrayList<PeliculaConcreta> getPeliculasYear (String optio) {
		ArrayList<PeliculaConcreta> peliculas = new ArrayList<>();
		for (PeliculaConcreta p : peliculaConcretaRepository.findAll())
			if (p.getReleased().contains(optio))
				peliculas.add(p);
		return peliculas;
	}
	
	public void addPelicula (PeliculaConcreta pi) {
		peliculaConcretaRepository.save(pi);
	}
	
	public PeliculaConcreta findId (String optio) {
		return peliculaConcretaRepository.findByImdbID(optio);
	}
	
	public void deletePelicula (PeliculaConcreta pi) {
		peliculaConcretaRepository.delete(pi);
	}

	public void modPelicula (PeliculaConcreta pi) {
		peliculaConcretaRepository.setNewPelicula(pi.getPlot(), pi.getReleased(), pi.getDirector(), pi.getActors(), 
				pi.getPoster(), pi.getImdbRating(), pi.getRuntime(), pi.getVideo(), pi.getImdbID());
	}
}