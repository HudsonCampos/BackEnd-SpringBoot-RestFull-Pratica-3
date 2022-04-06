		package com.curso.poo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.poo.model.vo.MotoVO;
import com.curso.poo.service.MotoService;

@RestController
@RequestMapping("/moto/v1")
public class MotoController {
	
	@Autowired
	MotoService motoService;
	
	@PostMapping(value = "/created")
	public MotoVO created(@RequestBody @Valid MotoVO motovo) {
		return motoService.created(motovo);
		
	}
	
	@GetMapping(value = "/buscarPorId/{id}")
	public MotoVO buscarPorId(@PathVariable("id") Integer id) {
		MotoVO motovo = motoService.buscarPorId(id);
		return motovo.add(linkTo(methodOn(MotoController.class).buscarPorId(id)).withSelfRel());
	}
}
