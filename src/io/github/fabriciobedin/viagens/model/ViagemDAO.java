package io.github.fabriciobedin.viagens.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViagemDAO {
	//hashmap = lista de chaves associado a valores
	private static HashMap<String, Viagem> VIAGENS = new HashMap<String, Viagem>();
	
	static FirebaseOptions options;
	static DatabaseReference ref;
	
	
	static{
		//ler o json de configurações baixado do console.firebase..
		try {
			//lê o json de configurações
			FileInputStream file = new FileInputStream("confs.json");
			
			//inicia as opções de inicialização do firebase
			options = new FirebaseOptions.Builder()
					.setServiceAccount(file)
					.setDatabaseUrl("https://viagens-c8e49.firebaseio.com/")
					.build();
			
			//inicializa a aplicação firebase
			FirebaseApp.initializeApp(options);
			
			//criar o firebase database que é a real referencia ao banco de dados
			FirebaseDatabase database = FirebaseDatabase.getInstance();
			
			//a instancia do reference
			ref = database.getReference();
			
			//criar o listener que escuta qualquer alteração na base de dados realtime
			//o próprio firebase faz push notification para seu servidor avisando que um dado
			//foi adicionado, alterado ou removido automaticamente
			ref.addValueEventListener(new ValueEventListener() {
				
				@Override
				public void onDataChange(DataSnapshot data) {
					// TODO Auto-generated method stub
					System.out.println(data.getValue());
					Iterable<DataSnapshot> iterable = data.getChildren();
					Iterator<DataSnapshot> iterator = iterable.iterator();
					
					VIAGENS.clear();
					while (iterator.hasNext()){
						DataSnapshot internalData = iterator.next();
						System.out.println("laço: "+internalData.getValue());
						Viagem viagem = internalData.getValue(Viagem.class);
						VIAGENS.put(internalData.getKey(), viagem);
					}
				}
				
				@Override
				public void onCancelled(DatabaseError arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			//vai ler apenas uma vez no banco
			//ref.addListenerForSingleValueEvent(new ... igual ao de cima);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//firebase
	}
	
		
	public List<Viagem> listViagens() {
		List<Viagem> listViagens = new ArrayList<Viagem>();
		listViagens.addAll(VIAGENS.values());
		return listViagens;
	}
	
	public void insereViagem(Viagem viagem){
		ref.child(""+VIAGENS.size()).setValue(viagem);
	}
	

	public void removeViagem(String key) {
		ref.child(key).removeValue();
		
	}
	
	public void atualizaViagem(String key, Viagem viagem){
		ref.child(key).setValue(viagem);
	}

}
