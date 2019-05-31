package com.hksy.framework.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;











public class TreeUtil
{
  public List<TreeObject> getChildTreeObjects(List<TreeObject> list, int praentId)
  {
/*  21 */     List<TreeObject> returnList = new ArrayList();
/*  22 */     for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext();) {
/*  23 */       TreeObject t = (TreeObject)iterator.next();

/*  25 */       if (t.getParentId().equals(Integer.valueOf(praentId))) {
/*  26 */         recursionFn(list, t);
/*  27 */         returnList.add(t);
      }
    }
/*  30 */     return returnList;
  }





  private void recursionFn(List<TreeObject> list, TreeObject t)
  {
/*  39 */     List<TreeObject> childList = getChildList(list, t);
/*  40 */     t.setChildren(childList);
/*  41 */     for (TreeObject tChild : childList) {
/*  42 */       if (hasChild(list, tChild))
      {
/*  44 */         Iterator<TreeObject> it = childList.iterator();
/*  45 */         while (it.hasNext()) {
/*  46 */           TreeObject n = (TreeObject)it.next();
/*  47 */           recursionFn(list, n);
        }
      }
    }
  }


  private List<TreeObject> getChildList(List<TreeObject> list, TreeObject t)
  {
/*  56 */     List<TreeObject> tlist = new ArrayList();
/*  57 */     Iterator<TreeObject> it = list.iterator();
/*  58 */     while (it.hasNext()) {
/*  59 */       TreeObject n = (TreeObject)it.next();
/*  60 */       if (n.getParentId().equals(t.getId())) {
/*  61 */         tlist.add(n);
      }
    }
/*  64 */     return tlist; }

/*  66 */   List<TreeObject> returnList = new ArrayList();





  public List<TreeObject> getChildTreeObjects(List<TreeObject> list, int typeId, String prefix)
  {
/*  74 */     if (list == null) return null;
/*  75 */     for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext();) {
/*  76 */       TreeObject node = (TreeObject)iterator.next();

/*  78 */       if (node.getParentId().equals(Integer.valueOf(typeId))) {
/*  79 */         recursionFn(list, node, prefix);
      }
    }




/*  86 */     return this.returnList;
  }

  private void recursionFn(List<TreeObject> list, TreeObject node, String p) {
/*  90 */     List<TreeObject> childList = getChildList(list, node);
/*  91 */     if (hasChild(list, node)) {
/*  92 */       this.returnList.add(node);
/*  93 */       Iterator<TreeObject> it = childList.iterator();
/*  94 */       while (it.hasNext()) {
/*  95 */         TreeObject n = (TreeObject)it.next();
/*  96 */         n.setName(p + n.getName());
/*  97 */         recursionFn(list, n, p + p);
      }
    } else {
/* 100 */       this.returnList.add(node);
    }
  }

  private boolean hasChild(List<TreeObject> list, TreeObject t)
  {
/* 106 */     return getChildList(list, t).size() > 0;
  }

  public void main(String[] args) {}
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/TreeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */