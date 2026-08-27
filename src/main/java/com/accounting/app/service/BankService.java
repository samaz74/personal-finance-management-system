package com.accounting.app.service;

import com.accounting.app.dto.BankRequest;
import com.accounting.app.dto.BankResponse;
import com.accounting.app.dto.mapper.BankMapper;
import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Bank;
import com.accounting.app.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {
    private final BankRepository bankRepository;
    private final BankMapper bankMapper;
    public BankService(BankRepository bankRepository, BankMapper bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }
    public Bank getBankByIdentity(Long id) {
        return bankRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("بانک یافت نشد."));
    }
    public BankResponse getBankById(Long id) {
        return bankRepository.findById(id).map(bankMapper::toResponse).orElseThrow(()-> new ResourceNotFoundExeption("بانک یافت نشد."));
    }
    public BankResponse addBank(BankRequest bankRequest) {
        Bank bank = bankMapper.toEntity(bankRequest);
         bankRepository.save(bank);
         return bankMapper.toResponse(bank);
    }
    public List<BankResponse> getAllBanks() {
        return bankRepository.findAll().stream().map(bankMapper::toResponse).collect(Collectors.toList());
    }
    public List<BankResponse> getBankByName(String name) {
        return bankRepository.findBanksByNameContaining(name).stream().map(bankMapper::toResponse).collect(Collectors.toList());
    }
    public BankResponse updateBank(Long id, BankRequest bankRequest) {
        if (bankRepository.existsById(id)){
            Bank bank = bankMapper.toEntity(bankRequest);
            bank.setId(id);
            bankRepository.save(bank);
            return bankMapper.toResponse(bank);
        }else throw new ResourceNotFoundExeption("بانک یافت نشد.");

    }
    public void toggleBank(Long id) {
        Bank bank = bankRepository.findById(id).orElseThrow(()->new ResourceNotFoundExeption("بانک یافت نشد."));
        bank.setActive(!bank.getActive());
        bankRepository.save(bank);
    }
}
