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
public class CoinBaseTX {
    private String txid;
    private long version;
    private long locktime;
    private List<VIn> vin;
    private List<VOut> vout;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getLocktime() {
        return locktime;
    }

    public void setLocktime(long locktime) {
        this.locktime = locktime;
    }

    public List<VIn> getVin() {
        return vin;
    }

    public void setVin(List<VIn> vin) {
        this.vin = vin;
    }

    public List<VOut> getVout() {
        return vout;
    }

    public void setVout(List<VOut> vout) {
        this.vout = vout;
    }
    
}
