package DesignPatterns.singleton;

public class Mgr01 {

    private static final Mgr01 INSTANCE = new Mgr01();

    //method1: make the constructor private
    //推荐使用！简单实用！线程安全！
    private Mgr01(){}

    public static Mgr01 getInstance() {
        return INSTANCE;
    }

//    public static void main(String[] args) {
//        Mgr01 m1 = Mgr01.getInstance();
//        Mgr01 m2 = Mgr01.getInstance();
//        System.out.println(m1 == m2);
//    }
}
