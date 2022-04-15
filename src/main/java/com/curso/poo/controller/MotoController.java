package com.curso.poo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.poo.model.vo.MotoVO;
import com.curso.poo.service.MotoService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/moto/v1")
@Api(value = "Api moto vrum", tags = "Aplicação online moto vrum")
public class MotoController {
	
	@Autowired
	MotoService motoService;
	
	@PostMapping(value = "/created", 
			produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	public MotoVO created(@RequestBody @Valid MotoVO motovo) {
		return motoService.created(motovo);
		
	}
	
	@GetMapping(value = "/buscarPorId/{id}",
			produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	public MotoVO buscarPorId(@PathVariable("id") Integer id) {
		MotoVO motovo = motoService.buscarPorId(id);
		return motovo.add(linkTo(methodOn(MotoController.class).buscarPorId(id)).withSelfRel());
	}
	
	@PatchMapping(value = "/andarMoto/{id}", produces = {"application/json", "application/xml"})
	public MotoVO andarMoto(@PathVariable("id") Integer id) {
		var motoVo = motoService.andarMoto(id);
		return motoVo.add(linkTo(methodOn(MotoController.class).buscarPorId(id)).withSelfRel());
	}
	
	@GetMapping(value = "/pageMoto",
			produces = {"application/json", "application/xml"})
	public Page<MotoVO> motoPage(
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "limit", defaultValue = "4") Integer limit,
				@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		
		var sortDirection = "desc".equalsIgnoreCase(direction)?Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		
		Page<MotoVO> motovoPage = motoService.motoPage(pageable);
		
		motovoPage
			.stream()
			.forEach(mt -> mt.add(linkTo(methodOn(MotoController.class).buscarPorId(mt.getId())).withSelfRel()));
		
		
		return motovoPage;
		
	}	
	
	@GetMapping(value = "/pageMotoCor/{cor}",
			produces = {"application/json", "application/xml"})
	public ResponseEntity<Page<MotoVO>> motoPageByCor(
				@PathVariable("cor") String cor,
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "limit", defaultValue = "4") Integer limit,
				@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		
		var sortDirection = "desc".equalsIgnoreCase(direction)?Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		
		Page<MotoVO> motovoPageByCor = motoService.motoPageByCor(cor, pageable);
		
		motovoPageByCor
			.stream()
			.forEach(mt -> mt.add(linkTo(methodOn(MotoController.class).buscarPorId(mt.getId())).withSelfRel()));
		
		
		return ResponseEntity.ok().body(motovoPageByCor);
		
	}	

	
}
