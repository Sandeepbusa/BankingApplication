package com.flm.service.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flm.dto.AccountDto;
import com.flm.entity.Account;
import com.flm.mapper.AccountMapper;
import com.flm.repository.AccountRepository;
import com.flm.service.AccountService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	
	private final AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account= accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, Double amount) {
		Account account= accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
	    double total = account.getBalance()+ amount;
	    account.setBalance(total);
		Account savedAccount =  accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount); 
	}

	@Override
	public AccountDto withdraw(Long id, Double amount) {
		Account account= accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public void deleteAccount(Long id) {
		Account account= accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		accountRepository.deleteById(id);
		
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((amount) -> AccountMapper.mapToAccountDto(amount)).collect(Collectors.toList());
	}




}
