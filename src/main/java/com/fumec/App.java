package com.fumec;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {
	public static void main(String[] args) {

		System.out.println("Conectando ao MariaDb");
		
		MongoClient mongo = new MongoClient("localhost", 27017);

		MongoDatabase db = mongo.getDatabase("tcc");

		MongoCollection<Document> table = db.getCollection("funcionarios");

		System.out.println("Conex�o bem sucedida");
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Escreva o n�mero de registros a serem inseridos: ");
		boolean valido = false;
		Integer quantidade = 0;
		while (!valido) {
			try {
				quantidade = reader.nextInt();
				valido = true;
			} catch (NumberFormatException e) {
				System.out.println("Favor digitar um N�MERO");
			}
		}
		reader.close();
		
//		Document doc1 = criarDocumento1();
//		Document doc2 = criarDocumento2();
//		Document doc3 = criarDocumento3();

		System.out.println("Inserindo dados...");
		
		
		
		
		ArrayList<Document> listaDocs = new ArrayList<Document>();
		for (int i = 0; i < quantidade / 3; i++) {
			
			listaDocs.add(criarDocumento1());
			listaDocs.add(criarDocumento2());
			listaDocs.add(criarDocumento3());
//			table.insertOne(doc1);
//			table.insertOne(doc2);
//			table.insertOne(doc3);
		}
		
		long inicio = System.nanoTime();
		
		for(Document doc : listaDocs) {
			table.insertOne(doc);
		}
		
		long tempoDecorrido = System.nanoTime() - inicio;

		double segundos = (double) tempoDecorrido / 1000000000.0;

		System.out.println("Registros inseridos com sucesso!");
		System.out.println("Tempo decorrido(em segundos): " + segundos);

		// table.insertOne(doc);

		// BasicDBObject searchQuery = new BasicDBObject();
		// searchQuery.put("nome", "Daniel");

		// System.out.println("Find one:");

		// MongoCursor<Document> cursor = table.find().iterator();
		// try {
		// while (cursor.hasNext()) {
		// Document cur = (Document) cursor.next();
		// System.out.println(cur);
		// }
		// } finally {
		// cursor.close();
		// }

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

	public static Document criarDocumento1() {

		Document dependente;
		Document empresa;
		Document endereco;
		Document doc;
		
		dependente = new Document()
				.append("nome", "Alok Achkar Peres Petrillo")
				.append("data_nascimento", converteStringParaDate("26/08/1991"))
				.append("email", "alok@artistfactory.com")
				.append("parentesco", "filho");
				
		empresa = new Document()
				.append("nome", "Unisys")
				.append("data_criacao", converteStringParaDate("01/01/1900"))
				.append("num_funcionarios", 100000)
				.append("lucro", 5000000000.50);
		
		endereco = new Document()
				.append("pais", "Brasil")
				.append("estado", "MG")
				.append("cidade", "Belo Horizonte")
				.append("bairro", "Santa Efig�nia")
				.append("rua", "Av. dos Andradas")
				.append("numero", 3000)
				.append("cep", "30260070");
		
    	doc = new Document()
    	.append("nome", "Neymar da Silva Santos J�nior")
    	.append("data_nascimento", converteStringParaDate("05/02/1993"))
    	.append("email", "neymar.jr@gmail.com")
    	.append("ativo", true)
    	.append("salario", 10000000.23)
    	//Dependentes
    	.append("dependentes", dependente)// 
    	//Fun��o
    	.append("funcao", new Document("nome", "Desenvolvedor Java Jr.").append("descricao", "Desenvolver, analisar requisitos e testar aplica��es Java Web," + 
    			" utuilizando conhecimentos em HTML, Javascript, Css, entre outros"))
    	//Empresa
    	.append("empresa", empresa.append("endereco", endereco));
    	
    	return doc;
    }
	
	public static Document criarDocumento2() {

		Document dependente;
		Document empresa;
		Document endereco;
		Document doc;
		
		dependente = new Document()
				.append("nome", "Mar�lia Mendon�a")
				.append("data_nascimento", converteStringParaDate("22/07/1995"))
				.append("email", "marilia@hotmail.com")
				.append("parentesco", "prima");
				
		empresa = new Document()
				.append("nome", "Microsoft")
				.append("data_criacao", converteStringParaDate("02/02/1950"))
				.append("num_funcionarios", 200000)
				.append("lucro", 10000000000.50);
		
		endereco = new Document()
				.append("pais", "Brasil")
				.append("estado", "SP")
				.append("cidade", "S�o Paulo")
				.append("bairro", "Morumbi")
				.append("rua", "Av. Dr. Chucri Zaidan")
				.append("numero", 1240)
				.append("cep", "04711130");
		
    	doc = new Document()
    	.append("nome", "William Henry Gates III")
    	.append("data_nascimento", converteStringParaDate("05/02/1992"))
    	.append("email", "bill@microsoft.com")
    	.append("ativo", false)
    	.append("salario", 50000000.75)
    	//Dependentes
    	.append("dependentes", dependente)// 
				// Fun��o
				.append("funcao", new Document("nome", "Gerente de Projetos").append("descricao",
						"Os gerentes de projetos cultivam as habilidades das pessoas para desenvolver"
								+ " confian�a e comunica��o entre todas as partes interessadas do projeto: seus patrocinadores, aqueles que far�o"
								+ " uso dos resultados do projeto, aqueles que disp�em dos recursos necess�rios e os membros da equipe do projeto"))
    	//Empresa
    	.append("empresa", empresa.append("endereco", endereco));
    	
    	return doc;
    }
	
	public static Document criarDocumento3() {

		Document dependente;
		Document empresa;
		Document endereco;
		Document doc;
		
		dependente = new Document()
				.append("nome", "Larissa de Macedo Machado")
				.append("data_nascimento", converteStringParaDate("30/03/1993"))
				.append("email", "anitta@somlivre.com.br")
				.append("parentesco", "filha");
				
		empresa = new Document()
				.append("nome", "Google")
				.append("data_criacao", converteStringParaDate("02/02/2000"))
				.append("num_funcionarios", 300000)
				.append("lucro", 15000000000.50);
		
		endereco = new Document()
				.append("pais", "Brasil")
				.append("estado", "RJ")
				.append("cidade", "Rio de Janeiro")
				.append("bairro", "Lapa")
				.append("rua", "R. Teixeira de Freitas")
				.append("numero", 31)
				.append("cep", "20021350");
		
    	doc = new Document()
    	.append("nome", "Neymar da Silva Santos J�nior")
    	.append("data_nascimento", converteStringParaDate("05/02/1993"))
    	.append("email", "neymar.jr@gmail.com")
    	.append("ativo", true)
    	.append("salario", 10000000.23)
    	//Dependentes
    	.append("dependentes", dependente)// 
    	//Fun��o
				.append("funcao", new Document("nome", "SCRUM Master").append("descricao",
						"O Scrum Master procura assegurar que a equipe respeite e siga os valores e as pr�ticas\"\r\n"
								+ " do Scrum. Ele tamb�m protege a equipe assegurando que ela n�o se comprometa excessivamente com rela��o �quilo que\"\r\n"
								+ " � capaz de realizar durante um Sprint."))
				//Empresa
    	.append("empresa", empresa.append("endereco", endereco));
    	
    	return doc;
    }
}
