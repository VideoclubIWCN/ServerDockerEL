package com.iwcn.master.services;

import static org.junit.Assert.*;

import org.junit.Test;

import com.iwcn.master.model.PeliculaConcreta;

public class PeliculaConcretaTest {

	@Test
	public void testCompleto() {
		PeliculaConcreta p = new PeliculaConcreta(1, "tt0800369", "Thor", 
				"The powerful, but arrogant god Thor, is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.", 
				"06 May 2011", "Kenneth Branagh", "Chris Hemsworth, Natalie Portman, Tom Hiddleston, Anthony Hopkins",
				"https://images-na.ssl-images-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg", "7.0", 
				"115 min", "https://www.youtube.com/watch?v=Uvjcm93izdg");
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
		imprimir("Se verifica que se crea un producto completo");
	}
	
	@Test
	public void testVacio() {
		PeliculaConcreta p = new PeliculaConcreta();
		assertEquals(p.getId(), 0);
		assertEquals(p.getImdbID(), null);
		assertEquals(p.getTitle(), null);
		assertEquals(p.getPlot(), null);
		assertEquals(p.getReleased(), null);
		assertEquals(p.getDirector(), null);
		assertEquals(p.getActors(), null);
		assertEquals(p.getPoster(), null);
		assertEquals(p.getImdbRating(), null);
		assertEquals(p.getRuntime(), null);
		assertEquals(p.getVideo(), null);
		imprimir("Se verifica que se crea un producto");
	}

	@Test
	public void testSetID() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setId(1);
		assertEquals(p.getId(), 1);
		imprimir("Se verifica que se inserta el 'Id'");
	}
	
	@Test
	public void testSetImdbID() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setImdbID("tt0800369");
		assertEquals("tt0800369", p.getImdbID());
		imprimir("Se verifica que se inserta el 'ImdbId'");
	}
	
	@Test
	public void testSetTitle() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setTitle("Thor");
		assertEquals("Thor", p.getTitle());
		imprimir("Se verifica que se inserta el 'Titulo'");
	}
	
	@Test
	public void testSetPlot() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setPlot("The powerful, but arrogant god Thor, is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.");
		assertEquals(p.getPlot(), "The powerful, but arrogant god Thor, is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.");
		imprimir("Se verifica que se inserta el 'Plot'");
	}
	
	@Test
	public void testSetReleased() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setReleased("06 May 2011");
		assertEquals(p.getReleased(), "06 May 2011");
		imprimir("Se verifica que se inserta el 'Released'");
	}
	
	@Test
	public void testSetDirector() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setDirector("Kenneth Branagh");
		assertEquals(p.getDirector(), "Kenneth Branagh");
		imprimir("Se verifica que se inserta el 'Director'");
	}
	
	@Test
	public void testSetActors() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setActors("Chris Hemsworth, Natalie Portman, Tom Hiddleston, Anthony Hopkins");
		assertEquals(p.getActors(), "Chris Hemsworth, Natalie Portman, Tom Hiddleston, Anthony Hopkins");
		imprimir("Se verifica que se inserta el 'Actors'");
	}
	
	@Test
	public void testSetPoster() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg");
		assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg", p.getPoster());
		imprimir("Se verifica que se inserta el 'Poster'");
	}
	
	@Test
	public void testSetImdbRating() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setImdbRating("7.0");
		assertEquals(p.getImdbRating(), "7.0");
		imprimir("Se verifica que se inserta el 'ImdbRating'");
	}
	
	@Test
	public void testSetRuntime() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setRuntime("115 min");
		assertEquals(p.getRuntime(), "115 min");
		imprimir("Se verifica que se inserta el 'Runtime'");
	}
	
	@Test
	public void testSetVideo() {
		PeliculaConcreta p = new PeliculaConcreta();
		p.setVideo("https://www.youtube.com/watch?v=Uvjcm93izdg");
		assertEquals(p.getVideo(), "https://www.youtube.com/watch?v=Uvjcm93izdg");
		imprimir("Se verifica que se inserta el 'Video'");
	}
	
	private static void imprimir(String text) {
		System.out.println(text);
	}

}
