package com.shopping.walletservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.walletservice.model.Transaction;
import com.shopping.walletservice.model.Wallet;
import com.shopping.walletservice.model.WalletRecharge;
import com.shopping.walletservice.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private TransactionService transactionService;

	@Override
	public Optional<Wallet> findWalletByUserId(String userId) {
		return walletRepository.findWalletByUserId(userId);
	}

	@Override
	public void createWallet(Wallet wallet) {
		walletRepository.save(wallet);
		
	}
	
	
    //Method to Recharge User Wallet
	@Override
	public void rechargeWallet(WalletRecharge recharge) {
		Optional<Wallet> userWallet = this.findWalletByUserId(recharge.getUserId());
		if(userWallet.isPresent()) {
			Wallet currentWallet = userWallet.get();
			//Adding recharge amount to current Wallet
			double updatedBalance = currentWallet.getBalance()+(recharge.getAmount());
			currentWallet.setBalance(updatedBalance);
			walletRepository.save(currentWallet);
			
		}else {
			System.out.println("No wallet available");
		}
		
	}
	
    //Order Payment Completion
	@Override
	public ResponseEntity<String> orderPayment(Transaction transaction) {
		Optional<Wallet> senderWallet = this.findWalletByUserId(transaction.getSender());
		if(senderWallet.isPresent()) {
			Wallet userWallet = senderWallet.get();
			if((userWallet.getBalance()-transaction.getAmount()) >=0) {
				double updatedBalance = userWallet.getBalance()-transaction.getAmount();
				userWallet.setBalance(updatedBalance);
				String transactionId = transactionService.processTransaction(transaction);
				walletRepository.save(userWallet);
				return ResponseEntity.ok().body(transactionId);
			}
			else {
				return ResponseEntity.badRequest().body("Insufficient Funds");
			}
		}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet Not available for user");
			}
		}
		
	

	
}
