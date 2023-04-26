package basic.example._generics;

import java.util.ArrayList;
import java.util.Comparator;

public class Employee {
    String name;

    Double sal;

    MyDate birthday;

    Employee(String name, Double sal, MyDate birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public Double getSal() {
        return sal;
    }

    public Employee setSal(Double sal) {
        this.sal = sal;
        return this;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public Employee setBirthday(MyDate birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}';
    }

    static class MyDate implements Comparable<MyDate> {
        private Integer year;

        private Integer month;

        private Integer day;

        public MyDate(Integer year, Integer month, Integer day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public Integer getYear() {
            return year;
        }

        public MyDate setYear(Integer year) {
            this.year = year;
            return this;
        }

        public Integer getMonth() {
            return month;
        }

        public MyDate setMonth(Integer month) {
            this.month = month;
            return this;
        }

        public Integer getDay() {
            return day;
        }

        public MyDate setDay(Integer day) {
            this.day = day;
            return this;
        }

        @Override
        public int compareTo(MyDate another) {
            String date1 = String.format("%d%d%d", this.getYear(), this.getMonth(), this.getDay());
            String date2 = String.format("%d%d%d", another.getYear(), another.getMonth(), another.getDay());
            return date1.compareTo(date2);
        }
    }
}


class Debug {
    public static void main(String[] args) {
        ArrayList<Employee> employeeArrayList = new ArrayList<Employee>();
        Employee employee1 = new Employee("zhangsan", 5000.0, new Employee.MyDate(2021, 10, 19));
        Employee employee2 = new Employee("wangwu", 5001.0, new Employee.MyDate(2021, 11, 19));
        Employee employee3 = new Employee("wangwu", 5002.0, new Employee.MyDate(2021, 10, 17));
        employeeArrayList.add(employee1);
        employeeArrayList.add(employee2);
        employeeArrayList.add(employee3);

        employeeArrayList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                String name1 = o1.getName();
                String name2 = o2.getName();
                int res = name1.compareTo(name2);
                if (res != 0) {
                    return res;
                } else {
                    Employee.MyDate birthday1 = o1.getBirthday();
                    Employee.MyDate birthday2 = o2.getBirthday();
                    return birthday1.compareTo(birthday2);
                }
            }
        });

        System.out.println(employeeArrayList);
    }
}
