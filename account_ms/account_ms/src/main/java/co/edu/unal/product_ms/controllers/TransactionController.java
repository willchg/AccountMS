package co.edu.unal.account_ms.controllers;

import co.edu.unal.account_ms.exceptions.AccountNotFoundException;
import co.edu.unal.account_ms.exceptions.InsufficientBalanceException;
import co.edu.unal.account_ms.models.Account;
import co.edu.unal.account_ms.models.Transaction;
import co.edu.unal.account_ms.repositories.AccountRepository;
import co.edu.unal.account_ms.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TransactionController {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionController(AccountRepository accountRepository, TransactionRepository transactionRepository){

        this.accountRepository=accountRepository;
        this.transactionRepository = transactionRepository;
    }
    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction transaction) {
        Account accountOrigin = accountRepository.findById(transaction.getUsernameOrigin()).orElse(null);
        Account accountDestiny = accountRepository.findById(transaction.getUsernameDestiny()).orElse(null);

        if (accountOrigin == null) {
            throw new AccountNotFoundException("No se encontro una cuenta con el username:" + transaction.getUsernameOrigin());

        }
        if (accountDestiny == null) {
            throw new AccountNotFoundException("No se encontro una cuenta con el username:"+transaction.getUsernameDestiny());
        }
        if(accountOrigin.getBalance() < transaction.getValue())
            throw new InsufficientBalanceException("Saldo Insuficiente");

        accountOrigin.setBalance(accountOrigin.getBalance() - transaction.getValue());
        accountOrigin.setLastChange(new Date());
        accountRepository.save(accountOrigin);

        accountDestiny.setBalance(accountDestiny.getBalance() +
                transaction.getValue());
        accountDestiny.setLastChange(new Date());
        accountRepository.save(accountDestiny);

        transaction.setDate(new Date());
        return transactionRepository.save(transaction);
    }
    @GetMapping("/transactions/{username}")
    List<Transaction> userTransaction(@PathVariable String username){
        Account userAccount = accountRepository.findById(username).orElse(null);
        if (userAccount == null)
            throw new AccountNotFoundException("No se encontro una cuenta con el username:" + username);
        List<Transaction> transactionsOrigin = transactionRepository.findByUsernameOrigin(username);
        List<Transaction> transactionsDestiny = transactionRepository.findByUsernameDestiny(username);
        List<Transaction> transactions  =Stream.concat(transactionsOrigin.stream(),transactionsDestiny.stream()).collect(Collectors.toList());

        return transactions;
    }
}


