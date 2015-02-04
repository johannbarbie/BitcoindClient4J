/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.silabsoft.cryptocoin.jsonrpc;

/**
 * These RPC calls should be universal with most cryptocoin daemons.
 * @author Silabsoft
 * 
 */
public interface GenericCryptocoinClientInterface {
    public <T> T getinfo();
    public <T> T getblock(String hash);
    
    
}
