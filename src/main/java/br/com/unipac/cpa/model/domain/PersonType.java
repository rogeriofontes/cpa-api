package br.com.unipac.cpa.model.domain;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public enum PersonType {
	LEGAL(1, "Pessoa Juridica"), PHYSICAL(2, "Pessoa Fisica");

	private int ordinal;
	private String name;

	private PersonType(int ordinal, String name) {
		this.ordinal = ordinal;
		this.name = name;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public String getName() {
		return name;
	}

	public String getNameById(int id) {
		for (PersonType personType : PersonType.values()) {
			if (personType.getOrdinal() == id) {
				return personType.getName();
			}
		}
		return null;
	}
	
	public int getByType(String type) {
		return Stream.of(PersonType.values()).filter(p -> p.name().equals(type)).findAny().orElse(PersonType.PHYSICAL).ordinal;
	}
	
	public static List<PersonType> getPersonTypes() {
		return Stream.of(PersonType.values())
				.collect(Collectors.toList());
	}
	
	public static PersonType get(String personType) {
		log.info("Type" + personType);
		return Stream.of(PersonType.values()).filter(p -> p.name().equals(personType)).findAny().orElseThrow(() -> new ResourceNotFoundException("PersonType not Found"));
	}
}
