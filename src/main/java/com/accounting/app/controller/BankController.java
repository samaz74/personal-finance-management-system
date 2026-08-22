package com.accounting.app.controller;

import com.accounting.app.dto.BankRequest;
import com.accounting.app.dto.BankResponse;
import com.accounting.app.service.BankService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/")
    public List<BankResponse> getAllBanks(){
        return bankService.getAllBanks();
    }
    @GetMapping("/{id}")
    public BankResponse getBankWithId(@PathVariable Long id){
        return bankService.getBankById(id);
    }
    @GetMapping("/search/{name}")
    public List<BankResponse> searchBank(@PathVariable String name){
        return bankService.getBankByName(name);
    }
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public BankResponse creatBank(@RequestBody BankRequest bankRequest){
        return bankService.addBank(bankRequest);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BankResponse updateBank(@PathVariable Long id, @RequestBody BankRequest bankRequest){
        return bankService.updateBank(id,bankRequest);
    }
    @PatchMapping("/toggle/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void toggleBankStatus(@PathVariable Long id){
        bankService.toggleBank(id);
    }

}
