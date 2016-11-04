package io.github.fabriciobedin.viagens.ws;

import java.util.List;

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

}
