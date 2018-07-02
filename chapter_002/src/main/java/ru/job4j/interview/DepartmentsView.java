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
        ListIterator<Department> iterator = departmentList.listIterator();
        while (iterator.hasNext()) {
            Department dep = iterator.next();
            String[] codes = dep.getFullCode().split("\\\\");
            String checkFullCode = codes[0];
            for (int i = 0; i < codes.length - 1; i++) {
                Department checkingDepartment = new Department(checkFullCode, codes[i]);
                if (!departmentList.contains(checkingDepartment)) {
                    iterator.add(checkingDepartment);
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
                String[] codes1 = o1.getFullCode().split("\\\\");
                String[] codes2 = o2.getFullCode().split("\\\\");
                int result = 0;
                int min = codes1.length > codes2.length ? codes2.length : codes1.length;
                for (int i = 0; i < min; i++) {
                    if (order != SortingOrder.ASCENDING) {
                        result = (result == 0) ? codes2[i].compareTo(codes1[i]) : result;
                    } else {
                        result = (result == 0) ? codes1[i].compareTo(codes2[i]) : result;
                    }
                }
                result = (result == 0) ? codes1.length - codes2.length : result;
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
