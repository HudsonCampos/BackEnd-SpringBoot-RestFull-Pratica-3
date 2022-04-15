package com.curso.poo.model.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.RepresentationModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper=false)
public class MotoVO extends RepresentationModel<MotoVO> implements Serializable{


	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("unused")
	private Integer id;
	
	@NotEmpty(message = "{campo.cor.obrigatorio}")
	private String cor;
	
	@SuppressWarnings("unused")
	private Integer cilindradas;
	
	private Boolean andando;

}
