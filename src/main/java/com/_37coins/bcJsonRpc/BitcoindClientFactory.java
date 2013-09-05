package com._37coins.bcJsonRpc;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;


import com._37coins.bcJsonRpc.events.BitcoinDListener;
import com._37coins.bcJsonRpc.pojo.Transaction;
import com._37coins.bcJsonRpc.pojo.Transaction.Category;
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
		String account = null;
		for (Transaction tr : t.getDetails()){
			Map<String,Object> m = new HashMap<>();
			m.put("amount", tr.getAmount());
			m.put("fee",tr.getFee());
			m.put("bcAddress", tr.getAddress());
			if (tr.getCategory()==Category.RECEIVE){
				receive.add(m);
				//TODO: fix this
				account = tr.getAccount();
			}else{
				send.add(m);
			}
		}
		Map<String,Object> rv = new HashMap<>();
		rv.put("confirmations", t.getConfirmations());
		rv.put("txid", t.getTxid());
		rv.put("receive", receive);
		rv.put("send", send);
		rv.put("account",account);
		return rv;
	}

}
