 package com.hksy.framework.util;


 public class ExcelHeader
   implements Comparable<ExcelHeader>
 {
   private String title;

   private int order;

   private int width;
   private String methodName;

   public ExcelHeader(String title, int order, int width, String methodName)
   {
/* 16 */     this.width = width;
/* 17 */     this.title = title;
/* 18 */     this.order = order;
/* 19 */     this.methodName = methodName;
   }

   public int compareTo(ExcelHeader o) {
/* 23 */     return this.order < o.order ? -1 : this.order > o.order ? 1 : 0;
   }

   public String getTitle() {
/* 27 */     return this.title;
   }

   public void setTitle(String title) {
/* 31 */     this.title = title;
   }

   public int getOrder() {
/* 35 */     return this.order;
   }

   public void setOrder(int order) {
/* 39 */     this.order = order;
   }

   public String getMethodName() {
/* 43 */     return this.methodName;
   }

   public void setMethodName(String methodName) {
/* 47 */     this.methodName = methodName;
   }

   public int getWidth() {
/* 51 */     return this.width;
   }

   public void setWidth(int width) {
/* 55 */     this.width = width;
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/ExcelHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */