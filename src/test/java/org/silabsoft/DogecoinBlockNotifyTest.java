/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft;

import com._37coins.bcJsonRpc.CryptocoinClientFactory;
import com._37coins.bcJsonRpc.events.BlockListener;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.silabsoft.cryptocoin.jsonrpc.clientInterface.DogecoinDInterface;
import org.silabsoft.cryptocoin.jsonrpc.pojo.dogecoin.DogecoinBlock;

/**
 *
 * @author Unsignedbyte
 */
public class DogecoinBlockNotifyTest {

    public static final String USER_NAME = "silab_local";
    public static final String PASSWORD = "uF9vfavXOsN7nCpPl2yH";
    public static final String URL = "http://127.0.0.1:2090";

    public static void main(String[] args) {
        try {
            CryptocoinClientFactory clientFactory
                    = new CryptocoinClientFactory(
                            new URL(URL),
                            USER_NAME,
                            PASSWORD);
          DogecoinDInterface client = clientFactory.getClient(DogecoinDInterface.class);
           
            new BlockListener(client).addObserver(new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    DogecoinBlock block = (DogecoinBlock) arg;
                    System.out.println(block.getHeight()+" "+block.getChainwork());
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DogecoinBlockNotifyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
