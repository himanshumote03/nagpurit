package com.appxbuild.nagpurit.service;


import com.appxbuild.nagpurit.entity.AccountDeletionMsg;

import java.util.List;

public interface AccountDeletionMsgService {
    List<AccountDeletionMsg> findAll();
    AccountDeletionMsg findById(int theId);
    AccountDeletionMsg save(AccountDeletionMsg theAccountDeletionMsg);
    void deleteById(int theId);
}
