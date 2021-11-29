package co.edu.unal.account_ms.repositories;

import co.edu.unal.account_ms.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction,String> {
    List<Transaction> findByProductoOrigin(String productoOrigin);
    List<Transaction> findByProductoDestiny(String productoDestiny);
}
