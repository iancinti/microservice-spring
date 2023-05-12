package ma.enset.ebank.service;

import ma.enset.ebank.dto.BankAccountRequestDTO;
import ma.enset.ebank.dto.BankAccountResponseDTO;
import ma.enset.ebank.entities.BankAccount;
import ma.enset.ebank.mappers.AccountMapper;
import ma.enset.ebank.reposetories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountReposetory;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .type(bankAccountRequestDTO.getType())
                .currency(bankAccountRequestDTO.getCurrency())
                .build();
        BankAccount save = bankAccountReposetory.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(save);
        return bankAccountResponseDTO;
    }

    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO){
        BankAccount account = bankAccountReposetory.findById(id).orElseThrow();
        if(bankAccountRequestDTO.getBalance() != null)
            account.setBalance(bankAccountRequestDTO.getBalance());
        if(bankAccountRequestDTO.getType() != null)
            account.setType(bankAccountRequestDTO.getType());
        if(bankAccountRequestDTO.getCurrency() != null)
            account.setCurrency(bankAccountRequestDTO.getCurrency());
        BankAccount save = bankAccountReposetory.save(account);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(save);
        return bankAccountResponseDTO;
    }
}
