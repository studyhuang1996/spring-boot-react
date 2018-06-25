/*
 * @(#) JsonUtil
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-13 16:24:58
 */

package com.sunsharing.kaohe.utils;




import com.sunsharing.kaohe.pojo.User;

import org.assertj.core.util.Lists;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //xuleibua
        //对象的字段全部列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);
       //取消默认转换成timestamps的形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,false);
        //忽列空bean转json
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        //统一时间格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss "));

        //faxuleihua
        //忽略 在json字符串存在字段，在java对象中不存在对象
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    //对象转string
    public static <T> String objToString(T obj){
      if (obj == null ){
          return null;
      }
        try {
            return obj instanceof  String ?(String) obj : objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
          log.error("对象转字符串失败");
            return null;
        }
    }

    //对象转格式化好的jsonstring
    public static <T> String objToJSONString(T obj){
        if (obj == null ){
            return null;
        }
        try {
            return obj instanceof  String ?(String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            log.error("对象转字符串失败");
            return null;
        }
    }

    //字符json串转对象
    //第一个为T为泛型，2是返回值类型
   public static <T> T string2Obj(String str,Class<T> clazz){
       if (StringUtils.isEmpty(str) || clazz==null){
           return null;
       }
       try {
           return clazz.equals(String.class)?(T)str :objectMapper.readValue(str,clazz);
       } catch (IOException e) {
           log.error("字符串转对象失败");
           return null;
       }
   }

   //转成List对象集合
   public static <T> T string2JSONListObj(String str,Class<?> collectionClass,
                                      Class<?>... elementClasses){
       JavaType javaType = objectMapper.getTypeFactory()
              .constructParametricType(collectionClass,elementClasses);
       try {
           return objectMapper.readValue(str,javaType);
       } catch (IOException e) {
           log.error("字符串转对象失败");
           return null;
       }

   }

    //转成List对象集合
    public static <T> T string2ListObj(String str, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(str) || typeReference==null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class)?str: objectMapper.readValue(str,typeReference));
        } catch (IOException e) {
            log.error("字符串转对象失败");
            return null;
        }

    }


    public static void main(String[] args) {
        User user = new User();
        user.setUsername("huang");
        user.setId(2);

        User user2 = new User();
        user.setUsername("huaFFFng");
        user.setId(1);

        List<User> users = Lists.newArrayList();
        users.add(user2);
        users.add(user);
        String objTostr = JsonUtil.objToJSONString(users);
        List<User> uu = JsonUtil.string2JSONListObj(objTostr,List.class,User.class);
        System.out.println("DDFF"+uu.get(1).getUsername());
        log.info(objToString(user));
        log.info(objToJSONString(user));

        User user1 = string2Obj(objToString(user),User.class);
       System.out.println(user1.getUsername());
    }

}
