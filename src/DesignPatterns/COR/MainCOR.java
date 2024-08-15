package DesignPatterns.COR;
import java.util.ArrayList;
import java.util.List;

//学习内容： 1. how to write filter COR
//          2.how to stop at a specific filter.

public class MainCOR {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("hello :) <script> mashibing.com  996 ");
        FilterChain fc1 = new FilterChain();
        fc1.add(new HTMLFilter()).add(new URLFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new sensitiveFilter()).add(new FaceFilter());
        //connect a chain to a chain
        fc1.add(fc2);

        fc1.doFilter(msg);
        System.out.println(msg);
    }
}

class Msg{
    String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface Filter{
    boolean doFilter(Msg m);
}

class HTMLFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace("<", "[");
        r = r.replace(">", "]");
        m.setMsg(r);
        return true;
    }
}

class sensitiveFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace("996", "995");
        m.setMsg(r);
        return false;
    }
}

class FaceFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace(":)", "^V^");
        m.setMsg(r);
        return true;
    }
}

class URLFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace("mashibing.com", "https://mashibing.com");
        m.setMsg(r);
        return true;
    }
}

class FilterChain implements Filter{
    List<Filter> filters = new ArrayList<>();
    public FilterChain add(Filter f){
        filters.add(f);
        return this;
    }
    public boolean doFilter(Msg m){
        for(Filter f : filters){
            boolean TF = f.doFilter(m);
            if(!TF) return  false;
        }
        return true;
    }
}