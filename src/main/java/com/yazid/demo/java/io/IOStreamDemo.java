package com.yazid.demo.java.io;

import com.yazid.demo.java.io.entity.Employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author YazidChen
 * @date 2019/07/27 0027 10:29
 */
public class IOStreamDemo {

    public void temp() {
        //用户工作目录（项目根目录）
        //用户工作目录（项目根目录）
        System.out.println(System.getProperty("user.dir"));
        //行结束符
        System.out.println(System.getProperty("line.separator"));
        System.out.println("文件分隔符：" + System.getProperty("file.separator"));
        System.out.println("路径分隔符：" + System.getProperty("path.separator"));
    }

    /**
     * 文本文件
     *
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @author YazidChen
     * @date 2019/08/25 0025
     */
    public void TextFile() throws FileNotFoundException, UnsupportedEncodingException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Yazid", 25, "1994-07-06");
        staff[1] = new Employee("Tony", 25, "1994-01-06");
        staff[2] = new Employee("Harry", 24, "1994-09-06");

        try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8")) {
            writeData(staff, out);
        }

        try (Scanner in = new Scanner(new FileInputStream("employee.dat"), "UTF-8")) {
            Employee[] newStaff = readData(in);
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    /**
     * 将所有雇员写出至文件
     *
     * @param employees 雇员
     * @param out       写出流
     */
    private void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);
        for (Employee employee : employees) {
            writeEmployee(out, employee);
        }
    }

    /**
     * 读取所有雇员
     *
     * @param in 输入流
     * @return
     */
    private Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    /**
     * 将单一雇员写出至文件
     *
     * @param out      写出流
     * @param employee 雇员
     */
    private void writeEmployee(PrintWriter out, Employee employee) {
        out.println(employee.getName() + "|" + employee.getAge() + "|" + employee.getBirthday());
    }

    /**
     * 读入单一雇员
     *
     * @param in 输入流
     */
    private Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        Integer age = Integer.parseInt(tokens[1]);
        String birthday = tokens[2];
        return new Employee(name, age, birthday);
    }
}
