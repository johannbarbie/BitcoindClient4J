/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.silabsoft.cryptocoin.jsonrpc.pojo;

import java.util.List;

/**
 * 
 * @author Silabsoft
 */
public class AuxPOW {
    private long size;
    private CoinBaseTX coinbasetx;
    private List<String> coinbaseMerkleBranch;
    private long coinbaseIndex;
    private List<String> chainMerkleBranch;
    private long chainIndex;
    private ParentBlock parent_block;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public CoinBaseTX getCoinbasetx() {
        return coinbasetx;
    }

    public void setCoinbasetx(CoinBaseTX coinbaseTX) {
        this.coinbasetx = coinbasetx;
    }

    public List<String> getCoinbaseMerkleBranch() {
        return coinbaseMerkleBranch;
    }

    public void setCoinbaseMerkleBranch(List<String> coinbaseMerkleBranch) {
        this.coinbaseMerkleBranch = coinbaseMerkleBranch;
    }

    public long getCoinbaseIndex() {
        return coinbaseIndex;
    }

    public void setCoinbaseIndex(long coinbaseIndex) {
        this.coinbaseIndex = coinbaseIndex;
    }

    public List<String> getChainMerkleBranch() {
        return chainMerkleBranch;
    }

    public void setChainMerkleBranch(List<String> chainMerkleBranch) {
        this.chainMerkleBranch = chainMerkleBranch;
    }

    public long getChainIndex() {
        return chainIndex;
    }

    public void setChainIndex(long chainIndex) {
        this.chainIndex = chainIndex;
    }

    public ParentBlock getParent_block() {
        return parent_block;
    }

    public void setParent_block(ParentBlock parent_block) {
        this.parent_block = parent_block;
    }


}
