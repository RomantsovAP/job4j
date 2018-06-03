package ru.job4j.finance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**Банк
 * @author AlekseyRomantsov
 * @since 01.06.2018
 * @version 1.0.0.0
 */
public class Bank {
    private final Map<User, List<Account>> userAccounts = new TreeMap<>();
    private static final User EMPTY_USER = new User("", "");
    private static final Account EMPTY_ACCOUNT = new Account(0, "");

    public void addUser(User user) {
        if (user != null) {
            userAccounts.putIfAbsent(user, new ArrayList<>());
        }
    }

    public Map<User, List<Account>> getAllUserAccounts() {
        return new TreeMap<>(userAccounts);
    }

    private boolean allAccountsAreEmpty(ArrayList<Account> accounts) {
        boolean empty = true;
        for (Account account: accounts) {
            if (account.getAmount() != 0) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    public void deleteUser(User user) {
        if (user != null && userAccounts.containsKey(user)) {
            ArrayList<Account> accounts = (ArrayList<Account>) userAccounts.get(user);
            if (allAccountsAreEmpty(accounts)) {
                userAccounts.remove(user);

            } else {
                //TODO нужно делать нормальные исключения
                System.out.println("Невозможно удалить пользователя с непустыми счетами");
            }
        }
    }

    private User findUserByPassport(String passport) {
        User userFound = Bank.EMPTY_USER;
        for (User user : userAccounts.keySet()) {
            if (passport.equals(user.getPassport())) {
                userFound = user;
                break;
            }
        }
        return userFound;
    }

    public void addAccountToUser(String passport, Account account) {
        User user = findUserByPassport(passport);
        if (user != Bank.EMPTY_USER && userAccounts.containsKey(user)) {
            ArrayList<Account> accounts = (ArrayList<Account>) userAccounts.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            } else {
                //TODO нужно делать нормальные исключения
                System.out.println("У такого клиента уже есть такой счет");
            }
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        User user = findUserByPassport(passport);
        if (user != Bank.EMPTY_USER && userAccounts.containsKey(user) && account != null) {
            ArrayList<Account> accounts = (ArrayList<Account>) userAccounts.get(user);
            accounts.remove(account);
        }
    }

    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = new ArrayList<>();
        User user = findUserByPassport(passport);
        if (user != Bank.EMPTY_USER && userAccounts.containsKey(user)) {
            accounts = userAccounts.get(user);
        }
        return accounts;
    }

    private Account findAccountByRequisite(User user, String requisite) {
        Account accountFound = Bank.EMPTY_ACCOUNT;
        List<Account> accounts = userAccounts.get(user);
        int index = accounts.indexOf(new Account(0, requisite));
        if (index != -1) {
            accountFound = accounts.get(index);
        }
        return accountFound;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, int amount) {
        boolean complete = false;
        User srcUser    = findUserByPassport(srcPassport);
        User destUser   = findUserByPassport(destPassport);
        if (srcUser != Bank.EMPTY_USER && destUser != Bank.EMPTY_USER) {
            Account srcAccount = findAccountByRequisite(srcUser, srcRequisite);
            Account destAccount = findAccountByRequisite(destUser, dstRequisite);
            if (srcAccount != Bank.EMPTY_ACCOUNT && destAccount != Bank.EMPTY_ACCOUNT) {
                if (srcAccount.getAmount() >= amount) {
                    srcAccount.setAmount(srcAccount.getAmount() - amount);
                    destAccount.setAmount(destAccount.getAmount() + amount);
                    complete = true;
                } else {
                    System.out.println("Недостаточно средств");
                }
            } else {
                System.out.println("Не обнаружено счетов с такими реквизитами");
            }
        } else {
            System.out.println("Не удалось идентифицировать клиентов по данным паспортам");
        }

        return complete;
    }



}
