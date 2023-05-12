package ma.enset.ebank.web;

import ma.enset.ebank.dto.BankAccountRequestDTO;
import ma.enset.ebank.dto.BankAccountResponseDTO;
import ma.enset.ebank.entities.BankAccount;
import ma.enset.ebank.mappers.AccountMapper;
import ma.enset.ebank.reposetories.BankAccountRepository;
import ma.enset.ebank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountReposetory;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountReposetory, AccountService accountService, AccountMapper accountMapper){
        this.bankAccountReposetory = bankAccountReposetory;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountReposetory.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountReposetory.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save (@RequestBody BankAccountRequestDTO bankAccount){
//        if(bankAccount.getId() == null)
//            bankAccount.setId(UUID.randomUUID().toString());
//        if(bankAccount.getCreatedAt() == null)
//            bankAccount.setCreatedAt(new Date());
        return accountService.addAccount(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO update (@PathVariable String id, @RequestBody BankAccountRequestDTO bankAccount){
//        BankAccount account = bankAccountReposetory.findById(id).orElseThrow();
//        if(bankAccount.getBalance() != null)
//            account.setBalance(bankAccount.getBalance());
//        if(bankAccount.getType() != null)
//            account.setType(bankAccount.getType());
//        if(bankAccount.getCurrency() != null)
//            account.setCurrency(bankAccount.getCurrency());
//        if(bankAccount.getCreatedAt() != null)
//            account.setCreatedAt(new Date());
        return accountService.updateAccount(id, bankAccount);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id){
        bankAccountReposetory.deleteById(id);
    }
}