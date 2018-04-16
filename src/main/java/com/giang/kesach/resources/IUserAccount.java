package com.giang.kesach.resources;

import com.giang.kesach.models.*;

import java.util.List;

public interface IUserAccount {
    Account getAccountByName(String name);
    Account getAccountById(int id);
    List<Account> getAllAccount();
   int checkLoginInfo(Account account);
   boolean isUserExist(String username);

   int createNewAccount(Account account);

    boolean deleteAccount(Account account);

   int changeAccountPassword(Account account);
   int createNewBookShelf(int accountId,BookShelf bookShelf);
   void deleteBookShelf(int accountId,int shelfId);
    List<BookShelf> showAllBookShelf(int accountId);

}
