package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.Transactions;
import com.appxbuild.nagpurit.entity.User;
import com.appxbuild.nagpurit.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionRestController {

    private TransactionsService transactionsService;

    @Autowired
    public TransactionRestController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/transactions")
    public List<Transactions> findAll(){
        return transactionsService.findAll();
    }

    @GetMapping("/transactions/{id}")
    public Transactions getTransactions(@PathVariable int id) {
        Transactions transactions = transactionsService.findById(id);
        if (transactions == null) {
            throw new RuntimeException("Transactions id is not found" + id);
        }
        return transactions;
    }

    @GetMapping("/transactions/login/{loginId}")
    public ResponseEntity<Transactions> getTransactionsByLoginId(@PathVariable int loginId) {
        Optional<Transactions> transactions = transactionsService.findAll()
                .stream()
                .filter(u -> u.getLoginDetails() != null && u.getLoginDetails().getId() == loginId)
                .findFirst();

        return transactions.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/transactions")
    public Transactions addTransactions(@RequestBody Transactions transactions) {
        LocalDateTime dt = LocalDateTime.now();
        transactions.setPaymentDate(dt);
        transactions.setCreated(dt);
        Transactions newTransactions = transactionsService.save(transactions);
        return newTransactions;
    }

    @PutMapping("/transactions")
    public Transactions updateTransactions(@RequestBody Transactions transactions) {

        Transactions existingTransactions = transactionsService.findById(transactions.getId());

        if (existingTransactions == null) {
            throw new RuntimeException("Login Detail with id " + transactions.getId() + " not found");
        }
        transactions.setCreated(existingTransactions.getCreated());

        LocalDateTime localDateTime = LocalDateTime.now();
        transactions.setPaymentDate(localDateTime);
        transactions.setModified(localDateTime);

        Transactions newTransactions = transactionsService.save(transactions);
        return newTransactions;
    }

    @DeleteMapping("/transactions/{id}")
    public String deleteTransactions(@PathVariable int id) {
        Transactions transactions = transactionsService.findById(id);

        if (transactions == null) {
            throw new RuntimeException("transactions id is not found " + id);
        }
        transactionsService.deleteById(id);
        return "transactions Message id is deleted" + id;
    }



}
