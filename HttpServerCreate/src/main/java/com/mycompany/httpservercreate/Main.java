/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.httpservercreate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author koushik
 */
public class Main {
    public static int port = 13001;
    public static List<Profile> arr ;
    public static void main(String[] args) {
        // start http server
        arr = new ArrayList<>();
        DemoHttpServer httpServer = new DemoHttpServer();
        httpServer.Start(port);


    }
}
