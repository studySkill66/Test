 package com.hksy.framework.util.json;

 import com.alibaba.fastjson.JSON;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang3.StringUtils;
 import org.codehaus.jackson.JsonNode;
 import org.codehaus.jackson.map.ObjectMapper;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;






 public class JsonUtils
 {
/* 21 */   private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

   public static Object TO_OBJ(String source, Class<?> clazz) {
/* 24 */     return JSON.parseObject(source, clazz);
   }

   public static String map2JsonString(Map<String, Object> map) {
     try {
/* 29 */       return JacksonJsonMapper.getInstance().writeValueAsString(map);
     } catch (IOException e) {
/* 31 */       logger.error("转换JSON失败！", e);
     }
/* 33 */     return "";
   }








   public static <T> T json2Bean(JsonNode node, Class<T> clazz)
   {
     try
     {
/* 47 */       return (T)JacksonJsonMapper.getInstance().readValue(node, clazz);
     } catch (Exception e) {
/* 49 */       e.printStackTrace();
/* 50 */       throw new RuntimeException(e);
     }
   }

   public static <T> List<T> json2List(String json, Class<T> clazz) {
/* 55 */     List<T> list = new ArrayList();
/* 56 */     if (StringUtils.isBlank(json)) {
/* 57 */       return list;
     }
     try {
/* 60 */       JsonNode nodes = JacksonJsonMapper.getInstance().readTree(json);
/* 61 */       for (JsonNode jsonNode : nodes) {
/* 62 */         T t = json2Bean(jsonNode, clazz);
/* 63 */         list.add(t);
       }
     } catch (Exception e) {
/* 66 */       logger.error("json数据不合法：" + json);
/* 67 */       e.printStackTrace();
     }
/* 69 */     return list;
   }

   public static String toJSONString(Object obj) {
     try {
/* 74 */       return JacksonJsonMapper.getInstance().writeValueAsString(obj);
     } catch (Exception e) {
/* 76 */       logger.error("转换json失败", e); }
/* 77 */     return "";
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/json/JsonUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */