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

import io.swagger.annotations.ApiOperation;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/wallet")
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	@GetMapping("/findwallet/{userId}")
	@ApiOperation(value = "Finding a user wallet",
	notes = "Finding a user wallet by using id")
	public Optional<Wallet> findWalletByUserId(@PathVariable("userId") String userId){
		return walletService.findWalletByUserId(userId);
	}
	
	@PostMapping("/create")
	@ApiOperation(value = "Creating a user wallet",
	notes = "Creating a wallet for a particular user")
	public void createWallet(@RequestBody Wallet wallet) {
		walletService.createWallet(wallet);
		
	}
	
	@PostMapping("/rechargewallet")
	@ApiOperation(value = "Recharging user wallet",
	notes = "Recharging a user wallet using userId")
	public void rechargeWallet(@RequestBody WalletRecharge walletRecharge) {
		walletService.rechargeWallet(walletRecharge);
	}
	
	@PostMapping("/transfer")
	@ApiOperation(value = "Transfering amount from user wallet",
	notes = "Finding a user by using id")
	public ResponseEntity<String> processTransaction(@RequestBody Transaction transaction){
		return walletService.orderPayment(transaction);
	}

}
