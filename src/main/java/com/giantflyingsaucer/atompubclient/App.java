/*
 * Original code created by the Apache Abdera team
 * http://abdera.apache.org/
 */
package com.giantflyingsaucer.atompubclient;

import java.util.Date;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.protocol.Response.ResponseType;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.abdera.protocol.client.ClientResponse;

public class App {

    public static void main(String[] args) throws Exception {
        
        Abdera abdera = new Abdera();
        AbderaClient abderaClient = new AbderaClient(abdera);
        Factory factory = abdera.getFactory();
        
        Entry entry = factory.newEntry();
        entry.setId("tag:example.org,2011:foo");
        entry.setTitle("This is the title");
        entry.setUpdated(new Date());
        entry.addAuthor("Chad");
        entry.setContent("Hello World");
        report("The Entry to Post", entry.toString());

        Document<Entry> doc = abderaClient.post("http://localhost:9002/employee", entry).getDocument();
        report("The Created Entry", doc.getRoot().toString());
        
        ClientResponse resp = abderaClient.get("http://localhost:9002/employee");
        if (resp.getType() == ResponseType.SUCCESS) {
            Document<Feed> docFeed = resp.getDocument();
            report("The Returned Feed", docFeed.getRoot().toString());
        } else {
          // Error
        }        
    }

    private static void report(String title, String message) {
        System.out.println("== " + title + " ==");
        if (message != null)
            System.out.println(message);
        System.out.println();
    }
}