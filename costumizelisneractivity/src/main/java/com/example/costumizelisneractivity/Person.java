package com.example.costumizelisneractivity;

public class Person {

    private String data = "This is My Listener!";


    //1.1首先定义一个私有的、空的监听器对象，用来接收传递进来的事件监听器
    private PersonListener mPersonlistener;

    //1.2将传递进来的事件监听器付给1.1中的listener
    // 注册时用 setOnPersonListener(this),this指代当前 Activity
    //由于Activity implements Person.PersonListener 相当于PersonListener的子类
    public void setOnPersonListener(PersonListener listener){
            this.mPersonlistener = listener;
    }

    //1.3判断 mPersonlistener 是否为 null,如果不为 null，则执行监听器中的方法，
    //否则
    public void use(){
        if(mPersonlistener!= null) {
            this.mPersonlistener.onMyWay(data);
        }
    }

    public interface PersonListener{
        public void onMyWay(String str);
    }
}
