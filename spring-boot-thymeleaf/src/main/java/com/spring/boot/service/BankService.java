package com.spring.boot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.boot.domain.Bank;
import com.spring.boot.dto.BankDto;
import com.spring.boot.repo.BankRepository;

@Service
@Transactional
public class BankService {
	
	@Autowired
	private BankRepository bankRepository;
	
	public Integer saveBank(BankDto bankDto) {
		
		
		Integer savedStatus = 0; // 1 for success and 0 for failed
		
		Bank bank = copyBankFromDto(bankDto);
		
		Bank savedBank = bankRepository.save(bank);
		
		if(savedBank != null) {			
			savedStatus = 1;
		}
		
		return savedStatus;
	}
	
	public List<BankDto> findAllBanks() {
			
		List<Bank> banks = bankRepository.findAllByOrderByBankNameAsc();

		List<BankDto> bankDtos = banks.stream().map(bank -> copyBankFromEntity(bank)).collect(Collectors.toList());
		
		return bankDtos;
		
	}
	
	public Integer deleteBankBy(Long bankId) {
		
		Integer deleteStatus = 0; // 1 for success and 0 for failed
		
		
		Optional<Bank> bankOpt = bankRepository.findById(bankId);
		
		if(! bankOpt.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		Bank bank = bankOpt.get();
		
		bankRepository.delete(bank);
		
		deleteStatus = 1;
		
		return deleteStatus;
	}
	
	
	
	public BankDto findBankById(Long bankId) {
		
		Optional<Bank> bankOpt = bankRepository.findById(bankId);
		
		if(! bankOpt.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		Bank bank = bankOpt.get();
		
		BankDto bankDto =copyBankFromEntity(bank);
		
		return bankDto;
		
	}
	
	
	
	public BankDto copyBankFromEntity(Bank bank) {
		
		BankDto bankDto = new BankDto();
		
		BeanUtils.copyProperties(bank, bankDto);
		
		return bankDto;
	}
	
	
	public Bank copyBankFromDto(BankDto bankDto) {
		
		Bank bank = new Bank();
		BeanUtils.copyProperties(bankDto, bank);
		return bank;		
	}

}
