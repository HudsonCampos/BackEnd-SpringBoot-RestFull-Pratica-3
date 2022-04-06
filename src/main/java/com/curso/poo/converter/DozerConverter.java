package com.curso.poo.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseObject(O origen, Class<D> destination) {
		return mapper.map(origen, destination);
	}
	
	public static <O, D> List<D> parseListObject(List<O> origens, Class<D> destination) {
		List<D> objectsParse = new ArrayList<>();
		for(Object o: origens){
			objectsParse.add(mapper.map(o, destination));
		}
		
		return objectsParse;
	}
}
