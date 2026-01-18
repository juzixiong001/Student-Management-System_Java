package com.StudentManagementSystem.Normal;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    static ArrayList<Student> list = new ArrayList<>();

    static {
        list.add(new Student("星期天" , "juzi001" , 19 , "中国"));
        list.add(new Student("王五" , "juzi002" , 32 , "日本"));
        list.add(new Student("李子" , "juzi003" , 9 , "山西"));
    }

    //利用final定义常量，在后续的switch语句中增强代码可读性
    private static final String ADD_STUDENT =  "1";
    private static final String DEL_STUDENT =  "2";
    private static final String UPDATE_STUDENT =  "3";
    private static final String QUERY_STUDENT =  "4";
    private static final String EXIT =  "5";

    public static void startStudentSystem() {

        loop: while (true) {
            System.out.println("-----------------欢迎来到橘子熊学生管理系统-----------------");
            System.out.println("1：添加学生");
            System.out.println("2：删除学生");
            System.out.println("3：修改学生");
            System.out.println("4：查询学生");
            System.out.println("5：退出");
            System.out.println("请输入您需要的服务：");

            Scanner sc = new Scanner(System.in);
            switch (sc.next()) {
                case ADD_STUDENT -> addStudent(list);
                case DEL_STUDENT -> delStudent(list);
                case UPDATE_STUDENT -> updateStudent(list);
                case QUERY_STUDENT -> queryStudent(list);
                case EXIT -> {
                    System.out.println("您已退出橘子熊学生管理系统");
                    break loop;
                    //若不用标记来跳出循环可以使用
                    //System.exit(0); 停止虚拟机运行
                }
                default -> System.out.println("输入错误");
            }
        }


    }



    //添加学生
    public static void addStudent(ArrayList<Student> list) {

        Scanner sc = new Scanner(System.in);
        Student student = new Student();

        while (true) {
            System.out.println("请输入学生id");
            String id = sc.next();
            //检查id的唯一性
            boolean flag = isIdExist(list, id);
            if(flag){
                System.out.println("id已存在,请重新操作");
                return;
            }else {
                //若不存在,则将id赋值给student对象，跳出循环
                student.setId(id);
                break;
            }
        }


        System.out.println("请输入学生姓名");
        student.setName(sc.next());

        System.out.println("请输入学生年龄");
        student.setAge(sc.nextInt());

        System.out.println("请输入学生地址");
        student.setAddress(sc.next());

        list.add(student);
        System.out.println("添加成功");
    }



    //删除学生
    public static void delStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学生id");
        String id = sc.next();
        //检查id是否存在
        boolean flag = isIdExist(list, id);
        if(!flag){
            System.out.println("id不存在,请重新操作");
            return;
        }else {
            for(int i =0 ; i <list.size() ; i++) {
                if(list.get(i).getId().equals(id)) {

                    System.out.println("id为：" +  id +  "的学生信息删除成功");
                    list.remove(i);
                    return;
                }
            }
        }

    }



    //修改学生
    public static void updateStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学生id");
        String id= sc.next();
        //检查id是否存在
        boolean flag = isIdExist(list, id);
        if(!flag){
            System.out.println("id不存在,请重新操作");
            return;
        }else {
            for(int i =0 ; i <list.size() ; i++) {
                if(list.get(i).getId().equals(id)) {
                    System.out.println("请输入要修改的姓名");
                    list.get(i).setName(sc.next());
                    System.out.println("请输入要修改的年龄");
                    list.get(i).setAge(sc.nextInt());
                    System.out.println("请输入要修改的地址");
                    list.get(i).setAddress(sc.next());
                    System.out.println("id为：" + id + "的学员信息修改成功");
                    return;
                }
            }
        }

    }



    //查询学生
    public static void queryStudent(ArrayList<Student> list) {
        if(list.isEmpty()){
            System.out.println("无学生信息,请先添加学生信息!");
            return;
        }
        System.out.println("id\t\t\t姓名\t年龄\t地址");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getId() + "\t" + list.get(i).getName() + "\t" +
                    list.get(i).getAge() + "\t" + list.get(i).getAddress());
        }
        System.out.print("\n");

    }

    //判断id是否唯一
    public static boolean isIdExist(ArrayList<Student> list, String id) {
        //遍历list集合,判断id是否存在
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id)){
                //若存在,返回true
                return true;
            }
        }
        //若不存在,返回false
        return false;
    }

}


