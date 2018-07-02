package ru.job4j.interview;

import java.util.Objects;

/**
 * Подразделение
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 02.07.2018
 */
public class Department {

    private String fullCode;
    private String name;

    /**
     * Для создания нового подраздения нужно указать полный код подразделения и наименование
     * @param fullCode - полный код подразделения, разделитель \
     * @param name - наименование позрадления
     */
    public Department(String fullCode, String name) {
        this.fullCode = fullCode;
        this.name = name;
    }

    public String getFullCode() {
        return fullCode;
    }

    @Override
    public String toString() {
        return "Department{fullCode=" + fullCode + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Department that = (Department) o;
        return Objects.equals(fullCode, that.fullCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fullCode);
    }
}
