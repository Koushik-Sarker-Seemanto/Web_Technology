/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.httpservercreate;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author koushik
 */
public class PostClass implements HttpHandler{

    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Served by /echoPost handler...");
        // parse request
        Map<String, Object> parameters = new HashMap<String, Object>();
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();
        System.out.println(query);
        JSONObject js = new JSONObject(query.toString());
        String name = js.getString("name");
        String phone = js.getString("phone");
        Profile info = new Profile(name,phone);
        Main.arr.add(info);
        System.out.println("Name "+name);
        System.out.println("Phone "+phone);
        //parseQuery(query, parameters);
        // send response
        String response = name+" "+phone;
//			for (String key : parameters.keySet())
//				response += key + " = " + parameters.get(key) + "\n";
//                        System.out.println(response);
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
    
}
