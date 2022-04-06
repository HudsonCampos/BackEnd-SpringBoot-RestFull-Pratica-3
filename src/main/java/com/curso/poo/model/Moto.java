package com.curso.poo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "t_moto")
public class Moto implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_moto", sequenceName = "seq_moto", allocationSize = 1)
	@GeneratedValue(generator = "seq_moto", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	
	
	@Column(name = "cor", nullable = false, length = 250)
	//@NotEmpty(message = "{campo.cor.obrigatorio}") -> caso não tenha VO
	private String cor;
	
	@Column(name = "cilindradas", nullable = false)
	private Integer cilindradas;
}
