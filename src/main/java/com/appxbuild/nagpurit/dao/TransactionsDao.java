package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsDao extends JpaRepository<Transactions, Integer> {

    // no need to write anything

}
