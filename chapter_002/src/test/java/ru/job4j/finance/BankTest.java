package ru.job4j.finance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BankTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream fakeConsole = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.fakeConsole));
    }

    @After
    public void backOutput() {
        System.out.println("execute after method");
    }

    @Test
    public void whenAddSomeUserThenItPresent() {
        Bank bank = new Bank();
        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);
        assertThat(true, is(bank.getAllUserAccounts().containsKey(testUser)));
    }

    @Test
    public void whenDeleteSomeUserThenItDeletes() {
        Bank bank = new Bank();
        bank.addUser(new User("Gleb", "515: 515"));
        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);
        bank.addUser(new User("Fedor", "555: 555"));
        bank.deleteUser(testUser);
        assertThat(false, is(bank.getAllUserAccounts().containsKey(testUser)));
    }

    @Test
    public void whenDeleteSomeUserWithMoneyOnWalletThenItNoDeletes() {
        Bank bank = new Bank();
        bank.addUser(new User("Gleb", "515: 515"));
        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);
        bank.addAccountToUser("12345: 11233", new Account(123, "40102346762736333"));
        bank.addUser(new User("Fedor", "555: 555"));
        bank.deleteUser(testUser);
        assertThat(true, is(bank.getAllUserAccounts().containsKey(testUser)));
        assertThat(fakeConsole.toString(), is("Невозможно удалить пользователя с непустыми счетами" + System.lineSeparator()));
    }

    @Test
    public void whenGetUserAccountsByPassportThenItReturnAllAccounts() {
        Bank bank = new Bank();
        bank.addUser(new User("Gleb", "515: 515"));
        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);
        bank.addAccountToUser("12345: 11233", new Account(123, "40102346762736333"));
        bank.addAccountToUser("12345: 11233", new Account(500, "40102346762736777"));
        bank.addUser(new User("Fedor", "555: 555"));
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(0, "40102346762736333"));
        accounts.add(new Account(0, "40102346762736777"));
        assertThat(accounts, is(bank.getUserAccounts("12345: 11233")));
    }

    @Test
    public void whenTransferMoneyWhenItTransfers() {
        Bank bank = new Bank();
        bank.addUser(new User("Gleb", "515: 515"));
        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);
        bank.addAccountToUser("12345: 11233", new Account(123, "40102346762736333"));
        bank.addAccountToUser("12345: 11233", new Account(500, "40102346762736777"));
        User userfedor = new User("Fedor", "555: 555");
        bank.addUser(userfedor);
        bank.addAccountToUser("555: 555", new Account(500, "40102346762736999"));

        assertThat(true, is(bank.transferMoney(
                "12345: 11233", "40102346762736333", "555: 555", "40102346762736999", 123)));
        assertThat(0, is(bank.getAllUserAccounts().get(testUser).get(0).getAmount()));
        assertThat(623, is(bank.getAllUserAccounts().get(userfedor).get(0).getAmount()));
    }

    @Test
    public void whenCantTransferMoneyWhenItNoTransfers() {
        Bank bank = new Bank();
        bank.addUser(new User("Gleb", "515: 515"));
        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);
        bank.addAccountToUser("12345: 11233", new Account(123, "40102346762736333"));
        bank.addAccountToUser("12345: 11233", new Account(500, "40102346762736777"));
        User userfedor = new User("Fedor", "555: 555");
        bank.addUser(userfedor);
        bank.addAccountToUser("555: 555", new Account(500, "40102346762736999"));

        assertThat(false, is(bank.transferMoney(
                "12345: 11233", "40102346762736333", "555: 555", "40102346762736999", 124)));
        assertThat(123, is(bank.getAllUserAccounts().get(testUser).get(0).getAmount()));
        assertThat(500, is(bank.getAllUserAccounts().get(userfedor).get(0).getAmount()));
        assertThat(fakeConsole.toString(), is("Недостаточно средств" + System.lineSeparator()));
    }

    @Test
    public void whenCantFindUserWhenItNoTransfers() {
        Bank bank = new Bank();
        bank.addUser(new User("Gleb", "515: 515"));
        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);
        bank.addAccountToUser("12345: 11233", new Account(123, "40102346762736333"));
        bank.addAccountToUser("12345: 11233", new Account(500, "40102346762736777"));
        User userfedor = new User("Fedor", "555: 555");
        bank.addUser(userfedor);
        bank.addAccountToUser("555: 555", new Account(500, "40102346762736999"));

        assertThat(false, is(bank.transferMoney(
                "22345: 11233", "40102346762736333", "555: 555", "40102346762736999", 124)));
        assertThat(fakeConsole.toString(), is("Не удалось идентифицировать клиентов по данным паспортам" + System.lineSeparator()));
    }

    @Test
    public void whenCantFindAccountWhenItNoTransfers() {
        Bank bank = new Bank();
        bank.addUser(new User("Gleb", "515: 515"));
        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);
        bank.addAccountToUser("12345: 11233", new Account(123, "40102346762736333"));
        bank.addAccountToUser("12345: 11233", new Account(500, "40102346762736777"));
        User userfedor = new User("Fedor", "555: 555");
        bank.addUser(userfedor);
        bank.addAccountToUser("555: 555", new Account(500, "40102346762736999"));

        assertThat(false, is(bank.transferMoney(
                "12345: 11233", "40102346762736333", "555: 555", "4010234676273699*", 124)));
        assertThat(fakeConsole.toString(), is("Не обнаружено счетов с такими реквизитами" + System.lineSeparator()));
    }

    @Test
    public void whenDeleteSomeAccountThenItDeletes() {
        Bank bank = new Bank();

        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);

        Account account = new Account(0, "401003940934544");
        bank.addAccountToUser("12345: 11233", account);

        bank.deleteAccountFromUser("12345: 11233", account);
        assertThat(0, is(bank.getUserAccounts("12345: 11233").size()));
    }

    @Test
    public void whenDeleteSomeNotEmptyAccountThenItNotDeletes() {
        Bank bank = new Bank();

        User testUser = new User("Ivan", "12345: 11233");
        bank.addUser(testUser);

        Account account = new Account(123, "401003940934544");
        bank.addAccountToUser("12345: 11233", account);

        bank.deleteAccountFromUser("12345: 11233", account);
        assertThat(1, is(bank.getUserAccounts("12345: 11233").size()));
        assertThat(fakeConsole.toString(), is("Невозможно удалить счет, на котором есть остаток денежных средств" + System.lineSeparator()));
    }

}
