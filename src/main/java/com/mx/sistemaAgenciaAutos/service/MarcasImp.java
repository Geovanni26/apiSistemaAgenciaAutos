package com.mx.sistemaAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sistemaAgenciaAutos.dao.MarcasDao;
import com.mx.sistemaAgenciaAutos.model.Marcas;


@Service
public class MarcasImp {
	@Autowired
	MarcasDao marcasDao;
	
	@Transactional(readOnly=true)
	public List<Marcas>listar(){
		return marcasDao.findAll();
	}
	
	/* 
	//Al guardar validar que el id y el nombre no se repita
	@Transactional
	public String guardar(Marcas marca) {
	    if (marcasDao.existsById(marca.getId()))
	        return "El ID ya existe.";	    
	    if (marcasDao.findAll().stream().anyMatch(m -> m.getNombre().equalsIgnoreCase(marca.getNombre())))
	        return "El nombre ya existe.";

	    marcasDao.save(marca);
	    return "Marca guardada exitosamente.";
	}*/
	
	//MAnera de guardar con ciclo y condicion: 
	@Transactional
	public String guardar(Marcas marca) {
		String respuesta = "";
		boolean bandera=false;
		for(Marcas m:marcasDao.findAll()) {
			if(m.getId().equals(marca.getId())) {
				bandera=true;
				respuesta="idExistente";
				break;				
			}else if(m.getNombre().equals(marca.getNombre())) {
				bandera=true;
				respuesta="nombreExistente";
				break;
			}			
		}
		if(bandera==false) {
			marcasDao.save(marca);
			respuesta="guardado";
		}
		return respuesta;
	}

	
	@Transactional(readOnly = true)
	public Marcas buscar(Long id) {
	    return marcasDao.findById(id).orElse(null);
	}
	
	//validar que el idExista
	@Transactional
	public boolean editar(Marcas marca) {
		boolean bandera =false;
		for(Marcas m: marcasDao.findAll()) {
			if(m.getId().equals(marca.getId())) {
				marcasDao.save(marca);
				bandera=true;
				break;
			}
		}
		return bandera;
	}
	
	//validar que el idExixta para eliminar
	public boolean eliminar(Long id) {
		boolean bandera=false;
		for(Marcas m : marcasDao.findAll()) {
			if(m.getId().equals(id)) {
				marcasDao.deleteById(id);
				bandera=true;
				break;
			}
		}
		return bandera;
	}



}





