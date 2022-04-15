package com.curso.poo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.curso.poo.converter.DozerConverter;
import com.curso.poo.model.Moto;
import com.curso.poo.model.vo.MotoVO;
import com.curso.poo.repository.RepositoryMoto;

@Service
public class MotoService {

	@Autowired
	RepositoryMoto repositoryMoto;
	
	public MotoVO created(MotoVO motovo) {
		var entity = DozerConverter.parseObject(motovo, Moto.class);
		
		return DozerConverter.parseObject(repositoryMoto.save(entity), MotoVO.class);
	}

	public MotoVO buscarPorId(Integer id) {
		var entity = repositoryMoto.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não localizado!"));
		return DozerConverter.parseObject(entity, MotoVO.class);
	}
	
	@Transactional
	public MotoVO andarMoto(Integer id) {
		repositoryMoto.andarMoto(id);
		var entity = repositoryMoto.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id "+ id + " Não localizado!"));
		return DozerConverter.parseObject(entity, MotoVO.class);
	}

	public Page<MotoVO> motoPage(Pageable pageable) {
		var page = repositoryMoto.findAll(pageable);
		return page.map(this::convertPageObject);
	}	

	// Busca paginada com path e param
	public Page<MotoVO> motoPageByCor(String cor, Pageable pageable) {
		var page = repositoryMoto.buscarMotoPage(cor, pageable);
		return page.map(this::convertPageObject);
	}
	
	public MotoVO convertPageObject(Moto motoEntity) {
		return DozerConverter.parseObject(motoEntity, MotoVO.class);
	}

}
