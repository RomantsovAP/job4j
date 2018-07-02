package ru.job4j.interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class DepartmentViewTest {

    @Test
    public void whenAllDepartmentsPresentThenAscendingSortingWorks() {
        List<Department> departmentList = new ArrayList<>();
        DepartmentsView departmentsView = new DepartmentsView(departmentList);

        departmentList.add(new Department("K1\\SK1", "SK1"));
        departmentList.add(new Department("K1\\SK2", "SK2"));
        departmentList.add(new Department("K1\\SK1\\SSK1", "SSK1"));
        departmentList.add(new Department("K1\\SK1\\SSK2", "SSK2"));
        departmentList.add(new Department("K2", "K2"));
        departmentList.add(new Department("K1", "K1"));
        departmentList.add(new Department("K2\\SK1\\SSK1", "SSK1"));
        departmentList.add(new Department("K2\\SK1\\SSK2", "SSK2"));
        departmentList.add(new Department("K2\\SK1", "SK1"));

        List<Department> result = new ArrayList<>();
        result.add(departmentList.get(5));
        result.add(departmentList.get(0));
        result.add(departmentList.get(2));
        result.add(departmentList.get(3));
        result.add(departmentList.get(1));
        result.add(departmentList.get(4));
        result.add(departmentList.get(8));
        result.add(departmentList.get(6));
        result.add(departmentList.get(7));

        List<Department> sortedList = departmentsView.getSorted(SortingOrder.ASCENDING);
        assertThat(result, is(sortedList));
    }

    @Test
    public void whenNotAllDepartmentsPresentThenAscendingSortingWorks() {
        List<Department> departmentList = new ArrayList<>();
        DepartmentsView departmentsView = new DepartmentsView(departmentList);

        departmentList.add(new Department("K1\\SK1", "SK1"));
        departmentList.add(new Department("K1\\SK2", "SK2"));
        departmentList.add(new Department("K1\\SK1\\SSK1", "SSK1"));
        departmentList.add(new Department("K1\\SK1\\SSK2", "SSK2"));
        departmentList.add(new Department("K2", "K2"));
        departmentList.add(new Department("K1", "K1"));
        departmentList.add(new Department("K2\\SK1\\SSK1", "SSK1"));
        departmentList.add(new Department("K2\\SK1\\SSK2", "SSK2"));
        departmentList.add(new Department("K2\\SK1", "SK1"));

        List<Department> result = new ArrayList<>();
        result.add(departmentList.get(5));
        result.add(departmentList.get(0));
        result.add(departmentList.get(2));
        result.add(departmentList.get(3));
        result.add(departmentList.get(1));
        result.add(departmentList.get(4));
        result.add(departmentList.get(8));
        result.add(departmentList.get(6));
        result.add(departmentList.get(7));

        departmentList.remove(8);
        departmentList.remove(5);
        departmentList.remove(4);

        List<Department> sortedList = departmentsView.getSorted(SortingOrder.ASCENDING);
        assertThat(result, is(sortedList));
    }

    @Test
    public void whenNotAllDepartmentsPresentThenDescendingSortingWorks() {
        List<Department> departmentList = new ArrayList<>();
        DepartmentsView departmentsView = new DepartmentsView(departmentList);

        departmentList.add(new Department("K1\\SK1", "SK1"));
        departmentList.add(new Department("K1\\SK2", "SK2"));
        departmentList.add(new Department("K1\\SK1\\SSK1", "SSK1"));
        departmentList.add(new Department("K1\\SK1\\SSK2", "SSK2"));
        departmentList.add(new Department("K2", "K2"));
        departmentList.add(new Department("K1", "K1"));
        departmentList.add(new Department("K2\\SK1\\SSK1", "SSK1"));
        departmentList.add(new Department("K2\\SK1\\SSK2", "SSK2"));
        departmentList.add(new Department("K2\\SK1", "SK1"));

        List<Department> result = new ArrayList<>();
        result.add(departmentList.get(4));
        result.add(departmentList.get(8));
        result.add(departmentList.get(7));
        result.add(departmentList.get(6));
        result.add(departmentList.get(5));
        result.add(departmentList.get(1));
        result.add(departmentList.get(0));
        result.add(departmentList.get(3));
        result.add(departmentList.get(2));

        departmentList.remove(8);
        departmentList.remove(5);
        departmentList.remove(4);

        List<Department> sortedList = departmentsView.getSorted(SortingOrder.DESCENDING);
        assertThat(result, is(sortedList));
    }

}
