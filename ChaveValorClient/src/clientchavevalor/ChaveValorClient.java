package clientchavevalor;

import clientchavevalor.ChaveValorService;
import clientchavevalor.KeyNotFound;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author XPimenta
 */
public class ChaveValorClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            TTransport transport;
            
            transport = new TSocket("localhost", 9090);
            transport.open();
             
             TProtocol protocol = new TBinaryProtocol(transport);
             ChaveValorService.Client client = new ChaveValorService.Client(protocol);
             perform(client);
             
             transport.close();
             
        }catch(TException x){
             x.printStackTrace(); 
        }
        // TODO code application logic here
    }
        
    private static void perform(ChaveValorService.Client client ) throws TException{
        // Primeira interação - Grava um valor na posição 0 
       java.lang.String responseSet1 = client.setKV(0, "Key 0");
        System.out.println(responseSet1);
        
       java.lang.String responseGet1 = client.getKV(0);
        System.out.println(responseGet1);
        
        System.out.println(""); 
         
        //Segunda interação - Grava um valor na posição 1
       java.lang.String responseSet2 = client.setKV(1, "Key 1");
        System.out.println(responseSet2);
        
       java.lang.String responseGet2 = client.getKV(1);
        System.out.println(responseGet2);
        
          System.out.println(""); 
        
        // Terceira interação - Grava um valor na posição 0 e retorna o valor anterior gravado e imprime o valor atual 
       java.lang.String response3 = client.setKV(0, "Key 0 att");      
        System.out.println(response3);
        
       java.lang.String responseGet3 = client.getKV(0);
        System.out.println(responseGet3);
    }
    
}
