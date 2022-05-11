package com.example.mygson;

import com.example.mygson.bean.Job;
import com.example.mygson.bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObjTest {

       @Test //1.必须在测试方法里面
       public  void testGson(){
           User user = new User("lxd","1234",26,false);
           Gson gson = new Gson();
           //java对象【序列化】为JSON数据===>【toJson方法】
           String json = gson.toJson(user);//fromJson和toJson的区别
           System.out.println("序列化："+json);
            //json【反序列化】为Java对象 ===> 【fromJson方法】
           User u2 = gson.fromJson(json, User.class);//Class
           System.out.println("反序列化："+u2.getUserName()+u2.getPwd());

       }
    //2.嵌套对象的序列化与反序列化
       @Test
     public  void  test2(){
           User user = new User("lxd","1234",26,false);
           Job job = new Job("开发", 8000);
           user.setJob(job);

           Gson gson = new Gson();
           String json = gson.toJson(user);
           System.out.println(json);//JSON嵌套输出
           User u2 = gson.fromJson(json, User.class);
           System.out.println(u2);
           System.out.println(u2.getUserName()+"\n"+u2.getJob());

       }

       //3. Array数组的序列化与反序列化
       @Test
    public  void  test3(){
          User[] a =  new User[3]; //a数组名代表 ,共三个
           a[0] = new User("lxd", "1234", 20, false);
           a[1] = new User("gbc", "123", 18, true);

           Gson gson = new Gson();
           String json = gson.toJson(a);
           System.out.println("序列化数组："+json);

           User[] user = gson.fromJson(json, User[].class);
           System.out.println("数组的反序列化:"+user[0]);//user[0]对应a[0]
           System.out.println("数组的反序列化:"+user[1]);
           System.out.println("数组的反序列化:"+user[2]);
       }
      //  4.List列表集合的序列化与反序列化
      @Test
    public  void test4(){
          List<User> list = new ArrayList<>(); //OBJ对象
          list.add(new User("lsp","111",30,false));//里面穿的是User对象
          list.add(new User("wbd","321",20,true));
          list.add(null);

          Gson gson = new Gson();
          //序列化
          String json = gson.toJson(list);
          System.out.println("Lsit集合："+json);
          //反序列化
          Type type = new TypeToken<List<User>>(){}.getType(); //创建Type对象
          List<User> list2= gson.fromJson(json, type);//Type typeOFT //Object==List<User> //因为泛型的原因，不能使用List.class
          System.out.println(list2.get(0));//获取List集合第0个
          System.out.println(list2.get(1));
          System.out.println(list2.get(2));
      }

    // 5.Map集合的序列化与反序列化
    @Test
    public  void test5(){
        Map<String,User> map = new HashMap(); //字符串类型：Key和 OBJ对象:Value
        map.put("1", new User("lsp","111",30,false));//里面穿的是User对象
        map.put("2", new User("wbd","321",20,true));
        map.put("3",null);

        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(map); //转换
        System.out.println("Map集合："+json);
        //反序列化 Map<String,User>
        Type type = new TypeToken<Map<String,User>>(){}.getType(); //创建Type对象
        Map<String,User> map2= gson.fromJson(json, type);//Type typeOFT //Object==List<User> //因为泛型的原因，不能使用List.class
        System.out.println(map2.get("1"));//获取Map集合key值
        System.out.println(map2.get("2"));
        System.out.println(map2.get("3"));
    }

    // 6.Set集合的序列化与反序列化:从第一个开始
    @Test
    public  void test6(){
        Set<User> set = new HashSet<>(); //字符串类型：Key和 OBJ对象:Value
        set.add(new  User("lsp","111",30,false));//里面穿的是User对象
        set.add( new User("wbd","321",20,true));
        set.add(null);

        Gson gson = new Gson();
        //序列化
        String json = gson.toJson(set); //转换
        System.out.println("Set集合："+json);

        //反序列化 Set<User>
        Type type = new TypeToken< Set<User>  >(){}.getType(); //创建Type对象
        Set<User>   set2 = gson.fromJson(json, type);//Type typeOFT //Object==List<User> //因为泛型的原因，不能使用List.class
        Iterator<User> iterator = set2.iterator();//使用迭代器方法，获取Set
        while (iterator.hasNext()){
            User next = iterator.next();
            System.out.println(next);
        }

    //List<User>
//        System.out.println(set2.get(0));//获取Map集合key值
//        System.out.println(set2.get(1));
//        System.out.println(set2.get(2));
    }
     //7. 让实体类里面的@Excupos生效
    @Test
    public void testAnnotation(){
        User user = new User("lxd", "1230", 21, false);
        user.setTest1(12);
        user.setTest2(10);
        //ExposeAnnotation
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(user);
        System.out.println(json);
        User u2 = gson.fromJson(json, User.class);
        System.out.println(u2);


    }


}
