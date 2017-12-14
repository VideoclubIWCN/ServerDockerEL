package com.iwcn.master.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import com.iwcn.master.controllers.AppRestController;
import com.iwcn.master.model.PeliculaConcreta;

@RunWith(SpringRunner.class)
public class AppRestControllerTest {
	
	private ArrayList<PeliculaConcreta> todasPeliculas = new ArrayList<PeliculaConcreta> ();
	
	@Mock
	private DatabaseServices dbServices;
	
	@InjectMocks
	private AppRestController appRest = new AppRestController();
	
	public void AppRestController () {
		
	}
	
	@Before
	public void init(){
		PeliculaConcreta p = new PeliculaConcreta(1, "tt0800369", "Thor", 
				"The powerful, but arrogant god Thor, is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.", 
				"06 May 2011", "Kenneth Branagh", "Chris Hemsworth, Natalie Portman, Tom Hiddleston, Anthony Hopkins",
				"https://images-na.ssl-images-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg", "7.0", 
				"115 min", "https://www.youtube.com/watch?v=Uvjcm93izdg");
		todasPeliculas.add(p);
		
		when(dbServices.findId("tt0800369")).thenReturn(p);
		when(dbServices.getPeliculas()).thenReturn(todasPeliculas);
		when(dbServices.getPeliculasTitle("Thor")).thenReturn(todasPeliculas);
		when(dbServices.getPeliculasYear("2011")).thenReturn(todasPeliculas);
		
	}
	
	@Test
	public void testListaPelicula() {
		assertEquals(appRest.listaPelicula().size(), 1);
		verify(dbServices).getPeliculas();
		imprimir("Se verifica que se llama una vez al método 'getPeliculas'");
	}
	
	@Test
	public void testListaPeliculaBusquedaTitle() {
		assertEquals(appRest.listaPeliculaBusqueda("Thor").size(), 1);
		verify(dbServices).getPeliculasTitle("Thor");
		imprimir("Se verifica que se llama una vez al método 'getPeliculasTitle'");
	}
	
	@Test
	public void testListaPeliculaBusquedaYear() {
		assertEquals(appRest.listaPeliculaBusqueda("2011").size(), 1);
		verify(dbServices).getPeliculasYear("2011");
		imprimir("Se verifica que se llama una vez al método 'getPeliculasYear'");
	}
	
	@Test
	public void testNuevoPelicula2() {
		assertEquals(appRest.listaPelicula().size(), 1);
		PeliculaConcreta p = new PeliculaConcreta(1, "tt0800369", "Thor", 
				"The powerful, but arrogant god Thor, is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.", 
				"06 May 2011", "Kenneth Branagh", "Chris Hemsworth, Natalie Portman, Tom Hiddleston, Anthony Hopkins",
				"https://images-na.ssl-images-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg", "7.0", 
				"115 min", "https://www.youtube.com/watch?v=Uvjcm93izdg");
		todasPeliculas.add(p);
		appRest.nuevaPelicula2(p);
		assertEquals(appRest.listaPelicula().size(), 2);
		verify(dbServices).addPelicula(p);
		imprimir("Se verifica que se llama una vez al método 'addPelicula'");
	}
	
	@Test
	public void testMostrarPelicula() {
		PeliculaConcreta p = appRest.mostrarPelicula("tt0800369");
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
		verify(dbServices).findId("tt0800369");
		imprimir("Se verifica que se llama una vez al método 'findId'");
	}		
	
	@Test
	public void testBorrarPelicula() {
		assertEquals(appRest.listaPelicula().size(), 1);
		PeliculaConcreta p = appRest.mostrarPelicula("tt0800369");
		appRest.borrarPelicula("tt0800369");
		todasPeliculas.remove(p);
		assertEquals(appRest.listaPelicula().size(), 0);
		verify(dbServices).deletePelicula(p);
		imprimir("Se verifica que se llama una vez al método 'deletePelicula'");
	}
	
	@Test
	public void testModificarPelicula() {
		assertEquals(appRest.listaPelicula().size(), 1);
		PeliculaConcreta p = new PeliculaConcreta(1, "tt0286716", "Hulk", 
				"Bruce Banner, a genetics researcher with a tragic past, suffers an accident that causes him to transform into a raging green monster when he gets angry.", 
				"20 Jun 2003", "Ang Lee", "Eric Bana, Jennifer Connelly, Sam Elliott, Josh Lucas",
				"https://images-na.ssl-images-amazon.com/images/M/MV5BNjcxMzZhZTEtMmEwYi00NDJmLWE5ZmUtOWJiMzRmMzEzMTY3L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg", "5.7", 
				"138 min", "https://www.youtube.com/watch?v=N5HT_NMVYUU");
		appRest.modificarPelicula(p);
		todasPeliculas.set(0, p);
		assertEquals(appRest.listaPelicula().size(), 1);
		verify(dbServices).modPelicula(p);
		imprimir("Se verifica que se llama una vez al método 'modPelicula'");
	}
	
	private static void imprimir(String text) {
		System.out.println(text);
	}
	
}