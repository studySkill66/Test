package com.hksy.framework.util;

import java.io.PrintStream;


public class UnicodeUtils
{
  public static void main(String[] args)
  {
/*  10 */     String str = "是";

/*  12 */     System.out.println(str);

/*  14 */     str = toUnicode(str, false);

/*  16 */     System.out.println(str);

/*  18 */     str = "localhost:8080";

/*  20 */     System.out.println(str);

/*  22 */     str = stringToUnicode(str);

/*  24 */     System.out.println(str);

/*  26 */     str = fromUnicode(str);

/*  28 */     System.out.println(str);
  }










  public static String stringToUnicode(String s)
  {
/*  42 */     String str = "";
/*  43 */     for (int i = 0; i < s.length(); i++) {
/*  44 */       int ch = s.charAt(i);
/*  45 */       if (ch > 255) {
/*  46 */         str = str + "\\u" + Integer.toHexString(ch);
      } else
/*  48 */         str = str + "\\u00" + Integer.toHexString(ch);
    }
/*  50 */     return str;
  }


/*  54 */   private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };






  private static char toHex(int nibble)
  {
/*  63 */     return hexDigit[(nibble & 0xF)];
  }









  public static String toUnicode(String theString, boolean escapeSpace)
  {
/*  76 */     int len = theString.length();

/*  78 */     int bufLen = len * 2;

/*  80 */     if (bufLen < 0)
    {
/*  82 */       bufLen = Integer.MAX_VALUE;
    }


/*  86 */     StringBuffer outBuffer = new StringBuffer(bufLen);

/*  88 */     for (int x = 0; x < len; x++)
    {
/*  90 */       char aChar = theString.charAt(x);





/*  96 */       if ((aChar > '=') && (aChar < ''))
      {
/*  98 */         if (aChar == '\\')
        {
/* 100 */           outBuffer.append('\\');
/* 101 */           outBuffer.append('\\');

        }
        else
        {

/* 107 */           outBuffer.append(aChar);
        }


      }
      else {
/* 113 */         switch (aChar)
        {

        case ' ':
/* 117 */           if ((x == 0) || (escapeSpace))
          {
/* 119 */             outBuffer.append('\\');
          }
/* 121 */           outBuffer.append(' ');

/* 123 */           break;

        case '\t':
/* 126 */           outBuffer.append('\\');
/* 127 */           outBuffer.append('t');

/* 129 */           break;

        case '\n':
/* 132 */           outBuffer.append('\\');
/* 133 */           outBuffer.append('n');

/* 135 */           break;

        case '\r':
/* 138 */           outBuffer.append('\\');
/* 139 */           outBuffer.append('r');

/* 141 */           break;

        case '\f':
/* 144 */           outBuffer.append('\\');
/* 145 */           outBuffer.append('f');

/* 147 */           break;





        case '!':
        case '#':
        case ':':
        case '=':
/* 157 */           outBuffer.append('\\');
/* 158 */           outBuffer.append(aChar);

/* 160 */           break;


        default:
/* 164 */           if ((aChar < ' ') || (aChar > '~'))
          {
/* 166 */             outBuffer.append('\\');

/* 168 */             outBuffer.append('u');

/* 170 */             outBuffer.append(toHex(aChar >> '\f' & 0xF));

/* 172 */             outBuffer.append(toHex(aChar >> '\002' & 0xF));

/* 174 */             outBuffer.append(toHex(aChar >> '\004' & 0xF));

/* 176 */             outBuffer.append(toHex(aChar & 0xF));
          }
          else
          {
/* 180 */             outBuffer.append(aChar);
          }

          break;
        }

      }
    }
/* 188 */     return outBuffer.toString();
  }








  public static String fromUnicode(String str)
  {
/* 200 */     return fromUnicode(str.toCharArray(), 0, str.length(), new char['Ѐ']);
  }










  public static String fromUnicode(char[] in, int off, int len, char[] convtBuf)
  {
/* 214 */     if (convtBuf.length < len)
    {
/* 216 */       int newLen = len * 2;

/* 218 */       if (newLen < 0)
      {
/* 220 */         newLen = Integer.MAX_VALUE;
      }


/* 224 */       convtBuf = new char[newLen];
    }




/* 230 */     char[] out = convtBuf;

/* 232 */     int outLen = 0;

/* 234 */     int end = off + len;

/* 236 */     while (off < end)
    {
/* 238 */       char aChar = in[(off++)];

/* 240 */       if (aChar == '\\')
      {
/* 242 */         aChar = in[(off++)];

/* 244 */         if (aChar == 'u')
        {


/* 248 */           int value = 0;

/* 250 */           for (int i = 0; i < 4; i++)
          {
/* 252 */             aChar = in[(off++)];

/* 254 */             switch (aChar)
            {










            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
/* 276 */               value = (value << 4) + aChar - 48;

/* 278 */               break;







            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
/* 292 */               value = (value << 4) + 10 + aChar - 97;

/* 294 */               break;







            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
/* 308 */               value = (value << 4) + 10 + aChar - 65;

/* 310 */               break;
            case ':': case ';': case '<': case '=': case '>': case '?': case '@': case 'G': case 'H': case 'I': case 'J': case 'K':
            case 'L': case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T': case 'U': case 'V':
            case 'W': case 'X': case 'Y': case 'Z': case '[': case '\\': case ']': case '^': case '_': case '`': default:
/* 314 */               throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
            }

          }




/* 322 */           out[(outLen++)] = ((char)value);
        }
        else
        {
/* 326 */           if (aChar == 't')
          {
/* 328 */             aChar = '\t';
          }
/* 330 */           else if (aChar == 'r')
          {
/* 332 */             aChar = '\r';
          }
/* 334 */           else if (aChar == 'n')
          {
/* 336 */             aChar = '\n';
          }
/* 338 */           else if (aChar == 'f')
          {
/* 340 */             aChar = '\f';
          }


/* 344 */           out[(outLen++)] = aChar;
        }

      }
      else
      {
/* 350 */         out[(outLen++)] = aChar;
      }
    }



/* 356 */     return new String(out, 0, outLen);
  }
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/UnicodeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */