 package com.hksy.framework.util;

 import java.util.ArrayList;
 import java.util.List;




 public class TreeObject
 {
   private Integer id;
   private Integer parentId;
   private String name;
   private String parentName;
   private String resKey;
   private String resUrl;
   private Integer level;
   private String type;
   private String description;
   private String icon;
   private Integer ishide;
/* 22 */   private List<TreeObject> children = new ArrayList();

/* 24 */   public Integer getId() { return this.id; }

   public void setId(Integer id) {
/* 27 */     this.id = id;
   }

/* 30 */   public Integer getParentId() { return this.parentId; }

   public void setParentId(Integer parentId) {
/* 33 */     this.parentId = parentId;
   }

/* 36 */   public List<TreeObject> getChildren() { return this.children; }

   public void setChildren(List<TreeObject> children) {
/* 39 */     this.children = children;
   }

/* 42 */   public String getName() { return this.name; }

   public void setName(String name) {
/* 45 */     this.name = name;
   }

/* 48 */   public String getParentName() { return this.parentName; }

   public void setParentName(String parentName) {
/* 51 */     this.parentName = parentName;
   }

/* 54 */   public String getResKey() { return this.resKey; }

   public void setResKey(String resKey) {
/* 57 */     this.resKey = resKey;
   }

/* 60 */   public String getResUrl() { return this.resUrl; }

   public void setResUrl(String resUrl) {
/* 63 */     this.resUrl = resUrl;
   }

/* 66 */   public Integer getLevel() { return this.level; }

   public void setLevel(Integer level) {
/* 69 */     this.level = level;
   }

/* 72 */   public String getType() { return this.type; }

   public void setType(String type) {
/* 75 */     this.type = type;
   }

/* 78 */   public String getDescription() { return this.description; }

   public void setDescription(String description) {
/* 81 */     this.description = description;
   }

/* 84 */   public Integer getIshide() { return this.ishide; }

   public void setIshide(Integer ishide) {
/* 87 */     this.ishide = ishide;
   }

/* 90 */   public String getIcon() { return this.icon; }

   public void setIcon(String icon) {
/* 93 */     this.icon = icon;
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/TreeObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */