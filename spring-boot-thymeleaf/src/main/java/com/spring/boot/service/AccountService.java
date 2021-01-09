package com.spring.boot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.boot.domain.Branch;
import com.spring.boot.domain.Account;
import com.spring.boot.dto.AccountDto;
import com.spring.boot.repo.AccountRepository;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Integer saveAccount(AccountDto accountDto) {
		
		
		Integer savedStatus = 0; // 1 for success and 0 for failed
		
		Account account = copyAccountFromDto(accountDto);
		
		Account savedAccount = accountRepository.save(account);
		
		if(savedAccount != null) {			
			savedStatus = 1;
		}
		
		return savedStatus;
	}
	
	public List<AccountDto> findAllAccounts() {
			
		List<Account> accounts = accountRepository.findAllByOrderByAccountNameAsc();

		List<AccountDto> accountDtos = accounts.stream().map(account -> copyAccountFromEntity(account)).collect(Collectors.toList());
		
		return accountDtos;
		
	}
	
	public Integer deleteAccountBy(Long accountId) {
		
		Integer deleteStatus = 0; // 1 for success and 0 for failed
				
		Optional<Account> accountOpt = accountRepository.findById(accountId);
		
		if(! accountOpt.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		Account account = accountOpt.get();
		
		accountRepository.delete(account);
		
		deleteStatus = 1;
		
		return deleteStatus;
	}
	
	
	
	public AccountDto findAccountById(Long accountId) {
		
		Optional<Account> accountOpt = accountRepository.findById(accountId);
		
		if(! accountOpt.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		Account account = accountOpt.get();
		
		AccountDto accountDto =copyAccountFromEntity(account);
		
		return accountDto;
		
	}
	
	
	
	public AccountDto copyAccountFromEntity(Account account) {
		
		AccountDto accountDto = new AccountDto();
		
		BeanUtils.copyProperties(account, accountDto);
		accountDto.setAccountName(account.getAccountName());	
		accountDto.setBranchId(account.getBranch().getId());
		accountDto.setBranchName(account.getBranch().getBranchName());
		return accountDto;
	}
	
	
	public Account copyAccountFromDto(AccountDto accountDto) {
		
		Account account = new Account();
		BeanUtils.copyProperties(accountDto, account);
		account.setBranch(getBranchById(accountDto.getBranchId()));
		return account;		
	}
	
	private Branch getBranchById(Long branchId) {
		
		Branch branch = new Branch();
		branch.setId(branchId);		
		return branch;
	}

}
