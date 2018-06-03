package ru.job4j.finance;

import java.util.Objects;

/**
 * Банковский счет, requisites - условно номер счета
 * @author AlekseyRomantsov
 * @since 01.06.2018
 * @version 1.0.0.0
 */
public class Account {
    private int amount; // считаем в центах\копейках
    private String requisites;

    public Account(int amount, String requisites) {
        this.amount = amount;
        this.requisites = requisites;
    }

    public int getAmount() {
        return amount;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}
