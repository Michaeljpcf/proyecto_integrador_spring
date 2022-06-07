package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entity.Department;
import com.proyecto.entity.Mascot;
import com.proyecto.service.MascotaService;
import com.proyecto.util.AppSettings;
import com.proyecto.util.Mensaje;

@RestController
@RequestMapping("/url/mascota")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class MascotaController {

	@Autowired
	private MascotaService mascotaService;
	
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<Mascot>> listaMascota() {
		List<Mascot> lista = mascotaService.listaMascota();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Mascot> getById(@PathVariable("id") Integer id){
		if (!mascotaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe la mascota"), HttpStatus.NOT_FOUND);
		Mascot mascot = mascotaService.findById(id).get();
		return new ResponseEntity<Mascot>(mascot, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaMascota(@RequestBody Mascot obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Mascot objSalida = mascotaService.insertaActualizaMascota(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateMascota(@PathVariable("id") Integer id,@RequestBody Mascot obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Mascot objSalida = mascotaService.insertaActualizaMascota(obj);
			if (!mascotaService.existsById(id))
				return new ResponseEntity(new Mensaje("No existe el id mascota"), HttpStatus.NOT_FOUND);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	
}
