package com.flm.service;

import java.util.List;

import com.flm.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);	
	
	AccountDto deposit(Long id, Double amount);
	
	AccountDto withdraw(Long id, Double amount	);	
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);

}
