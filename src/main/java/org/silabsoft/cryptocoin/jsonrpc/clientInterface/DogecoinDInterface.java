/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.cryptocoin.jsonrpc.clientInterface;

import org.silabsoft.cryptocoin.jsonrpc.pojo.dogecoin.DogecoinBlock;

/**
 *
 * @author Silabsoft
 */
public interface DogecoinDInterface {

    public DogecoinBlock getblock(String blockHash);
}
