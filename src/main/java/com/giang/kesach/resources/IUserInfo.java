package com.giang.kesach.resources;

import com.giang.kesach.models.Account;
import com.giang.kesach.models.UserInfo;


public interface IUserInfo {

    UserInfo getUserInfo(int id);

    boolean createUserInfo(int accountId,UserInfo userInfo);

    int modifyAccount(int accId);
    boolean isUserInfoExist(int accountId);
}
