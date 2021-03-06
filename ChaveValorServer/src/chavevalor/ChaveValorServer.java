/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chavevalor;

/**
 *
 * @author XPimenta
 */

import chavevalor.ChaveValorService;
import chavevalor.ChaveValorHandler;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class ChaveValorServer {
    public static ChaveValorHandler handler;
    public static ChaveValorService.Processor processor;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   
        try{
            handler = new ChaveValorHandler();
            processor = new ChaveValorService.Processor(handler);
           
        
           Runnable simple = new Runnable() {
            public void run() {
              simple(processor);
            }
          };      
     
             new Thread(simple).start();
        }catch(Exception x){
            x.printStackTrace();
        }
    }
    
    public static void simple(ChaveValorService.Processor processor){
        try{
            //Criando Servidor
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            
            //Startando servidor
            System.out.println("Servidor simples iniciado...");
            server.serve();
            
        }catch(Exception e){
            //Tratamento de erros
            e.printStackTrace();
        }
    }
}
