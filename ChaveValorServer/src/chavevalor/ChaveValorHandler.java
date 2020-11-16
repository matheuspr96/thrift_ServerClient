/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chavevalor;

import chavevalor.ChaveValorService;
import chavevalor.KeyNotFound;
import java.util.HashMap;
import org.apache.thrift.TException;

/**
 *
 * @author XPimenta
 */
public class ChaveValorHandler implements ChaveValorService.Iface {
    private HashMap<Integer, String> kv = new HashMap<>();
    
    @Override
    public String getKV(int key) throws KeyNotFound, TException {
        if (kv.containsKey(key)) {
            return kv.get(key);
        } else {
            throw new KeyNotFound();
        }
    }

    @Override
    public String setKV(int key, String value) throws KeyNotFound ,TException {
       String returnMessage = "";
       String oldValue;
        try{
            oldValue = this.getKV(key);
            kv.put(key, value);
            returnMessage = oldValue;          
        }
        catch(KeyNotFound ex){
            kv.put(key, value);
            returnMessage = "Valor inserido com sucesso!";          
        }
        catch(Exception e){
           returnMessage = "Ocorreu um erro inesperado!";
        }
        finally{
            return returnMessage; 
        }      
    }

    @Override
    public void delKV(int key) throws TException {
           kv.remove(key);
    }

   

}
