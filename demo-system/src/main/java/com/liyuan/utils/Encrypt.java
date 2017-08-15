package com.liyuan.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.UUID;


/**
 * @description 辅助工具：密码加密、UUID、令牌
 * @created 2017-04-10
 */
public class Encrypt {

    /**生成一个令牌号*/
    public static String createToken(){
        String str = createId();
        return str.replaceAll("-", "");
    }

    /**采用UUID算法生成一个唯一性的字符串*/
    public static String createId(){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return id;
    }

    /**
     * 将msg采用MD5算法处理,返回一个String结果
     * @param msg 明文
     * @return 密文
     */
    public static String md5(String msg){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            //原始信息input
            byte[] input = msg.getBytes();
            //加密信息output
            byte[] output = md.digest(input);//加密处理
            //采用Base64将加密内容output转成String字符串
            String str = Base64.encodeBase64String(output);
            return str;
        }catch(Exception ex){
            System.out.println("md5加密失败");
            return null;
        }
    }
}
