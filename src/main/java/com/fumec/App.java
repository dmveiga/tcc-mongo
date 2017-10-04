package com.fumec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {
    public static void main( String[] args ) {
    	
    	MongoClient mongo = new MongoClient("localhost", 27017);
    	
    	MongoDatabase  db = mongo.getDatabase("tcc");
    	
    	MongoCollection<Document> table = db.getCollection("funcionarios");
    	
    	SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
    	Date data = new Date();
		try {
			data = format.parse("28/05/1993");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Document docDependente1;
		Document docDependente2;
		Document empresa;
		Document endereco;
		Document doc;
		ArrayList<Document> documents = new ArrayList<Document>();
		
		for (int i = 0; i < 1000; i++) {
			docDependente1 = new Document()
					.append("nome", "Clara Medeiros Veiga")
					.append("data_nascimento", new Date())
					.append("email", "clara-veiga@hotmail.com")
					.append("parentesco", "Irmã");
					
			docDependente2 = new Document()
					.append("nome", "Joziane Medeiros Veiga")
					.append("data_nascimento", new Date())
					.append("email", "joziane.medeiros@outlook.com")
					.append("parentesco", "Mãe");
			
			empresa = new Document()
					.append("nome", "Unisys")
					.append("data_criacao", new Date())
					.append("num_funcionarios", 100000)
					.append("lucro", 5000000000.50);
			
			endereco = new Document()
					.append("pais", "Unisys")
					.append("estado", "MG")
					.append("cidade", "Lagoa Santa")
					.append("bairro", "Várzea")
					.append("rua", "R. Sol")
					.append("numero", 140)
					.append("cep", "33400000");
			
//			BasicDBObject document = new BasicDBObject();
	    	doc = new Document()
	    	.append("nome", "Daniel Medeiros Veiga")
	    	.append("data_nascimento", data)
	    	.append("email", "medeirosveigadaniel@gmail.com")
	    	.append("ativo", true)
	    	.append("salario", 2200.20)
	    	//Dependentes
	    	.append("dependentes", Arrays.asList(docDependente1, docDependente2))// 
	    	//Função
	    	.append("funcao", new Document("nome", "Desenvolvedor Java").append("descricao", "Desenvolver e Testar aplicações Java"))
	    	//Empresa
	    	.append("empresa", empresa.append("endereco", endereco));
	    	
	    	documents.add(doc);
		}
    	
		long inicio = System.nanoTime();
		table.insertMany(documents);
		long tempoDecorrido = System.nanoTime() - inicio;

		double segundos = (double) tempoDecorrido / 1000000000.0;

		System.out.println("Registros inseridos com sucesso!");
		System.out.println("Tempo decorrido(em segundos): " + segundos);
		
		

//    	table.insertOne(doc);
    	
//    	BasicDBObject searchQuery = new BasicDBObject();
//    	searchQuery.put("nome", "Daniel");
    	
//    	System.out.println("Find one:"); 
        
//        MongoCursor<Document> cursor = table.find().iterator(); 
//        try { 
//            while (cursor.hasNext()) { 
//                Document cur = (Document) cursor.next(); 
//                System.out.println(cur);
//            } 
//        } finally { 
//            cursor.close(); 
//        } 

        mongo.close();
        
    }
}
