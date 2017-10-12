package com.fumec;

import static com.mongodb.client.model.Filters.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class BuscaComplexa {
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
		
		
//		Bson filter = eq("nome", "Neymar da Silva Santos Júnior");
		Bson filter = and(gt("empresa.num_funcionarios", 150000),
				gt("data_nascimento", converteStringParaDate("01/01/1991")),
				lt("data_nascimento", converteStringParaDate("01/01/2000")), gt("salario", 30000000),
				regex("nome", ".*" + Pattern.quote("William Henry Gates III") + ".*"),
				eq("dependentes.email", "marilia@hotmail.com"), eq("funcao.nome", "Gerente de Projetos"),
				eq("empresa.endereco.estado", "SP"));
		
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
	
	public static Date converteStringParaDate(String data) {
		if (data != null && data != "" && data.length() == 10) {
			try {
				return new SimpleDateFormat("dd/MM/yyyyy").parse(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}

