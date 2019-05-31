package com.hksy.framework.util.validator;



public enum FileType
{
/*   7 */   TXT("000000000000"), 




/*  12 */   JPEG("FFD8FF"), 




/*  17 */   PNG("89504E47"), 




/*  22 */   GIF("47494638"), 




/*  27 */   TIFF("49492A00"), 




/*  32 */   BMP("424D"), 




/*  37 */   DWG("41433130"), 




/*  42 */   PSD("38425053"), 




/*  47 */   RTF("7B5C727466"), 




/*  52 */   XML("3C3F786D6C"), 




/*  57 */   HTML("3C21444F43545950452048544D4C"), 




/*  62 */   HTML5("3C21444F43545950452068746D6C"), 




/*  67 */   JSP("3C25402070616765206C"), 




/*  72 */   EML("44656C69766572792D646174653A"), 




/*  77 */   DBX("CFAD12FEC5FD746F"), 




/*  82 */   PST("2142444E"), 




/*  87 */   XLS_DOC("D0CF11E0A1B11AE1000000000000000000000000000000003E"), 




/*  92 */   DOC_2007("504B0304140006000800000021002B75AD8C"), 




/*  97 */   XLS_2007("504B0304140006000800000021007341B39D8E"), 




/* 102 */   MDB("5374616E64617264204A"), 




/* 107 */   WPD("FF575043"), 




/* 112 */   EPS("252150532D41646F6265"), 




/* 117 */   PDF("255044462D312E"), 




/* 122 */   QDF("AC9EBD8F"), 




/* 127 */   PWL("E3828596"), 




/* 132 */   ZIP("504B0304140000000800FB59D04AA93E3E2CC"), 




/* 137 */   RAR("52617221"), 




/* 142 */   WAV("57415645"), 




/* 147 */   AVI("41564920"), 




/* 152 */   RAM("2E7261FD"), 




/* 157 */   RM("2E524D46"), 




/* 162 */   MPG("000001BA"), 




/* 167 */   MOV("6D6F6F76"), 




/* 172 */   ASF("3026B2758E66CF11"), 




/* 177 */   EXE("4D5A90000300000004000000FFFF0000B"), 




/* 182 */   MID("4D546864");

/* 184 */   private String value = "";




  private FileType(String value)
  {
/* 191 */     this.value = value;
  }

  public String getValue() {
/* 195 */     return this.value;
  }

  public void setValue(String value) {
/* 199 */     this.value = value;
  }
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/validator/FileType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */