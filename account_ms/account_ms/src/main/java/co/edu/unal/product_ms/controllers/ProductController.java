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
    @GetMapping("/accounts/{username}")
    Account getAccount(@PathVariable String username){
        return accountRepository.findById(username)
                .orElseThrow( () ->
                        new AccountNotFoundException("No se encotró una cuenta para el username : " + username));
    }

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }
}
