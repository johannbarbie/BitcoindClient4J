package me.payjet.bcJsonRpc;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import me.payjet.bcJsonRpc.events.BitcoinDListener;
import me.payjet.bcJsonRpc.events.BlockListener;
import me.payjet.bcJsonRpc.events.WalletListener;
import me.payjet.bcJsonRpc.pojo.Transaction;
import me.payjet.bcJsonRpc.pojo.Transaction.Category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.Base64;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

public class BitcoindClientFactory {

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

	public BitcoindClientFactory(URL url, String username, String password)
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

	public static Map<String,Object> txToMap(Transaction t){
		List<Map<String,Object>> receive = new ArrayList<>();
		List<Map<String,Object>> send = new ArrayList<>();
		for (Transaction tr : t.getDetails()){
			Map<String,Object> m = new HashMap<>();
			m.put("amount", tr.getAmount());
			m.put("fee",tr.getFee());
			m.put("address", tr.getAddress());
			if (tr.getCategory()==Category.RECEIVE){
				receive.add(m);
			}else{
				send.add(m);
			}
		}
		Map<String,Object> rv = new HashMap<>();
		rv.put("confirmations", t.getConfirmations());
		rv.put("txid", t.getTxid());
		rv.put("receive", receive);
		rv.put("send", send);
		rv.put("account",t.getAccount());
		return rv;
	}
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {

		BitcoindClientFactory clientFactory = new BitcoindClientFactory(new URL(
				"http://127.0.0.1:8332/"), "admin", "test9900");

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

		//System.out.println("info: "+ new ObjectMapper().writeValueAsString(client.getinfo()));
		//System.out.println("account: "+ new ObjectMapper().writeValueAsString(client.getaccount("mgPZmH6D2KDbEc5DPYfLoLmuph6qT2LC4p")));
		//System.out.println("accountAddress: "+ new ObjectMapper().writeValueAsString(client.getaccountaddress("test")));
		//System.out.println("listreceivedbyaccount: "+ new ObjectMapper().writeValueAsString(client.listreceivedbyaccount(0, true)));
		//System.out.println("move: "+ new ObjectMapper().writeValueAsString(client.move("test", "test2", 1)));
		//System.out.println("list received by address: "+ new ObjectMapper().writeValueAsString(client.listreceivedbyaddress(0, true)));
		//System.out.println("list addresses: "+ new ObjectMapper().writeValueAsString(client.getaddressesbyaccount("test")));
		//System.out.println("send: "+ new ObjectMapper().writeValueAsString(client.sendfrom("test", "mrGY1vX2oMZaEofgjPMtGZMgbb1Y7M3U48", 0.2)));
		System.out.println("get transaction: "+ new ObjectMapper().writeValueAsString(client.gettransaction("79738a09b123b5f265db2ea6fcf8e0460fabfce44536c854b8a519ea6caffd8f")));
		// System.out.println(new ObjectMapper().writeValueAsString(client.getblock("00000000000000776aa11c57f7b53cd5c48d2069b2525c0489671fbcb09f0db1")));
		// System.out.println("blockcount:" + client.getblockcount());
		// System.out.println("getAccountAddress:" + client.getaccountaddress(""));
		// System.out.println("connectionCount:" + client.getconnectioncount());
		// System.out.println("difficulty:" + client.getdifficulty());
		// System.out.println("generate?:" + client.getgenerate());
		// System.out.println("listAccounts:" + client.listaccounts(0));
		// System.out.println("receivedByAccount:" + new ObjectMapper().writeValueAsString(client.listreceivedbyaccount(0, true)));
		// System.out.println("list Transactions:" + new ObjectMapper().writeValueAsString(client.listtransactions("", 25, 0)));

		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
