package com._37coins.bcJsonRpc.events;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com._37coins.bcJsonRpc.CryptocoinClientFactory;
import com._37coins.bcJsonRpc.pojo.Block;
import org.silabsoft.cryptocoin.jsonrpc.GenericCryptocoinClientInterface;

/**
 *
 * @author Unsignedbyte
 */
public class BlockListener extends Observable implements Observer {

	final private Observable blockListener;
	final private GenericCryptocoinClientInterface client;

    /**
     *
     */
    public Thread listener = null;

    /**
     *
     * @param client
     * @throws IOException
     */
    public BlockListener(final GenericCryptocoinClientInterface client) throws IOException {
		if (CryptocoinClientFactory.blockSocket!=null){
			blockListener = new BitcoinDListener(CryptocoinClientFactory.blockSocket);
		}else{
			blockListener = new BitcoinDListener(4001);
		}
		this.client = client;
	}
        
    /**
     * Used to set port for daemons that are not started with the java application.
     * @param client
     * @param port
     * @throws IOException
     */
    public BlockListener(final GenericCryptocoinClientInterface client,int port) throws IOException {
		
			blockListener = new BitcoinDListener(port);
		
		this.client = client;
	}
	@Override
	public synchronized void addObserver(Observer o) {
		if (null == listener) {
			blockListener.addObserver(this);
			listener = new Thread((Runnable) blockListener, "blockListener");
			listener.start();
		}
		super.addObserver(o);
	}

	@Override
	public void update(Observable o, Object arg) {
		final String value = ((String) arg).trim();
		(new Thread() {
			public void run() {
				Block block = client.getblock(value);
				setChanged();
				notifyObservers(block);
			}
		}).start();
	}
	
    /**
     *
     */
    public void stop(){
		listener.interrupt();
	}

}
