package io.github.fabriciobedin.viagens.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaViagens {
	
	@XmlElement(name="viagem")
	private List<Viagem> viagens;
	
	public ListaViagens(){}
	
	public List<Viagem> getViagens(){
		return viagens;
	}

	public ListaViagens(List<Viagem> viagens) {
		this.viagens = viagens;
	}
	

}
