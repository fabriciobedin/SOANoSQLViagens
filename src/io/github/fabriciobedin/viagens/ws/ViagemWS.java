package io.github.fabriciobedin.viagens.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import io.github.fabriciobedin.viagens.model.ListaViagens;
import io.github.fabriciobedin.viagens.model.Viagem;
import io.github.fabriciobedin.viagens.model.ViagemDAO;



@WebService
public class ViagemWS {
	
	private ViagemDAO dao = new ViagemDAO();

	public ListaViagens todasViagens(){
		List<Viagem> viagens = dao.listViagens();
		return new ListaViagens(viagens);
	}
	
	public Viagem insereViagem(@WebParam(name="viagem") Viagem viagem){
		dao.insereViagem(viagem);
		return viagem;
	}
	
	public String deletaViagem(@WebParam(name="key") String key){
		dao.removeViagem(key);
		return key + " removida!";
	}
	
	public Viagem editaViagem(@WebParam(name="key") String key, 
			@WebParam(name="viagem") Viagem viagem){
		dao.atualizaViagem(key, viagem);
		return viagem;
	}
	

}
