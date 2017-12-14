package com.iwcn.master.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iwcn.master.model.PeliculaConcreta;
import com.iwcn.master.services.DatabaseServices;

@RestController
public class AppRestController {
	
	@Autowired
	private DatabaseServices dbServices;
	
	// Vista que contiene Lista de Peliculas
	@RequestMapping(value = "/principal/list", method = RequestMethod.GET)
    public List<PeliculaConcreta> listaPelicula() {
		return dbServices.getPeliculas();
    }
	
	// Vista que contiene Lista de Peliculas tras la búsqueda
	@RequestMapping(value = "/principal/busqueda3", method = RequestMethod.GET)
    public List<PeliculaConcreta> listaPeliculaBusqueda(@RequestParam String optio) {
		boolean canConvert;
		
		// Saber si se puede buscar por año o no
		try {
			Integer.parseInt(optio);
			canConvert = true;
		}
		catch (NumberFormatException excepcion) {
			canConvert = false;
		}
		
		// Buscar por año si la entrada se puede convertir a entero
		if (canConvert)
			return dbServices.getPeliculasYear(optio);
		
		// Buscar por titulo si la entrada no se puede convertir a entero
		else
			return dbServices.getPeliculasTitle(optio);
    }
	
	// Vista que muestra que la pelicula se ha añadido
	@RequestMapping(value = "/principal/fin", method = RequestMethod.POST)
    public void nuevaPelicula2(@RequestBody PeliculaConcreta pi) {
		dbServices.addPelicula(pi);
    }
	
	// Vista de la pelicula en si
    @RequestMapping(value = "/principal/peliculaBD/{optio}", method = RequestMethod.GET)
	public PeliculaConcreta mostrarPelicula(@PathVariable String optio) {
    	return dbServices.findId(optio);
    }
    
    // Vista que contiene Lista de Productos
    @RequestMapping(value = "/principal/peliculaBD/borrar/{optio}", method = RequestMethod.DELETE)
    public void borrarPelicula(@PathVariable String optio) {
    	dbServices.deletePelicula(dbServices.findId(optio));
    } 
    
    // Vista que muestra que el producto se ha editado
    @RequestMapping(value = "/principal/peliculaBD/modificar", method = RequestMethod.POST)
    public void modificarPelicula(@RequestBody PeliculaConcreta pi) {
    	dbServices.modPelicula(pi);
    }
    
}