/*
 * Original code created by the Apache Abdera team
 * http://abdera.apache.org/
 */
package com.giantflyingsaucer.atompubclient;

import java.util.Date;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.abdera.protocol.client.ClientResponse;
import org.apache.abdera.protocol.client.RequestOptions;

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

        RequestOptions opts = new RequestOptions();
        opts.setContentType("application/atom+xml;type=entry");

        ClientResponse resp = abderaClient.post("http://localhost:9002/employee", entry, opts);
        report("HTTP STATUS TEXT", resp.getStatusText());
    }

    private static void report(String title, String message) {
        System.out.println("== " + title + " ==");
        if (message != null)
            System.out.println(message);
        System.out.println();
    }
}