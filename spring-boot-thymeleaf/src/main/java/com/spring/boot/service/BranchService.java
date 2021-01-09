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
import com.spring.boot.domain.Branch;
import com.spring.boot.dto.BranchDto;
import com.spring.boot.repo.BranchRepository;

@Service
@Transactional
public class BranchService {
	
	@Autowired
	private BranchRepository branchRepository;
	
	public Integer saveBranch(BranchDto branchDto) {
		
		
		Integer savedStatus = 0; // 1 for success and 0 for failed
		
		Branch branch = copyBranchFromDto(branchDto);
		
		Branch savedBranch = branchRepository.save(branch);
		
		if(savedBranch != null) {			
			savedStatus = 1;
		}
		
		return savedStatus;
	}
	
	public List<BranchDto> findAllBranchs() {
			
		List<Branch> branchs = branchRepository.findAllByOrderByBranchNameAsc();

		List<BranchDto> branchDtos = branchs.stream().map(branch -> copyBranchFromEntity(branch)).collect(Collectors.toList());
		
		return branchDtos;
		
	}
	
	public List<BranchDto> findAllBranchs(Long bankId) {
		
		List<Branch> branchs = branchRepository.findByBank_BankId(bankId);

		List<BranchDto> branchDtos = branchs.stream().map(branch -> copyBranchFromEntity(branch)).collect(Collectors.toList());
		
		return branchDtos;
		
	}
	
	public Integer deleteBranchBy(Long branchId) {
		
		Integer deleteStatus = 0; // 1 for success and 0 for failed
				
		Optional<Branch> branchOpt = branchRepository.findById(branchId);
		
		if(! branchOpt.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		Branch branch = branchOpt.get();
		
		branchRepository.delete(branch);
		
		deleteStatus = 1;
		
		return deleteStatus;
	}
	
	
	
	public BranchDto findBranchById(Long branchId) {
		
		Optional<Branch> branchOpt = branchRepository.findById(branchId);
		
		if(! branchOpt.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		Branch branch = branchOpt.get();
		
		BranchDto branchDto =copyBranchFromEntity(branch);
		
		return branchDto;
		
	}
	
	
	
	public BranchDto copyBranchFromEntity(Branch branch) {
		
		BranchDto branchDto = new BranchDto();
		
		BeanUtils.copyProperties(branch, branchDto);
		branchDto.setBranchName(branch.getBranchName());	
		branchDto.setBankId(branch.getBank().getBankId());
		branchDto.setBankName(branch.getBank().getBankName());
		
		return branchDto;
	}
	
	
	public Branch copyBranchFromDto(BranchDto branchDto) {
		
		Branch branch = new Branch();
		BeanUtils.copyProperties(branchDto, branch);
		branch.setBank(getBankById(branchDto.getBankId()));
		return branch;		
	}
	
	private Bank getBankById(Long bankId) {
		
		Bank bank = new Bank();
		bank.setBankId(bankId);
		return bank;
	}

}
