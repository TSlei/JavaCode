package com.zl.callback.syn;

/**
 * Created by zl on 2017/5/11.
 */
public class Client implements CallBack{
    Server server = new Server();

    /**如果契约，必须定义一个规定的函数，以实现被调
     * 这个方法，本对象一般不执行，只用于回调
     */
    public void execute(){
        System.out.println("我被回调了");
    }

    /**将本对象以接口的方式传递过去，server处理完后执行接口中的方法，实现回调*/
    public void callServer(){
        server.testTime(this);
    }
}