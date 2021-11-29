package co.edu.unal.account_ms.repositories;

import co.edu.unal.account_ms.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account,String> {}
