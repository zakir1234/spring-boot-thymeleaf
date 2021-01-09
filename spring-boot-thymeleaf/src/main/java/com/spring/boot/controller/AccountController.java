package com.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spring.boot.domain.Account;
import com.spring.boot.dto.AccountDto;
import com.spring.boot.dto.BankDto;
import com.spring.boot.dto.BranchDto;
import com.spring.boot.repo.AccountRepository;
import com.spring.boot.service.AccountService;
import com.spring.boot.service.BankService;
import com.spring.boot.service.BranchService;

@Controller
public class AccountController {
	

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private BankService bankService;
	
	
    @GetMapping("/account-form")
    public String accountForm(Model m) {
        m.addAttribute("account", new AccountDto());
        
        List<BankDto> banks = bankService.findAllBanks();
                
        List<BranchDto> branches = new ArrayList<>();         

        m.addAttribute("banks", banks);      
        
        m.addAttribute("branches", branches);
        return "/account-form";
    }
    
    
    @GetMapping("/branches/by/{bankId}")
    @ResponseBody
    public List<BranchDto> getBranchesByBankId(@PathVariable Long bankId){
    	
    	List<BranchDto> branches = branchService.findAllBranchs(bankId);
    	
    	return branches;
    	
    }
    

    @GetMapping("/save-account")
    public String saveAccount(@ModelAttribute AccountDto account) {

    	accountService.saveAccount(account);
        return "redirect:/account-form";
    }
    @RequestMapping("/account-edit/{id}")
    public String editAccount(@PathVariable Long id , Model model) {
        Account cmd = accountRepository.findById(id).get();
        model.addAttribute("cmd", cmd);
        return "/account-form"; //sources.html
    }
    @RequestMapping("/account-delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
        return "redirect:/account-form";
    }
    
    @ModelAttribute("accList")
    public List<AccountDto> accountList(){
        return accountService.findAllAccounts();
    }

}
