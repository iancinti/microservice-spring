package ma.enset.ebank;

import ma.enset.ebank.entities.BankAccount;
import ma.enset.ebank.entities.Customer;
import ma.enset.ebank.enums.AccountType;
import ma.enset.ebank.reposetories.BankAccountRepository;
import ma.enset.ebank.reposetories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {
            Stream.of("aaaa", "bbbb", "cccc", "dddd").forEach(c ->{
                Customer customer = Customer.builder()
                        .name(c)
                        .build();
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(c->{
                for (int i = 0; i < 2 ; i++){
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT: AccountType.SAVING_ACCOUNT)
                            .balance(Math.random() * 5000)
                            .createdAt(new Date())
                            .currency("MAD")
                            .customer(c)
                            .build();
                    bankAccountRepository.save(bankAccount);
                }
            });

        };
    }
}
