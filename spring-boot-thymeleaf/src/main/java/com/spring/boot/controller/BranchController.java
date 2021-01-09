package com.spring.boot.controller;

import java.util.List;

import com.spring.boot.dto.BranchDto;
import com.spring.boot.service.BankService;
import com.spring.boot.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BranchController {

    @Autowired
    private BankService bankService;
    
    @Autowired
    private BranchService branchService;

    @GetMapping("/branch")
    public String branchForm(Model m){
        m.addAttribute("branch", new BranchDto());
        m.addAttribute("bankList",bankService.findAllBanks()); 
        return "/branch";
    }

    @GetMapping("/save-branch")
    public String branchSave(@ModelAttribute BranchDto branchDto){
    	branchService.saveBranch(branchDto);
        return "redirect:/branch";
    }
    @ModelAttribute("branchList")
    public List<BranchDto> getEnquirySourceList(){
        return branchService.findAllBranchs();
    }

    @RequestMapping("/branch-edit/{id}")
    public String findBranch(@PathVariable Long id , Model model) {
        BranchDto branchDto = branchService.findBranchById(id);
        model.addAttribute("branch", branchDto);
        return "/branch";
    }

    @RequestMapping("/branch-delete/{id}")
    public String deleteBranch(@PathVariable Long id) {
        branchService.deleteBranchBy(id);
        return "redirect:/branch";
    }


}
