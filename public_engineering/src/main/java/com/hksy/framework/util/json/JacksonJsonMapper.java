 package com.hksy.framework.util.json;

 import java.util.ArrayList;
 import java.util.List;
 import org.codehaus.jackson.JsonNode;
 import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
 import org.codehaus.jackson.map.ObjectMapper;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;





 public class JacksonJsonMapper
 {
/* 17 */   private static final Logger log = LoggerFactory.getLogger(JacksonJsonMapper.class);

/* 19 */   static volatile ObjectMapper objectMapper = null;





   public static ObjectMapper getInstance()
   {
/* 27 */     if (objectMapper == null) {
/* 28 */       synchronized (ObjectMapper.class) {
/* 29 */         if (objectMapper == null) {
/* 30 */           objectMapper = new ObjectMapper();
/* 31 */           objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         }
       }
     }
/* 35 */     return objectMapper;
   }

   public static String getResultCode(String json) throws Exception {
/* 39 */     JsonNode jsonNode = getInstance().readTree(json);
/* 40 */     String resultCode = jsonNode.get("code").getTextValue();
/* 41 */     return resultCode;
   }

   public static JsonNode getData(String json) throws Exception {
/* 45 */     JsonNode jsonNode = getInstance().readTree(json);
/* 46 */     JsonNode data = jsonNode.get("data");
/* 47 */     return data;
   }

   public static int getValueAsInt(String json, String filedName) throws Exception {
/* 51 */     JsonNode jsonNode = getInstance().readTree(json);
/* 52 */     JsonNode data = jsonNode.get(filedName);
/* 53 */     return data.asInt();
   }

   public static String getValueAsString(String json, String filedName) throws Exception {
/* 57 */     JsonNode jsonNode = getInstance().readTree(json);
/* 58 */     JsonNode data = jsonNode.get(filedName);
/* 59 */     return data.asText();
   }

   public static <T> T json2Bean(JsonNode node, Class<T> clazz) {
     try {
/* 64 */       return (T)getInstance().readValue(node, clazz);
     } catch (Exception e) {
/* 66 */       e.printStackTrace();
/* 67 */       throw new RuntimeException(e);
     }
   }

   public static <T> List<T> json2List(JsonNode json, Class<T> clazz) {
/* 72 */     List<T> list = new ArrayList();
/* 73 */     if (json == null) {
/* 74 */       return list;
     }
     try {
/* 77 */       for (JsonNode jsonNode : json) {
/* 78 */         T t = json2Bean(jsonNode, clazz);
/* 79 */         list.add(t);
       }
     } catch (Exception e) {
/* 82 */       log.error("json数据不合法：" + json);
/* 83 */       e.printStackTrace();
     }
/* 85 */     return list;
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/json/JacksonJsonMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */