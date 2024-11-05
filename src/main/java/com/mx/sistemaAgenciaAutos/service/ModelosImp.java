package com.mx.sistemaAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sistemaAgenciaAutos.dao.MarcasDao;
import com.mx.sistemaAgenciaAutos.dao.ModelosDao;
import com.mx.sistemaAgenciaAutos.model.Marcas;
import com.mx.sistemaAgenciaAutos.model.Modelos;

@Service
public class ModelosImp {

	@Autowired
	ModelosDao modelosDao;
	@Autowired
	MarcasDao marcaDao;

	@Transactional(readOnly = true)
	public List<Modelos> listar() {
		return modelosDao.findAll();
	}

	// Validar: idmodelo, nombre no exista, idMarca exista
	// para muchas validaciones es recomendable el String valor de retorno

	// inyeccion de Marca->sAuto
	// Validar: idModelo, nombre no exista, idMarca exista, guardar
	@Transactional
	public String guardar(Modelos modelo) {

		// Ciclo...Marcas ---- Empezar a validar esto que idMarca exista
		// Condicon
		// bandera

		// Ciclo....Modelos
		// Condicion
		// Bandera

		// Ciclos anidados 2

		String respuesta = "";
		boolean banderaMar = false;
		boolean banderaMod = false;

		for (Marcas m : marcaDao.findAll()) {

			if (m.getId().equals(modelo.getMarca().getId())) {
				// Que existe el idMarca
				banderaMar = true;

				for (Modelos mod : modelosDao.findAll()) {

					if (mod.getId().equals(modelo.getId())) {
						// Que idModelo ya existe
						respuesta = "idModeloExiste";
						banderaMod = true;
						break;
					} else if (mod.getNombre().equals(modelo.getNombre())) {
						// Que nombreModelo ya existe
						respuesta = "nombreModExiste";
						banderaMod = true;
						break;
					}
				}
				break;
			}
		}

		// idMarcaNoexiste
		if (banderaMar == false) {
			respuesta = "idMarNoExiste";
			banderaMod = true;
		} else if (banderaMod == false) {
			modelosDao.save(modelo);
			respuesta = "guardado";
		}

		return respuesta;
	}


	// Que el idModelo exista y el idMarcaExista
	@Transactional
	public String editar(Modelos modelo) {
		List<Modelos> listaModelos = modelosDao.findAll();
		List<Marcas> listaMarcas = marcaDao.findAll();

		boolean modeloExiste = false;
		boolean marcaExiste = false;

		// Verificar si el modelo existe
		for (Modelos m : listaModelos) {
			if (m.getId().equals(modelo.getId())) {
				modeloExiste = true;

				/*
				 * marca existe
				 * */
				for (Marcas marca : listaMarcas) {
					if (marca.getId().equals(modelo.getMarca().getId())) {
						marcaExiste = true;

						/*
						 * enrta aqiu*/
						m.setNombre(modelo.getNombre());
						m.setTipo(modelo.getTipo());
						m.setPrecio(modelo.getPrecio());
						m.setFechaLanz(modelo.getFechaLanz());
						m.setMarca(marca); 
						modelosDao.save(m);
						return "actualizado"; 
					}
				}
				break; 
			}
		}

	
		if (!modeloExiste) {
			return "idModeloNoExiste";
		}
		if (!marcaExiste) {
			return "idMarNoExiste";
		}

		return "error"; 
	}


	// Que el idModelo exista
	@Transactional
	public boolean eliminar(Long id) {
		boolean bandera = false;

		/*
		 * buscar el ID
		 * */
		for (Modelos m : modelosDao.findAll()) {
			if (m.getId().equals(id)) {
				modelosDao.deleteById(id); 
				bandera = true;
				break;
			}
		}

		return bandera;
	}

	@Transactional(readOnly = true)
	public Modelos buscar(Long id) {
		for (Modelos modelo : modelosDao.findAll()) {
			if (modelo.getId().equals(id)) {
				return modelo;
			}
		}
		return null;
	}
}
