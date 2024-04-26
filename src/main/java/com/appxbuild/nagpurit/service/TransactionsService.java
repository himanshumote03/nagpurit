package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.Transactions;

import java.util.List;

public interface TransactionsService {

    List<Transactions> findAll();

    Transactions findById(int theId);

    Transactions save(Transactions transactions);

    void deleteById(int theId);

}
