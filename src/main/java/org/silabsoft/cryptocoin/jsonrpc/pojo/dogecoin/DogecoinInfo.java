/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.silabsoft.cryptocoin.jsonrpc.pojo.dogecoin;

import com._37coins.bcJsonRpc.pojo.Info;

/**
 *
 * @author Silabsoft
 */
public class DogecoinInfo extends Info{
    private long unlocked_until;

    public long getUnlocked_until() {
        return unlocked_until;
    }

    public void setUnlocked_until(long unlocked_until) {
        this.unlocked_until = unlocked_until;
    }
    
}
