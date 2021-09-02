package com.shopping.walletservice;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.shopping.walletservice.model.Wallet;
import com.shopping.walletservice.repository.WalletRepository;
import com.shopping.walletservice.service.WalletServiceImpl;

@SpringBootTest
class WalletServiceApplicationTests {

	@Autowired
	private WalletServiceImpl walletServiceImpl;
	
	@MockBean
	private WalletRepository walletRepository;
	
	@Test
	public void createWalletTest() {
		Wallet wallet = new Wallet("23232","7328732",9000);
		  walletServiceImpl.createWallet(wallet);
		  verify(walletRepository,times(1)).save(wallet);
	}

}
