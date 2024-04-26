package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.TransactionsDao;
import com.appxbuild.nagpurit.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsServiceImpl implements TransactionsService {


    private TransactionsDao transactionsDao;

    @Autowired
    public TransactionsServiceImpl(TransactionsDao transactionsDao) {
        this.transactionsDao = transactionsDao;
    }

    @Override
    public List<Transactions> findAll() {
        return transactionsDao.findAll();
    }

    @Override
    public Transactions findById(int theId) {
        Optional<Transactions> res = transactionsDao.findById(theId);
        Transactions transactions = null;
        if (res.isPresent()) {
            transactions = res.get();
        } else {
            throw new RuntimeException("Transactions id does not found " + theId);
        }
        return transactions;
    }

    @Override
    public Transactions save(Transactions transactions) {
        return transactionsDao.save(transactions);
    }

    @Override
    public void deleteById(int theId) {
        transactionsDao.deleteById(theId);

    }
}
