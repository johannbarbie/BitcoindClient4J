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
public class ScriptPubKey {
    private String asm;
    private String hex;
    private long reqSigs;
    private String type;
    private List<String> addresses;

    public String getAsm() {
        return asm;
    }

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public long getReqSigs() {
        return reqSigs;
    }

    public void setReqSigs(long reqSigs) {
        this.reqSigs = reqSigs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
    
}
