package com.accounting.app;

import com.accounting.app.dto.BankRequest;
import com.accounting.app.dto.BankResponse;
import com.accounting.app.dto.mapper.BankMapper;
import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Bank;
import com.accounting.app.repasitory.BankRepository;
import com.accounting.app.service.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServieceTest {

    @Mock
    private BankRepository bankRepository;

    @Mock
    private BankMapper bankMapper;

    @InjectMocks
    private BankService bankService;


    @Test
    void addBank_WhenVAlidationRequest_ReturnBankResponse(){
        BankRequest bankRequest = new BankRequest("بانک ملی", 603L , true);
        Bank bank = new Bank("بانک ملی", 603L);
        BankResponse expectedResault = new BankResponse(1L , "بانک ملی", 603L, true);

        when(bankMapper.toEntity(bankRequest)).thenReturn(bank);
        when(bankRepository.save(bank)).thenReturn(bank);
        when(bankMapper.toResponse(bank)).thenReturn(expectedResault);

        BankResponse bankResponse = bankService.addBank(bankRequest);

        assertThat(bankResponse.getName()).isEqualTo("بانک ملی");
        assertThat(bankResponse.getCode()).isEqualTo(603L);

    }

    @Test
    void searchBank_whenNotFound_ThrowException(){

        when(bankRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> bankService.getBankById(999L)).isInstanceOf(ResourceNotFoundExeption.class);
    }

    @Test
    void searchBank_foundTest(){
        Bank bank = new Bank("melli Bank", 458L);
        BankResponse resault = new BankResponse(1L, "melli Bank", 458L, true);

        when(bankRepository.findById(1L)).thenReturn(Optional.of(bank));
        when(bankMapper.toResponse(bank)).thenReturn(resault);

        BankResponse testResault = bankService.getBankById(1L);

        assertThat(testResault).isEqualTo(resault);
    }
}
