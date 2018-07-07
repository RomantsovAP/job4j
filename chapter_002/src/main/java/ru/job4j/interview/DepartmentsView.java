package ru.job4j.interview;

import java.util.*;

/**
 * Представление списка подразделений
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 02.07.2018
 */

public class DepartmentsView {

    private List<Department> departments;

    public DepartmentsView(List<Department> departments) {
        this.departments = departments;
    }

    public List<Department> getDepartments() {
        return new ArrayList<>(departments);
    }

    private void checkForMissedDepartments(List<Department> departmentList) {
        HashSet<String> checkedCodes = new HashSet<>();
        ListIterator<Department> iterator = departmentList.listIterator();
        while (iterator.hasNext()) {
            Department dep = iterator.next();
            String[] codes = dep.getFullCode().split("\\\\");
            String checkFullCode = codes[0];
            for (int i = 0; i < codes.length - 1; i++) {
                if (!checkedCodes.contains(checkFullCode)) {
                    Department checkingDepartment = new Department(checkFullCode, codes[i]);
                    if (!departmentList.contains(checkingDepartment)) {
                        iterator.add(checkingDepartment);
                    }
                    checkedCodes.add(checkFullCode);
                }
                checkFullCode += "\\" + codes[i + 1];
            }
        }
    }

    /**
     * Возвращает упорядоченный список подразделений
     * @param order - порядок сортировки
     * @return - отсортированный список подразделений
     */
    public List<Department> getSorted(SortingOrder order) {

        List<Department> sortingList = new ArrayList<>(departments);

        checkForMissedDepartments(sortingList);
        sortingList.sort(new Comparator<Department>() {
            @Override
            public int compare(Department o1, Department o2) {
                int result;
                String o1Code = o1.getFullCode();
                String o2Code = o2.getFullCode();
                if (order == SortingOrder.ASCENDING) {
                    result = o1Code.compareTo(o2Code);
                } else {
                    int minLength = (o1Code.length() > o2Code.length()) ? o2Code.length() : o1Code.length();
                    result = o2Code.substring(0,minLength).compareTo(o1Code.substring(0,minLength));
                    result = (result == 0) ? o1Code.length() - o2Code.length() : result;
                }
                return result;
            }
        });
        return sortingList;
    }

    /**
     * Выводит список подразделений в консоль, в указанном порядке
     * @param order - порядок сортировки
     */
    public void printDepartments(SortingOrder order) {
        for (Department dep : getSorted(order)) {
            System.out.println(dep);
        }
    }

    /**
     * Выводит список подразделений в консоль
     */
    public void printDepartments() {
        for (Department dep : departments) {
            System.out.println(dep);
        }
    }
}
