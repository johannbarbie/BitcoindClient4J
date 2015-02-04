/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.cryptocoin.jsonrpc.pojo.dogecoin;

import com._37coins.bcJsonRpc.pojo.Block;
import org.silabsoft.cryptocoin.jsonrpc.pojo.AuxPOW;

/**
 *
 * @author Silabsoft
 */
public class DogecoinBlock extends Block {

    private String chainwork;
    private AuxPOW auxpow;

    public String getChainwork() {
        return chainwork;
    }

    public AuxPOW getAuxpow() {
        return auxpow;
    }

    public void setAuxpow(AuxPOW auxpow) {
        this.auxpow = auxpow;
    }

}
