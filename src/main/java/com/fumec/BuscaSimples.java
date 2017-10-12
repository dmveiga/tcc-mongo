package com.fumec;

import static com.mongodb.client.model.Filters.eq;

import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class BuscaSimples {
	public static void main(String[] args) {

		System.out.println("Conectando ao MongoDb");

		MongoClient mongo = new MongoClient("localhost", 27017);

		MongoDatabase db = mongo.getDatabase("tcc");

		MongoCollection<Document> colecao = db.getCollection("funcionarios");

		System.out.println("Conexão bem sucedida");

		Scanner reader = new Scanner(System.in);
		System.out.println("Escreva o número de repetições de busca a serem realizadas: ");
		boolean valido = false;
		Integer quantidade = 0;
		while (!valido) {
			try {
				quantidade = reader.nextInt();
				valido = true;
			} catch (NumberFormatException e) {
				System.out.println("Favor digitar um NÚMERO");
			}
		}
		reader.close();
		
		
		Bson filter = eq("nome", "Neymar da Silva Santos Júnior");
		
		System.out.println("Pesquisando...");

		long inicio = System.nanoTime();
//		List<Document> all = colecao.find(filter).into(new ArrayList<Document>());
		MongoCursor<Document> cursor = colecao.find(filter).iterator();
		for (int i = 0; i < quantidade; i++) {
//			System.out.println(i);
			cursor = colecao.find(filter).iterator();
		}
		
		long tempoDecorrido = System.nanoTime() - inicio;
		double segundos = (double) tempoDecorrido / 1000000000.0;
		
		
		try {
			if (cursor.hasNext()) {
				Document cur = (Document) cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}
		
		
		
		System.out.println("Registros pesquisados com sucesso!");
		System.out.println("Tempo decorrido(em segundos): " + segundos);
		
		mongo.close();

	}
}
