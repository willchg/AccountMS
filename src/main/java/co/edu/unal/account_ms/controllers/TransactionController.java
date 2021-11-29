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
        Account accountOrigin = accountRepository.findById(transaction.getProductoOrigin()).orElse(null);
        Account accountDestiny = accountRepository.findById(transaction.getProductoOrigin()).orElse(null);

        if (accountOrigin == null) {
            throw new AccountNotFoundException("No se encontró el producto:" + transaction.getProductoOrigin());

        }
        if (accountDestiny == null) {
            throw new AccountNotFoundException("No se encontró el producto:"+transaction.getProductoOrigin());
        }
        if(accountOrigin.getPrecio() < transaction.getValue())
            throw new InsufficientBalanceException("Saldo Insuficiente");

        accountOrigin.setPrecio(accountOrigin.getPrecio() - transaction.getValue());
        accountOrigin.setLastChange(new Date());
        accountRepository.save(accountOrigin);

        accountDestiny.setPrecio(accountDestiny.getPrecio() +
                transaction.getValue());
        accountDestiny.setLastChange(new Date());
        accountRepository.save(accountDestiny);

        transaction.setDate(new Date());
        return transactionRepository.save(transaction);
    }
    @GetMapping("/transactions/{producto}")
    List<Transaction> userTransaction(@PathVariable String producto){
        Account userAccount = accountRepository.findById(producto).orElse(null);
        if (userAccount == null)
            throw new AccountNotFoundException("No se encontró el producto :" + producto);
        List<Transaction> transactionsOrigin = transactionRepository.findByProductoOrigin(producto);
        List<Transaction> transactionsDestiny = transactionRepository.findByProductoDestiny(producto);
        List<Transaction> transactions  =Stream.concat(transactionsOrigin.stream(),transactionsDestiny.stream()).collect(Collectors.toList());

        return transactions;
    }
}


