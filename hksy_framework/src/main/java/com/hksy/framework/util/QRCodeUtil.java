package com.hksy.framework.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
/*     */
import java.util.Hashtable;
import javax.imageio.ImageIO;

public class QRCodeUtil
{
  private static final String CHARSET = "utf-8";
  private static final String FORMAT_NAME = "JPG";
  private static final int QRCODE_SIZE = 300;
  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;

  private static BufferedImage createImage(String content, String imgPath, boolean needCompress)
    throws Exception
  {
/*  46 */     Hashtable<EncodeHintType, Object> hints = new Hashtable();
/*  47 */     hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
/*  48 */     hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
/*  49 */     hints.put(EncodeHintType.MARGIN, Integer.valueOf(1));
/*  50 */     BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);

/*  52 */     int width = bitMatrix.getWidth();
/*  53 */     int height = bitMatrix.getHeight();
/*  54 */     BufferedImage image = new BufferedImage(width, height, 1);

/*  56 */     for (int x = 0; x < width; x++) {
/*  57 */       for (int y = 0; y < height; y++) {
/*  58 */         image.setRGB(x, y, bitMatrix.get(x, y) ? -16777216 : -1);
      }
    }

/*  62 */     if ((imgPath == null) || ("".equals(imgPath))) {
/*  63 */       return image;
    }

/*  66 */     insertImage(image, imgPath, needCompress);
/*  67 */     return image;
  }











  private static void insertImage(BufferedImage source, String imgPath, boolean needCompress)
    throws Exception
  {
/*  83 */     File file = new File(imgPath);
/*  84 */     if (!file.exists()) {
/*  85 */       System.err.println("" + imgPath + "   该文件不存在！");
/*  86 */       return;
    }
/*  88 */     Image src = ImageIO.read(new File(imgPath));
/*  89 */     int width = src.getWidth(null);
/*  90 */     int height = src.getHeight(null);
/*  91 */     if (needCompress) {
/*  92 */       if (width > 60) {
/*  93 */         width = 60;
      }
/*  95 */       if (height > 60) {
/*  96 */         height = 60;
      }
/*  98 */       Image image = src.getScaledInstance(width, height, 4);

/* 100 */       BufferedImage tag = new BufferedImage(width, height, 1);

/* 102 */       Graphics g = tag.getGraphics();
/* 103 */       g.drawImage(image, 0, 0, null);
/* 104 */       g.dispose();
/* 105 */       src = image;
    }

/* 108 */     Graphics2D graph = source.createGraphics();
/* 109 */     int x = (300 - width) / 2;
/* 110 */     int y = (300 - height) / 2;
/* 111 */     graph.drawImage(src, x, y, width, height, null);
/* 112 */     Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6.0F, 6.0F);
/* 113 */     graph.setStroke(new BasicStroke(3.0F));
/* 114 */     graph.draw(shape);
/* 115 */     graph.dispose();
  }












  public static void encode(String qrcodeName, String content, String imgPath, String destPath, boolean needCompress)
    throws Exception
  {
/* 132 */     BufferedImage image = createImage(content, imgPath, needCompress);

/* 134 */     mkdirs(destPath);
/* 135 */     String file = qrcodeName + ".shtml";
/* 136 */     ImageIO.write(image, "JPG", new File(destPath + "/" + file));
  }







  public static void mkdirs(String destPath)
  {
/* 147 */     File file = new File(destPath);

/* 149 */     if ((!file.exists()) && (!file.isDirectory())) {
/* 150 */       file.mkdirs();
    }
  }











  public static void encode(String qrcodeName, String content, String imgPath, String destPath)
    throws Exception
  {
/* 167 */     encode(qrcodeName, content, imgPath, destPath, false);
  }











  public static void encode(String qrcodeName, String content, String destPath, boolean needCompress)
    throws Exception
  {
/* 183 */     encode(qrcodeName, content, null, destPath, needCompress);
  }








  public static void encode(String content, String destPath)
    throws Exception
  {
/* 196 */     encode(null, content, destPath, false);
  }













  public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
    throws Exception
  {
/* 214 */     BufferedImage image = createImage(content, imgPath, needCompress);

/* 216 */     ImageIO.write(image, "JPG", output);
  }









  public static void encode(String content, OutputStream output)
    throws Exception
  {
/* 230 */     encode(content, null, output, false);
  }








  public static String decode(File file)
    throws Exception
  {
/* 243 */     BufferedImage image = ImageIO.read(file);
/* 244 */     if (image == null) {
/* 245 */       return null;
    }
/* 247 */     BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
/* 248 */     BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

/* 250 */     Hashtable<DecodeHintType, Object> hints = new Hashtable();
/* 251 */     hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
/* 252 */     Result result = new MultiFormatReader().decode(bitmap, hints);
/* 253 */     String resultStr = result.getText();
/* 254 */     return resultStr;
  }







  public static String decode(String path)
    throws Exception
  {
/* 266 */     return decode(new File(path));
  }

  public static void main(String[] args) throws Exception {
/* 270 */     String text = "http://www.yihaomen.com";
/* 271 */     encode(text, "c:/me.jpg", "c:/barcode", true);
  }
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/QRCodeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */