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
public class VIn {
   private String coinbase;
    private long sequence;

    public String getCoinbase() {
        return coinbase;
    }

    public long getSequence() {
        return sequence;
    }
    
} 
