 package com.hksy.framework.util;

 import com.google.zxing.LuminanceSource;
 import java.awt.Graphics2D;
 import java.awt.image.BufferedImage;



 public class BufferedImageLuminanceSource extends LuminanceSource
 {
   private final BufferedImage image;
   private final int left;
   private final int top;

   public BufferedImageLuminanceSource(BufferedImage image)
   {
/* 16 */     this(image, 0, 0, image.getWidth(), image.getHeight());
   }

   public BufferedImageLuminanceSource(BufferedImage image, int left, int top, int width, int height)
   {
/* 21 */     super(width, height);

/* 23 */     int sourceWidth = image.getWidth();
/* 24 */     int sourceHeight = image.getHeight();
/* 25 */     if ((left + width > sourceWidth) || (top + height > sourceHeight)) {
/* 26 */       throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
     }


/* 30 */     for (int y = top; y < top + height; y++) {
/* 31 */       for (int x = left; x < left + width; x++) {
/* 32 */         if ((image.getRGB(x, y) & 0xFF000000) == 0) {
/* 33 */           image.setRGB(x, y, -1);
         }
       }
     }

/* 38 */     this.image = new BufferedImage(sourceWidth, sourceHeight, 10);

/* 40 */     this.image.getGraphics().drawImage(image, 0, 0, null);
/* 41 */     this.left = left;
/* 42 */     this.top = top;
   }

   public byte[] getRow(int y, byte[] row)
   {
/* 47 */     if ((y < 0) || (y >= getHeight())) {
/* 48 */       throw new IllegalArgumentException("Requested row is outside the image: " + y);
     }

/* 51 */     int width = getWidth();
/* 52 */     if ((row == null) || (row.length < width)) {
/* 53 */       row = new byte[width];
     }
/* 55 */     this.image.getRaster().getDataElements(this.left, this.top + y, width, 1, row);
/* 56 */     return row;
   }

   public byte[] getMatrix()
   {
/* 61 */     int width = getWidth();
/* 62 */     int height = getHeight();
/* 63 */     int area = width * height;
/* 64 */     byte[] matrix = new byte[area];
/* 65 */     this.image.getRaster().getDataElements(this.left, this.top, width, height, matrix);
/* 66 */     return matrix;
   }

   public boolean isCropSupported()
   {
/* 71 */     return true;
   }

   public LuminanceSource crop(int left, int top, int width, int height)
   {
/* 76 */     return new BufferedImageLuminanceSource(this.image, this.left + left, this.top + top, width, height);
   }


   public boolean isRotateSupported()
   {
/* 82 */     return true;
   }

   public LuminanceSource rotateCounterClockwise()
   {
/* 87 */     int sourceWidth = this.image.getWidth();
/* 88 */     int sourceHeight = this.image.getHeight();
/* 89 */     java.awt.geom.AffineTransform transform = new java.awt.geom.AffineTransform(0.0D, -1.0D, 1.0D, 0.0D, 0.0D, sourceWidth);

/* 91 */     BufferedImage rotatedImage = new BufferedImage(sourceHeight, sourceWidth, 10);

/* 93 */     Graphics2D g = rotatedImage.createGraphics();
/* 94 */     g.drawImage(this.image, transform, null);
/* 95 */     g.dispose();
/* 96 */     int width = getWidth();
/* 97 */     return new BufferedImageLuminanceSource(rotatedImage, this.top, sourceWidth - (this.left + width), 
/* 98 */       getHeight(), width);
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/BufferedImageLuminanceSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */