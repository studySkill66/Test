package com.hksy.framework.util;
/*     */

import java.awt.image.BufferedImage;

/*     */
/*     */
/*     */
public class ScaleImage {
    private int width;
    private int height;
    private int scaleWidth;
    /*  12 */   private double support = 4.1D;
    /*     */
/*  14 */   private double PI = 3.14159265358978D;
    /*     */
/*     */
    private double[] contrib;
    /*     */
/*     */
    private double[] normContrib;
    /*     */
/*     */
    private double[] tmpContrib;
    /*     */
/*     */
    private int nDots;
    /*     */
    private int nHalfDots;
    /*     */
/*  30 */   private static ScaleImage instance = new ScaleImage();

    /*     */
/*     */
/*  33 */
    public static ScaleImage getInstance() {
        return instance;
    }

    /*     */
    public BufferedImage imageZoomOut(BufferedImage srcBufferImage, int w, int h) {
/*  36 */
        this.width = srcBufferImage.getWidth();
/*  37 */
        this.height = srcBufferImage.getHeight();
/*  38 */
        this.scaleWidth = w;
/*     */
/*  40 */
        if (DetermineResultSize(w, h) == 1) {
/*  41 */
            return srcBufferImage;
        }
/*  43 */
        CalContrib();
/*  44 */
        BufferedImage pbOut = HorizontalFiltering(srcBufferImage, w);
/*  45 */
        BufferedImage pbFinalOut = VerticalFiltering(pbOut, h);
/*  46 */
        return pbFinalOut;
    }

    /*     */
/*     */
/*     */
/*     */
    private int DetermineResultSize(int w, int h) {
        double scaleH = w / this.width;
        double scaleV = h / this.height;
        if ((scaleH >= 1.0D) && (scaleV >= 1.0D)) {
            return 1;
        }
        return 0;
    }

    private double Lanczos(int i, int inWidth, int outWidth, double Support) {
        double x = i * outWidth / inWidth;
        return Math.sin(x * this.PI) / (x * this.PI) * Math.sin(x * this.PI / Support) / (x * this.PI / Support);
    }

    private void CalContrib() {
        this.nHalfDots = ((int) (this.width * this.support / this.scaleWidth));
        this.nDots = (this.nHalfDots * 2 + 1);
        try {
            this.contrib = new double[this.nDots];
            this.normContrib = new double[this.nDots];
            this.tmpContrib = new double[this.nDots];
        } catch (Exception localException) {
        }
        int center = this.nHalfDots;
        this.contrib[center] = 1.0D;
        double weight = 0.0D;
        int i = 0;
        for (i = 1; i <= center; i++) {
            this.contrib[(center + i)] = Lanczos(i, this.width, this.scaleWidth, this.support);
            weight += this.contrib[(center + i)];
        }
        for (i = center - 1; i >= 0; i--) {
            this.contrib[i] = this.contrib[(center * 2 - i)];
        }
        weight = weight * 2.0D + 1.0D;
        for (i = 0; i <= center; i++) {
            this.normContrib[i] = (this.contrib[i] / weight);
        }
        for (i = center + 1; i < this.nDots; i++) {
            this.normContrib[i] = this.normContrib[(center * 2 - i)];
        }
    }

    private void CalTempContrib(int start, int stop) {
        double weight = 0.0D;
        int i = 0;
        for (i = start; i <= stop; i++) {
            weight += this.contrib[i];
        }
        for (i = start; i <= stop; i++) {
            this.tmpContrib[i] = (this.contrib[i] / weight);
        }
    }

    private int GetRedValue(int rgbValue) {
        int temp = rgbValue & 0xFF0000;
        return temp >> 16;
    }
    private int GetGreenValue(int rgbValue) {
        int temp = rgbValue & 0xFF00;
        return temp >> 8;
    }

    private int GetBlueValue(int rgbValue) {
        return rgbValue & 0xFF;
    }

    private int ComRGB(int redValue, int greenValue, int blueValue) {
        return (redValue << 16) + (greenValue << 8) + blueValue;
    }

    private int HorizontalFilter(BufferedImage bufImg, int startX, int stopX, int start, int stop, int y, double[] pContrib) {
        double valueRed = 0.0;
        double valueGreen = 0.0;
        double valueBlue = 0.0;
        int valueRGB = 0;
        int i, j;

        for (i = startX, j = start; i <= stopX; i++, j++) {
            valueRGB = bufImg.getRGB(i, y);

            valueRed += GetRedValue(valueRGB) * pContrib[j];
            valueGreen += GetGreenValue(valueRGB) * pContrib[j];
            valueBlue += GetBlueValue(valueRGB) * pContrib[j];
        }

        valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
                Clip((int) valueBlue));
        return valueRGB;
    }


    private BufferedImage HorizontalFiltering(BufferedImage bufImage, int iOutW) {
        int dwInW = bufImage.getWidth();
        int dwInH = bufImage.getHeight();
        int value = 0;
        BufferedImage pbOut = new BufferedImage(iOutW, dwInH,
                BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < iOutW; x++) {

            int startX;
            int start;
            int X = (int) (((double) x) * ((double) dwInW) / (iOutW) + 0.5);
            int y = 0;

            startX = X - nHalfDots;
            if (startX < 0) {
                startX = 0;
                start = nHalfDots - X;
            } else {
                start = 0;
            }

            int stop;
            int stopX = X + nHalfDots;
            if (stopX > (dwInW - 1)) {
                stopX = dwInW - 1;
                stop = nHalfDots + (dwInW - 1 - X);
            } else {
                stop = nHalfDots * 2;
            }

            if (start > 0 || stop < nDots - 1) {
                CalTempContrib(start, stop);
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start,
                            stop, y, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start,
                            stop, y, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }
        }

        return pbOut;
    }

    /*     */
/*     */
    private int VerticalFilter(BufferedImage pbInImage, int startY, int stopY, int start, int stop, int x, double[] pContrib) {
        double valueRed = 0.0;
        double valueGreen = 0.0;
        double valueBlue = 0.0;
        int valueRGB = 0;
        int i, j;

        for (i = startY, j = start; i <= stopY; i++, j++) {
            valueRGB = pbInImage.getRGB(x, i);

            valueRed += GetRedValue(valueRGB) * pContrib[j];
            valueGreen += GetGreenValue(valueRGB) * pContrib[j];
            valueBlue += GetBlueValue(valueRGB) * pContrib[j];
            // System.out.println(valueRed+"->"+Clip((int)valueRed)+"<-");
            //
            // System.out.println(valueGreen+"->"+Clip((int)valueGreen)+"<-");
            // System.out.println(valueBlue+"->"+Clip((int)valueBlue)+"<-"+"-->");
        }

        valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
                Clip((int) valueBlue));
        // System.out.println(valueRGB);
        return valueRGB;
    }

    /*     */
    private BufferedImage VerticalFiltering(BufferedImage pbImage, int iOutH) {
        int iW = pbImage.getWidth();
        int iH = pbImage.getHeight();
        int value = 0;
        BufferedImage pbOut = new BufferedImage(iW, iOutH,
                BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < iOutH; y++) {

            int startY;
            int start;
            int Y = (int) (((double) y) * ((double) iH) / (iOutH) + 0.5);

            startY = Y - nHalfDots;
            if (startY < 0) {
                startY = 0;
                start = nHalfDots - Y;
            } else {
                start = 0;
            }

            int stop;
            int stopY = Y + nHalfDots;
            if (stopY > (iH - 1)) {
                stopY = iH - 1;
                stop = nHalfDots + (iH - 1 - Y);
            } else {
                stop = nHalfDots * 2;
            }

            if (start > 0 || stop < nDots - 1) {
                CalTempContrib(start, stop);
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop,
                            x, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop,
                            x, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }

        }

        return pbOut;

    }

    private int Clip(int x) {
        if (x < 0)
            return 0;

        if (x > 255)
            return 255;
        return x;
    }
}