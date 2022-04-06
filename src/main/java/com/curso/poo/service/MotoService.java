package com.curso.poo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

}
