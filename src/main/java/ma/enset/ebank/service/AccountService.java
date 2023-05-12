package ma.enset.ebank.service;

import ma.enset.ebank.dto.BankAccountRequestDTO;
import ma.enset.ebank.dto.BankAccountResponseDTO;
import ma.enset.ebank.entities.BankAccount;
import ma.enset.ebank.enums.AccountType;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO);
}
