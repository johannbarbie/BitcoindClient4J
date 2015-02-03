/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.silabsoft.cryptocoin.jsonrpc.events.dogecoin;

import com._37coins.bcJsonRpc.CryptocoinClientFactory;
import com._37coins.bcJsonRpc.events.BitcoinDListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import org.silabsoft.cryptocoin.jsonrpc.clientInterface.DogecoinDInterface;
import org.silabsoft.cryptocoin.jsonrpc.pojo.dogecoin.DogecoinBlock;

/**
 *
 * @author Silabsoft
 */
public class DogecoinBlockListener extends Observable implements Observer {

	final private Observable blockListener;
	final private DogecoinDInterface client;
	public Thread listener = null;

	public DogecoinBlockListener(final DogecoinDInterface client) throws IOException {
		if (CryptocoinClientFactory.blockSocket!=null){
			blockListener = new BitcoinDListener(CryptocoinClientFactory.blockSocket);
		}else{
			blockListener = new BitcoinDListener(2091);
		}
		this.client = client;
	}

	@Override
	public synchronized void addObserver(Observer o) {
		if (null == listener) {
			blockListener.addObserver(this);
			listener = new Thread((Runnable) blockListener, "DogecoinBlockListener");
			listener.start();
		}
		super.addObserver(o);
	}

	@Override
	public void update(Observable o, Object arg) {
		final String value = ((String) arg).trim();
		(new Thread() {
			public void run() {
				DogecoinBlock block = client.getblock(value);
				setChanged();
				notifyObservers(block);
			}
		}).start();
	}
	
	public void stop(){
		listener.interrupt();
	}

}
