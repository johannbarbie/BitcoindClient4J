package me.payjet.bcJsonRpc.events;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import me.payjet.bcJsonRpc.BitcoinDListener;
import me.payjet.bcJsonRpc.BitcoinQtInterface;
import me.payjet.bcJsonRpc.pojo.Block;

public class BlockListener extends Observable implements Observer {

	final private Observable blockListener;
	final private BitcoinQtInterface client;
	public Thread listener = null;

	public BlockListener(final BitcoinQtInterface client) throws IOException {
		blockListener = new BitcoinDListener(4001);
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

}
