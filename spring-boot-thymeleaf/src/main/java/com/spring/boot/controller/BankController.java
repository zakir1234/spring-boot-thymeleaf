package com.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.boot.domain.Bank;
import com.spring.boot.dto.BankDto;
import com.spring.boot.service.BankService;

@Controller
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	   @RequestMapping("/bank")
	    public String getBank(Model model) {
	        Bank bank = new Bank();
	        model.addAttribute("bank", bank);
	        return "/bank";
	    }

	    @PostMapping("/save-bank")
	    public String saveBank(@ModelAttribute BankDto bankDto) {
	    	bankService.saveBank(bankDto);
	        return "redirect:/bank";
	    }
	    
	    @ModelAttribute("bankList")
	    public List<BankDto> getEnquirySourceList(){
	        return bankService.findAllBanks();
	    }

	    @RequestMapping("/bank-edit/{id}")
	    public String editBank(@PathVariable Long id , Model model) {
	        BankDto bankDto = bankService.findBankById(id);
	        model.addAttribute("bank", bankDto);
	        return "/bank"; //sources.html
	    }

	    @RequestMapping("/bank-delete/{id}")
	    public String deleteBank(@PathVariable Long id) {
	    	bankService.deleteBankBy(id);
	        return "redirect:/bank";
	    }

}
