package me.payjet.bcJsonRpc;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import me.payjet.bcJsonRpc.events.BitcoinDListener;
import me.payjet.bcJsonRpc.events.BlockListener;
import me.payjet.bcJsonRpc.events.WalletListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.Base64;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

public class BitcionClientFactory {

	final private JsonRpcHttpClient client;
	final public Observable alertListener;

	/**
	 * 
	 * for the listener to work bitcoin has to be started like this:
	 * 
	 * ./bitcoind -blocknotify="echo '%s' | nc 127.0.0.1 4001"
	 * -walletnotify="echo '%s' | nc 127.0.0.1 4002"
	 * -alertnotify="echo '%s' | nc 127.0.0.1 4003"
	 * -datadir=/home/ubuntu/bcdData -conf=/home/ubuntu/.bitcoin/bitcoin.conf
	 * -daemon
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @throws IOException
	 */

	public BitcionClientFactory(URL url, String username, String password)
			throws IOException {
		String cred = Base64
				.encodeBytes((username + ":" + password).getBytes());
		Map<String, String> headers = new HashMap<>(1);
		headers.put("Authorization", "Basic " + cred);
		client = new JsonRpcHttpClient(url, headers);
		alertListener = new BitcoinDListener(4003);
		new Thread((Runnable) alertListener, "alertListener").start();
	}

	public BitcoindInterface getClient() {
		return ProxyUtil.createClientProxy(
				BitcoindInterface.class.getClassLoader(),
				BitcoindInterface.class, client);
	}

	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {

		BitcionClientFactory clientFactory = new BitcionClientFactory(new URL(
				"http://54.250.198.109:8332/"), "admin", "test9900");

		final BitcoindInterface client = clientFactory.getClient();

		new BlockListener(client).addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				try {
					System.out.println(new ObjectMapper()
							.writeValueAsString(arg));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		});
		new WalletListener(client).addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				try {
					System.out.println(new ObjectMapper()
							.writeValueAsString(arg));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		});

		clientFactory.alertListener.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				System.out.println("alert: " + arg);
			}
		});

		System.out.println("info: "
				+ new ObjectMapper().writeValueAsString(client.getinfo()));
		// System.out.println(new
		// ObjectMapper().writeValueAsString(client.getblock("00000000000000776aa11c57f7b53cd5c48d2069b2525c0489671fbcb09f0db1")));
		// System.out.println("blockcount:" + client.getblockcount());
		// System.out.println("getAccountAddress:" +
		// client.getaccountaddress(""));
		// System.out.println("connectionCount:" + client.getconnectioncount());
		// System.out.println("difficulty:" + client.getdifficulty());
		// System.out.println("generate?:" + client.getgenerate());
		// System.out.println("listAccounts:" + client.listaccounts(0));
		// System.out.println("receivedByAccount:" + new
		// ObjectMapper().writeValueAsString(client.listreceivedbyaccount(0,
		// true)));
		// System.out.println("list Transactions:" + new
		// ObjectMapper().writeValueAsString(client.listtransactions("", 25,
		// 0)));

		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
