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
public class AddedNode {

    private String addednode;
    private boolean connected;
    private List<NodeAddresses> addresses;

    public String getAddednode() {
        return addednode;
    }

    public void setAddednode(String addednode) {
        this.addednode = addednode;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public List<NodeAddresses> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<NodeAddresses> addresses) {
        this.addresses = addresses;
    }


}
