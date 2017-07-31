package com.zl.callback.syn;

/**
 * Created by zl on 2017/5/11.
 */
public class Server {

    /**如果要执行这个方法，必须提供回调接口*/
    public void testTime(CallBack callBack) {
        callBack.execute(); ///进行回调操作
    }
}