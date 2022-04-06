package com.curso.poo.model.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MotoVO extends RepresentationModel<MotoVO> implements Serializable{


	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	@NotEmpty(message = "{campo.cor.obrigatorio}")
	private String cor;
	
	private Integer cilindradas;

}
