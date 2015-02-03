/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft;

import com._37coins.bcJsonRpc.CryptocoinClientFactory;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.silabsoft.cryptocoin.jsonrpc.clientInterface.DogecoinDInterface;
import org.silabsoft.cryptocoin.jsonrpc.pojo.dogecoin.DogecoinInfo;

/**
 *
 * @author Silabsoft
 */
public class DogecoinRPCCommandTest {

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
            DogecoinInfo info = client.getinfo();
            System.out.println(info.getBalance()+" "+info.getUnlocked_until());
        } catch (IOException ex) {
            Logger.getLogger(DogecoinBlockNotifyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
