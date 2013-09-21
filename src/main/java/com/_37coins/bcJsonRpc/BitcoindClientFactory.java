package com._37coins.bcJsonRpc;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.Base64;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

public class BitcoindClientFactory {

	final private JsonRpcHttpClient client;

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
	}

	public BitcoindInterface getClient() {
		return ProxyUtil.createClientProxy(
				BitcoindInterface.class.getClassLoader(),
				BitcoindInterface.class, client);
	}

}
