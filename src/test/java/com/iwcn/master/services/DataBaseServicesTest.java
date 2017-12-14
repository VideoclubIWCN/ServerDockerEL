package com.iwcn.master.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.iwcn.master.model.PeliculaConcreta;
import com.iwcn.master.repositories.PeliculaConcretaRepository;

@RunWith(SpringRunner.class)
public class DataBaseServicesTest {

	private PeliculaConcreta pi;
	
	private ArrayList<PeliculaConcreta> todasPeliculas = new ArrayList<PeliculaConcreta> ();
	
	@Mock
	private PeliculaConcretaRepository peliculaRepository;
	
	@InjectMocks
	private DatabaseServices dbServices = new DatabaseServices();
	
	@Before
	public void init(){
		pi = new PeliculaConcreta(1, "tt0800369", "Thor", 
				"The powerful, but arrogant god Thor, is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.", 
				"06 May 2011", "Kenneth Branagh", "Chris Hemsworth, Natalie Portman, Tom Hiddleston, Anthony Hopkins",
				"https://images-na.ssl-images-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg", "7.0", 
				"115 min", "https://www.youtube.com/watch?v=Uvjcm93izdg");
		todasPeliculas.add(pi);
		
		when(peliculaRepository.findAll()).thenReturn(todasPeliculas);
		when(peliculaRepository.findByImdbID("tt0800369")).thenReturn(pi);
	}
	
	@Test
	public void testGetPeliculas() {
		List<PeliculaConcreta> peliculas = dbServices.getPeliculas();
		assertEquals(peliculas.size(), 1);
		verify(peliculaRepository).findAll();
		imprimir("Se verifica que se llama una vez al método 'getPeliculas'");
	}
	
	@Test
	public void testGetPeliculasTitle1() {
		List<PeliculaConcreta> peliculas = dbServices.getPeliculasTitle("Thor");
		assertEquals(peliculas.size(), 1);
		verify(peliculaRepository).findAll();
		imprimir("Se verifica que se llama una vez al método 'getPeliculasTitle'");
	}
	
	@Test
	public void testGetPeliculasTitle2() {
		List<PeliculaConcreta> peliculas = dbServices.getPeliculasTitle("Hulk");
		assertEquals(peliculas.size(), 0);
		verify(peliculaRepository).findAll();
		imprimir("Se verifica que se llama una vez al método 'getPeliculasTitle'");
	}
	
	@Test
	public void testGetPeliculasYear1() {
		List<PeliculaConcreta> peliculas = dbServices.getPeliculasYear("2011");
		assertEquals(peliculas.size(), 1);
		verify(peliculaRepository).findAll();
		imprimir("Se verifica que se llama una vez al método 'getPeliculasYear'");
	}
	
	@Test
	public void testGetPeliculasYear2() {
		List<PeliculaConcreta> peliculas = dbServices.getPeliculasYear("2010");
		assertEquals(peliculas.size(), 0);
		verify(peliculaRepository).findAll();
		imprimir("Se verifica que se llama una vez al método 'getPeliculasYear'");
	}
	
	@Test
	public void testAddPeliculaConcreta() {
        assertEquals(dbServices.getPeliculas().size(), 1);
        PeliculaConcreta p = new PeliculaConcreta(2, "tt0286716", "Hulk", 
				"Bruce Banner, a genetics researcher with a tragic past, suffers an accident that causes him to transform into a raging green monster when he gets angry.", 
				"20 Jun 2003", "Ang Lee", "Eric Bana, Jennifer Connelly, Sam Elliott, Josh Lucas",
				"https://images-na.ssl-images-amazon.com/images/M/MV5BNjcxMzZhZTEtMmEwYi00NDJmLWE5ZmUtOWJiMzRmMzEzMTY3L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg", "5.7", 
				"138 min", "https://www.youtube.com/watch?v=N5HT_NMVYUU");
        dbServices.addPelicula(p);
        todasPeliculas.add(p);
        assertEquals(dbServices.getPeliculas().size(), 2);
        verify(peliculaRepository).save(p);
		imprimir("Se verifica que se llama una vez al método 'addPelicula'");
	}

	@Test
	public void testFindId() {
		PeliculaConcreta p = dbServices.findId("tt0800369");
		assertEquals(p.getId(), 1);
		assertEquals(p.getImdbID(), "tt0800369");
		assertEquals(p.getTitle(), "Thor");
		assertEquals(p.getPlot(), "The powerful, but arrogant god Thor, is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.");
		assertEquals(p.getReleased(), "06 May 2011");
		assertEquals(p.getDirector(), "Kenneth Branagh");
		assertEquals(p.getActors(), "Chris Hemsworth, Natalie Portman, Tom Hiddleston, Anthony Hopkins");
		assertEquals(p.getPoster(), "https://images-na.ssl-images-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg");
		assertEquals(p.getImdbRating(), "7.0");
		assertEquals(p.getRuntime(), "115 min");
		assertEquals(p.getVideo(), "https://www.youtube.com/watch?v=Uvjcm93izdg");
		verify(peliculaRepository).findByImdbID("tt0800369");
		imprimir("Se verifica que se llama una vez al método 'findId'");
	}

	@Test
	public void testDeletePelicula() {
        assertEquals(dbServices.getPeliculas().size(), 1);
        PeliculaConcreta p = dbServices.findId("tt0800369");
        dbServices.deletePelicula(pi);
        todasPeliculas.remove(p);
        assertEquals(dbServices.getPeliculas().size(), 0);
        verify(peliculaRepository).delete(dbServices.findId("tt0800369"));
		imprimir("Se verifica que se llama una vez al método 'deletePelicula'");
	}

	@Test
	public void testModPelicula() {
        assertEquals(dbServices.getPeliculas().size(), 1);
        PeliculaConcreta p = new PeliculaConcreta(2, "tt0286716", "Hulk", 
				"Bruce Banner, a genetics researcher with a tragic past, suffers an accident that causes him to transform into a raging green monster when he gets angry.", 
				"20 Jun 2003", "Ang Lee", "Eric Bana, Jennifer Connelly, Sam Elliott, Josh Lucas",
				"https://images-na.ssl-images-amazon.com/images/M/MV5BNjcxMzZhZTEtMmEwYi00NDJmLWE5ZmUtOWJiMzRmMzEzMTY3L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg", "5.7", 
				"138 min", "https://www.youtube.com/watch?v=N5HT_NMVYUU");
        dbServices.modPelicula(p);
        todasPeliculas.set(0, p);
        assertEquals(dbServices.getPeliculas().size(), 1);
        imprimir("Se verifica que se llama una vez al método 'modPelicula'");
	}
	
	private static void imprimir(String text) {
		System.out.println(text);
	}

}