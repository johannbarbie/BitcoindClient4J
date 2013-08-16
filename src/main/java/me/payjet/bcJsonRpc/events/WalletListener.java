package me.payjet.bcJsonRpc.events;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import me.payjet.bcJsonRpc.BitcoinDListener;
import me.payjet.bcJsonRpc.BitcoinQtInterface;
import me.payjet.bcJsonRpc.pojo.Transaction;

public class WalletListener extends Observable implements Observer {

	final private Observable walletListener;
	final private BitcoinQtInterface client;
	public Thread listener = null;

	public WalletListener(final BitcoinQtInterface client) throws IOException {
		walletListener = new BitcoinDListener(4002);
		this.client = client;
	}

	@Override
	public synchronized void addObserver(Observer o) {
		if (null == listener) {
			walletListener.addObserver(this);
			listener = new Thread((Runnable) walletListener, "walletListener");
			listener.start();
		}
		super.addObserver(o);
	}

	@Override
	public void update(Observable o, Object arg) {
		final String value = ((String) arg).trim();
		(new Thread() {
			public void run() {
				Transaction tx = client.gettransaction(value);
				setChanged();
				notifyObservers(tx);
			}
		}).start();
	}

}
