package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.entity.Account;
import com.banking.repository.AccountRepo;

@Service
public class AccountService {

	@Autowired
	private AccountRepo repo;

	public List<Account> read() {
		return repo.findAll();
	}

	public void create(Account a) {
		repo.save(a);
	}
	
	public Account findById(Long id) {
		return repo.findById(id).orElseThrow();
	}
	
	public void deposit(Long id,double amount) {
		Account account = repo.findById(id).orElseThrow();
		System.out.println("found this account: "+account);
		double balance = amount+account.getBalance();
		account.setBalance(balance);
		repo.save(account);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
