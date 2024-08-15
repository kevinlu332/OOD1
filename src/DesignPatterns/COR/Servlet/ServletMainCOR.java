package DesignPatterns.COR.Servlet;

import java.util.ArrayList;
import java.util.List;


//作业： 实现servletFilter
    //hint: draw a graph and using recursion
//学习内容： you have a request that needs to pass filter 1,2,3
//          then, you do the response with filter 3,2,1
//          consider: do not write filter 1,2,3 repeatly

public class ServletMainCOR {
    public static void main(String[] args) {
//        Msg msg = new Msg();
//        msg.setMsg("hello :) <script> mashibing.com  996 ");

        Request req = new Request();
        req.str = "request";
        Response res = new Response();
        res.str = "response";

        //to-do
        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter()).add(new sensitiveFilter());

        fc.doFilter(req, res);

        System.out.println();
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
    boolean doFilter(Request request, Response response, FilterChain fc);
            // hint: new extra input here: filterChain
}

class HTMLFilter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response, FilterChain fc) {
        //to-do
        System.out.println("did filter 1.");
        fc.doFilter(request, response);
        System.out.println("did filter 1.");
        return false;
    }
}

class Request{
    String str;
}

class Response{
    String str;
}

class sensitiveFilter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response, FilterChain fc) {
        //to-do
        System.out.println("did filter 2.");
        fc.doFilter(request, response);
        System.out.println("did filter 2.");
        return false;
    }
}


class FilterChain  {
    List<Filter> filters = new ArrayList<>();
    public FilterChain add(Filter f){
        filters.add(f);
        return this;
    }
    int index = 0;
    public boolean doFilter(Request request, Response response){
        // to-do;  hint: recursion
        if(index >=filters.size()) return false;
        Filter f = filters.get(index);
        index++;
        return f.doFilter(request, response, this);
    }

}