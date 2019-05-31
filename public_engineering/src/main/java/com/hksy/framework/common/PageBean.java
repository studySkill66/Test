package com.hksy.framework.common;


public class PageBean
{
/*   6 */   private final Integer fistPage = Integer.valueOf(1);
/*   7 */   private Integer pageNo = Integer.valueOf(0);
/*   8 */   private Integer pageSize = Integer.valueOf(10);
/*   9 */   private Integer pageNum = Integer.valueOf(0);
/*  10 */   private Integer totalRecordCount = Integer.valueOf(0);



  public Integer getFistPage()
  {
/*  16 */     return this.fistPage;
  }



  public Integer getPreviousPage()
  {
/*  23 */     if (this.pageNo.intValue() - 1 <= 0) {
/*  24 */       return Integer.valueOf(1);
    }
/*  26 */     return Integer.valueOf(this.pageNo.intValue() - 1);
  }




  public Integer getNextPage()
  {
/*  34 */     if (this.pageNo.intValue() + 1 > this.pageNum.intValue()) {
/*  35 */       return this.pageNum;
    }
/*  37 */     return Integer.valueOf(this.pageNo.intValue() + 1);
  }




  public Integer getLastPage()
  {
/*  45 */     return this.pageNum;
  }



  public Integer getPageIndex()
  {
/*  52 */     return Integer.valueOf((this.pageNo.intValue() - 1) * this.pageSize.intValue());
  }



  public Integer getPageNo()
  {
/*  59 */     return this.pageNo;
  }



  public void setPageNo(Integer pageNo)
  {
/*  66 */     if (pageNo == null) {
/*  67 */       this.pageNo = Integer.valueOf(1);
    } else {
/*  69 */       this.pageNo = pageNo;
    }
  }




  public Integer getPageSize()
  {
/*  78 */     return this.pageSize;
  }



  public void setPageSize(Integer pageSize)
  {
/*  85 */     if (pageSize == null) {
/*  86 */       return;
    }
/*  88 */     this.pageSize = pageSize;
  }



  public Integer getPageNum()
  {
/*  95 */     if (this.totalRecordCount.intValue() == 0) {
/*  96 */       return this.totalRecordCount;
    }
/*  98 */     if (this.totalRecordCount.intValue() % this.pageSize.intValue() > 0) {
/*  99 */       this.pageNum = Integer.valueOf(this.totalRecordCount.intValue() / this.pageSize.intValue() + 1);
    } else {
/* 101 */       this.pageNum = Integer.valueOf(this.totalRecordCount.intValue() / this.pageSize.intValue());
    }

/* 104 */     return this.pageNum;
  }




  public Integer getTotalRecordCount()
  {
/* 112 */     return this.totalRecordCount;
  }




  public void setTotalRecordCount(Integer totalRecordCount)
  {
/* 120 */     this.totalRecordCount = totalRecordCount;
  }
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/common/PageBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */