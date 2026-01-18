package com.StudentManagementSystem.Normal;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//主界面
public class App {

    static ArrayList<User> list = new ArrayList<>();
    //静态代码块
    static { //初始添加用户信息
        list.add(new User("juzixiong" , "123456"  , "111222333444555666" , "12345678912"));

    }


    private static final String REGISTER = "1";
    private static final String LOGIN = "2";
    private static final String FORGET_PASSWORD = "3";
    private static final String EXIT = "4";
    public static void main(String[] args) {
        //主界面的搭建
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作：1.注册，2.登录，3.忘记密码，4.退出");

            String choice = sc.next();
            switch(choice) {
                case REGISTER -> register(list);
                case LOGIN -> login(list);
                case FORGET_PASSWORD -> forgetPassword(list);
                case EXIT -> {
                    System.out.println("谢谢使用,下次再见！");
                    System.exit(0);
                }
                default -> System.out.println("输入错误");
            }
        }
    }

    //注册界面
    private static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        String username,password,personId,phoneNumber;
        //1 用户名校验
        while (true) {
            System.out.println("请输入用户名");
            username = sc.next();
            //用户名长度为3-15位
            //只能是字母加数字的组合，但不能纯数字
            boolean flag1 = checkUsername(username);
            if(!flag1) {
                System.out.println("用户名格式不正确，请重新输入！");
                continue;
            }
            //用户名唯一
            boolean flag2 = contains(list , username);
            if(flag2) {
                //用户名已存在
                System.out.println("用户名" + username + "已存在，请重新输入！");
                continue;
            }else {
                System.out.println("用户名" + username + "可用");
                break;
            }
        }

        //2 密码校验
        while (true) {
            System.out.println("请输入注册的密码");
            password = sc.next();
            System.out.println("请再次输入注册的密码");
            String againPassword = sc.next();
            if(!againPassword.equals(password)) {
                System.out.println("两次密码不一致，请重新输入！");
                continue;
            }else {
                System.out.println("密码设置成功");
                break;
            }
        }

        //3 键盘录入身份证号码
        while (true) {
            System.out.println("请输入身份证号码");
            personId = sc.next();
            boolean flag = checkPersonId(personId);
            if(!flag) {
                System.out.println("身份证号码格式错误，请重新输入！");
                continue;
            }else {
                System.out.println("身份证号码格式正确");
                break;
            }
        }

        //4 键盘录入手机号码
        while (true) {
            System.out.println("请输入手机号码");
            phoneNumber = sc.next();
            boolean flag = checkPhoneNumber(phoneNumber);
            if(!flag) {
                System.out.println("手机号码格式错误，请重新输入！");
                continue;
            }else {
                System.out.println("手机号码格式正确");
                break;
            }
        }

        User u = new User(username, password , personId , phoneNumber);
        list.add(u);
        System.out.println("注册成功");

        //遍历集合
        printList(list);

    }

    private static boolean checkUsername(String username) {
        //用户名长度为3-15位
        int len = username.length();
        if(len < 3 || len > 15) {
            return false;
        }
        //只能是字母加数字的组合
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if(!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9')) {
                return false;
            }
        }
        //不能纯数字
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' ) {
                count++;
                break;
            }
        }
        return count > 0;
    }
    public static boolean contains(ArrayList<User> list , String username) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    private static boolean checkPersonId(String personId) {
        //长度为18
        if(!(personId.length() == 18)) {
            return false;
        }
        //不能以0开头
        if(personId.startsWith("0")) {
            return false;
        }
        //前17位必须为数字
        for (int i = 0; i < 17; i++) {
            char c = personId.charAt(i);
            if(!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        //最后一位可以为x || X
        char c = personId.charAt(17);
        if(!  (c == 'x' || c == 'X' || (c >= '0' && c <= '9')) ) {
            return false;
        }
        return true;
    }
    private static boolean checkPhoneNumber(String phone) {
        //长度为11
        if(!(phone.length() == 11)) {
            return false;
        }
        //不能以0开头
        if(phone.startsWith("0")) {
            return false;
        }
        //必须为数字
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if(!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
    private static void printList(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = new User();
            user = list.get(i);
            System.out.println(user.getUsername() + " " + user.getPassword() + " "
                    + user.getPersonId() + " " + user.getPhoneNumber());

        }
    }


    //登录界面
    public static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        String username;

        for (int i = 0 ; i < 3 ; i++) {
            while (true) {
                System.out.println("请输入用户名");
                username = sc.next();
                if(!contains(list ,username)) {
                    System.out.println("用户名" + username + "不存在，已跳转至首页面，请前往注册");
                    return;
                } else {
                    System.out.println("用户名" + username + "输入成功，请输入密码");
                    break;
                }
            }
            String password = sc.next();

            while (true) {
                String rightCode = getCode();
                System.out.println("验证码为：" + rightCode);
                System.out.println("请输入验证码");
                String code = sc.next();
                if(code.equalsIgnoreCase(rightCode)) {
                    System.out.println("验证码正确");
                    break;
                } else{
                    System.out.println("验证码错误");
                    continue;
                }
            }

            //验证用户名和密码
            //封装思想 : 将零散的数据封装成一个整体，传递参数就传递整体
            User useInfo = new User(username , password,null , null);
            boolean result = checkUserInfo(list,useInfo);
            if(result) {
                System.out.println("登录成功 , 可以开始使用学生管理系统");
                //创建对象，调用方法，启动学生管理系统
                StudentSystem ss = new StudentSystem();
                ss.startStudentSystem();
                break;
            } else {
                if(i == 2) {
                    System.out.println("登录失败 , 用户名或密码错误,当前账号" + username + "登录失败次数过多，已跳转至首页面");
                    return;
                } else {
                    System.out.println("登录失败 , 用户名或密码错误 ,还剩下" + (2 - i) + "次机会");
                }
            }

        }

    }

    public static String getCode () {
        //生成五位数随机验证码
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));
            list.add((char)('A' + i));
        }
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
            Character c = list.get(index);
            sb.append(c);
        }
        int num = r.nextInt(10);
        sb.append(num);
        //但是数字可以出现在任意位置，而不是只在最后一位，所以需要修改字符串，
        //字符串修改需要把字符串以字符数组的形式来修改
        char[] arr = sb.toString().toCharArray();
        int randomIndex = r.nextInt(arr.length);
        char temp = arr[randomIndex];  //把最大索引与一个随机索引交换
        arr[randomIndex] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        //交换完毕再把字符数组转换为字符串
        return new String(arr);
    }    //验证码
    public static boolean checkUserInfo(ArrayList<User> list, User useInfo) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if(user.getUsername().equals(useInfo.getUsername()) && user.getPassword().equals(useInfo.getPassword())) {
                return true;
            }
        }
        return false;
    }  //检查账号密码


    //忘记密码界面
    public static void forgetPassword(ArrayList<User> list) {
        System.out.println("请输入您的用户名");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        if(!contains(list , username)){
            System.out.println("用户名" + username + "不存在，已跳转至首页面，请前往注册");
            return;
        } else{
            System.out.println("查询到用户" + username + "的信息,请输入身份证号码和手机号码进行验证");
        }
        String personId = sc.next();
        String phoneNumber = sc.next();

        int index = findIndex(list , username);
        User user = list.get(index);

        if(!(user.getPersonId().equalsIgnoreCase(personId) && user.getPhoneNumber().equals(phoneNumber))) {
            System.out.println("身份证或手机号码有误，无法修改密码,请回到首页重新操作");
            return ;
        }
        String newPassWord;
        while (true) {
            System.out.println("请输入新密码");
            newPassWord = sc.next();
            System.out.println("请确认新密码");
            String newPassWordAgain = sc.next();
            if(newPassWord.equals(newPassWordAgain)) {
                System.out.println("密码修改成功");
                break;
            } else {
                System.out.println("两次密码输入不一致，请重新修改密码");
                continue;
            }
        }
        user.setPassword(newPassWord);


    }

    private static int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if(user.getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

}
