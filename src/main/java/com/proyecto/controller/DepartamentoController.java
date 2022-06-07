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
import com.proyecto.entity.DepartmentType;
import com.proyecto.service.DepartamentoService;
import com.proyecto.service.DepartmentTypesService;
import com.proyecto.util.AppSettings;
import com.proyecto.util.Mensaje;

@RestController
@RequestMapping("/url/departamento")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class DepartamentoController {
		
	@Autowired
	private DepartamentoService  departamentoService;
	
	@Autowired
	private DepartmentTypesService service;
	
	@GetMapping("/list")
	public ResponseEntity<List<Department>> obtenerListDepartamento(){
		List<Department> lista = departamentoService.listDepartament();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listdepartmenttypes")
	public ResponseEntity<List<DepartmentType>> getListDepartamentTypes(){
		List<DepartmentType> lista = service.listDepartamentTypes();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Department> getById(@PathVariable("id") Integer id){
		if (!departamentoService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe departamento"), HttpStatus.NOT_FOUND);
		Department department = departamentoService.findById(id).get();
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaDepartamento(@RequestBody Department obj){
		Map<String, Object> salida = new HashMap<>();		
		try {
			if (departamentoService.existsByNumber(obj.getNumber()))
				return new ResponseEntity(new Mensaje("Este número departamento ya existe"), HttpStatus.NOT_FOUND);
			Department objSalida = departamentoService.insertaActualizaDepartament(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró, consulte con el administrador.");
			}else {
				salida.put("mensaje", "Se registró correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String, Object>> updateDepartament(@PathVariable("id") Integer id, @RequestBody Department obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Department objSalida = departamentoService.insertaActualizaDepartament(obj);
			if (!departamentoService.existsById(id))
				return new ResponseEntity(new Mensaje("No existe el id departamento"), HttpStatus.NOT_FOUND);
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
