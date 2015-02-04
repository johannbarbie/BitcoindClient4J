/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.silabsoft.cryptocoin.jsonrpc.pojo;

/**
 *
 * @author Silabsoft
 */
public class RPCClientException extends Exception{
    private String error;

    public RPCClientException(String error) {
        super(error);
    }
    
    
}
