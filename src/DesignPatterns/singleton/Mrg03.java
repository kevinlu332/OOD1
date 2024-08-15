package DesignPatterns.singleton;

public class Mrg03 {
    private static Mrg03 INSTANCE;

    //懒汉式，要加额外的code来使他 thread-safe
            //因为可能有多个线程来new这个Mgr03

    private Mrg03() {
    }

    public static Mrg03 getINSTANCE(){
        if(INSTANCE == null){
            try{
                Thread.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            INSTANCE = new Mrg03();
        }
        return INSTANCE;
    }

}
