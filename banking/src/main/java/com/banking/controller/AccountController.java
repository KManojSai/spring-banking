package com.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.entity.Account;
import com.banking.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;

	@GetMapping("/accounts")
	public List<Account> readAccounts() {
		System.out.println("Inside Get mapping");
		List<Account> accounts = service.read();
		for(Account a:accounts) {
			System.out.println(a);			
		}
		return accounts;
	}

	@GetMapping("/accounts/{id}")
	public Account readAccount(@PathVariable String id) {
		Long localId = Long.parseLong(id);
		System.out.println("Entered id: " + localId);
		System.out.println("found this account: " + service.findById(localId));
		return service.findById(localId);
	}

	@PostMapping("/account")
	public void createAccount(@RequestBody Account a) {
		System.out.println("Inside create method: "+a);
		service.create(a);
	}

	@PutMapping("/deposit")
	public void depositBalance(@RequestBody Map<String, Object> requestBody) {
		Long id = Long.valueOf(requestBody.get("id").toString());
        double amount = Double.parseDouble(requestBody.get("amount").toString());
		System.out.println("Entered id: " + id);
		System.out.println("Entered amount: " + amount); 
		service.deposit(id, amount);
	}
	
	@DeleteMapping("/account/{id}")
	public void deleteById(@PathVariable Long id) {
		System.out.println("Entered delete mapping"); 
		service.delete(id);
	}

}
