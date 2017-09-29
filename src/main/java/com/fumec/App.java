package com.fumec;

import java.util.Date;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	MongoClient mongo = new MongoClient("localhost", 27017);
    	
    	MongoDatabase  db = mongo.getDatabase("tcc");
    	
    	MongoCollection<Document> table = db.getCollection("users");
    	
//    	BasicDBObject document = new BasicDBObject();
    	Document doc = new Document();
    	doc.append("nome", "Daniel");
    	doc.append("idade", 23);
    	doc.append("criadoEm", new Date());
    	
    	
    	table.insertOne(doc);
    	
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("nome", "Daniel");
    	
    	System.out.println("Find one:"); 
        
        MongoCursor<Document> cursor = table.find().iterator(); 
        try { 
            while (cursor.hasNext()) { 
                Document cur = (Document) cursor.next(); 
                System.out.println(cur);
            } 
        } finally { 
            cursor.close(); 
        } 
    	
    	
        System.out.println( "Hello World!" );
    }
}
