/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.cryptocoin.jsonrpc.pojo.dogecoin;

import com._37coins.bcJsonRpc.pojo.Block;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Silabsoft
 */
@JsonIgnoreProperties(ignoreUnknown=true) //ignoring for auxpow
public class DogecoinBlock extends Block{
  private  String chainwork;

    public String getChainwork() {
        return chainwork;
    }


}
