package com.shopping.walletservice.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.walletservice.model.Transaction;
import com.shopping.walletservice.model.Wallet;
import com.shopping.walletservice.model.WalletRecharge;
import com.shopping.walletservice.service.WalletService;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/wallet")
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	@GetMapping("/findwallet/{userId}")
	public Optional<Wallet> findWalletByUserId(@PathVariable("userId") String userId){
		return walletService.findWalletByUserId(userId);
	}
	
	@PostMapping("/create")
	public void createWallet(@RequestBody Wallet wallet) {
		walletService.createWallet(wallet);
		
	}
	
	@PostMapping("/rechargewallet")
	public void rechargeWallet(@RequestBody WalletRecharge walletRecharge) {
		walletService.rechargeWallet(walletRecharge);
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<String> processTransaction(@RequestBody Transaction transaction){
		return walletService.orderPayment(transaction);
	}

}
