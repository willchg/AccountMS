package co.edu.unal.account_ms.controllers;

import co.edu.unal.account_ms.exceptions.AccountNotFoundException;
import co.edu.unal.account_ms.models.Account;
import co.edu.unal.account_ms.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @GetMapping("/accounts/{producto}")
    Account getAccount(@PathVariable String producto){
        return accountRepository.findById(producto)
                .orElseThrow( () ->
                        new AccountNotFoundException("No se encotró el producto : " + producto));
    }

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }
}
