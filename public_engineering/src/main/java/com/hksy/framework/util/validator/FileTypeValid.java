//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hksy.framework.util.validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class FileTypeValid {
    private FileTypeValid() {
    }

    private static String bytesToHexString(byte[] src) {
        StringBuffer sb = new StringBuffer();
        if (src != null && src.length > 0) {
            for(int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    sb.append(0);
                }

                sb.append(hv);
            }

            return sb.toString();
        } else {
            return null;
        }
    }

    private static String getFileHead(File file) throws IOException {
        byte[] b = new byte[28];
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            in.read(b, 0, 28);
        } catch (IOException var11) {
            var11.printStackTrace();
            throw var11;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var10) {
                    var10.printStackTrace();
                    throw var10;
                }
            }

        }

        return bytesToHexString(b);
    }

    public static FileType getType(File file) throws IOException {
        String fileHead = getFileHead(file);
        if (fileHead != null && fileHead.length() != 0) {
            fileHead = fileHead.toUpperCase();
            FileType[] fileTypes = FileType.values();
            FileType[] var3 = fileTypes;
            int var4 = fileTypes.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                FileType type = var3[var5];
                if (fileHead.startsWith(type.getValue())) {
                    return type;
                }
            }

            return null;
        } else {
            return null;
        }
    }
}
