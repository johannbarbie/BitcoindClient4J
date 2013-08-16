package me.payjet.bcJsonRpc.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Transaction {
	
	public enum Category {
	    RECEIVE("receive");
	    
	    private String text;

	    Category(String text) {
	      this.text = text;
	    }
	    
	    @JsonValue
	    final String value() {
	        return this.text;
	    }

	    public String getText() {
	      return this.text;
	    }
	    
	    @JsonCreator
	    public static Category fromString(String text) {
	      if (text != null) {
	        for (Category b : Category.values()) {
	          if (text.equalsIgnoreCase(b.text)) {
	            return b;
	          }
	        }
	      }
	      return null;
	    }
	}


	private double fee;
	private double amount;
	private long blockindex;
	private Category category;
	private long confirmations;
	private long time;
	private List<Transaction> details;
	private String address;
    private String txid;
    private long block;
    private String blockhash;
    private String account;
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getBlockindex() {
		return blockindex;
	}
	public void setBlockindex(long blockindex) {
		this.blockindex = blockindex;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public long getConfirmations() {
		return confirmations;
	}
	public void setConfirmations(long confirmations) {
		this.confirmations = confirmations;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public long getBlock() {
		return block;
	}
	public void setBlock(long block) {
		this.block = block;
	}
	public String getBlockhash() {
		return blockhash;
	}
	public void setBlockhash(String blockhash) {
		this.blockhash = blockhash;
	}
	public List<Transaction> getDetails() {
		return details;
	}
	public void setDetails(List<Transaction> details) {
		this.details = details;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
    
    
}
