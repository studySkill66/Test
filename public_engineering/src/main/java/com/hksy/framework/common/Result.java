 package com.hksy.framework.common;

 import java.util.List;

 public class Result<T> {
   private T model;
   private List<T> resultSet;
   private int count;

   public T getModel() {
/* 11 */     return (T)this.model;
   }

   public void setModel(T model) {
/* 15 */     this.model = model;
   }

   public List<T> getResultSet() {
/* 19 */     return this.resultSet;
   }

   public void setResultSet(List<T> resultSet) {
/* 23 */     this.resultSet = resultSet;
   }

   public int getCount() {
/* 27 */     return this.count;
   }

   public void setCount(int count) {
/* 31 */     this.count = count;
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/common/Result.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */