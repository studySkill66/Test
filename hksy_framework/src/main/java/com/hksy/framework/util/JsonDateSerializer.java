 package com.hksy.framework.util;

 import java.io.IOException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.codehaus.jackson.JsonGenerator;
 import org.codehaus.jackson.JsonProcessingException;
 import org.codehaus.jackson.map.JsonSerializer;
 import org.codehaus.jackson.map.SerializerProvider;
 import org.springframework.stereotype.Component;





 @Component
 public class JsonDateSerializer
   extends JsonSerializer<Date>
 {
/* 20 */   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
/* 23 */     String formattedDate = dateFormat.format(date);

/* 25 */     gen.writeString(formattedDate);
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/JsonDateSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */