package com.shopping.walletservice.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.shopping.walletservice.model.Transaction;
import com.shopping.walletservice.model.Wallet;
import com.shopping.walletservice.model.WalletRecharge;

public interface WalletService {
	
	public Optional<Wallet> findWalletByUserId(String userId);
	public void createWallet(Wallet wallet);
	public void rechargeWallet(WalletRecharge recharge);
	public ResponseEntity<String> orderPayment(Transaction transaction);

}
