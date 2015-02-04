/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.cryptocoin.jsonrpc.clientInterface;

import java.util.List;
import org.silabsoft.cryptocoin.jsonrpc.GenericCryptocoinClientInterface;
import org.silabsoft.cryptocoin.jsonrpc.pojo.AddedNode;

import org.silabsoft.cryptocoin.jsonrpc.pojo.dogecoin.DogecoinBlock;
import org.silabsoft.cryptocoin.jsonrpc.pojo.dogecoin.DogecoinInfo;

/**
 *
 * @author Silabsoft
 */
public interface DogecoinDInterface extends GenericCryptocoinClientInterface {

    @Override
    public DogecoinInfo getinfo();

    @Override
    public DogecoinBlock getblock(String hash);

    public void backupwallet(String destination);

    public String dumpprivkey(String address);

    public List<AddedNode> getaddednodeinfo(boolean dns, String node);

    public List<AddedNode> getaddednodeinfo(boolean b);
}
