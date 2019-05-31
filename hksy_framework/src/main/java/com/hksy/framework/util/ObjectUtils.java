package com.hksy.framework.util;

/*     */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ObjectUtils {
    /*  14 */   private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    public static Integer toInteger(Object obj) {
        /*  17 */
        if (obj == null) {
            /*  18 */
            return null;
        }
        /*  20 */
        if ((obj instanceof Integer)) {
            /*  21 */
            return (Integer) obj;
        }
        /*  23 */
        String str = obj.toString().trim();
        /*  24 */
        if (str.length() > 0) {
            try {
                /*  26 */
                return new Integer(str);
            } catch (Exception localException) {
            }
        }
        /*  30 */
        return null;
    }

    public static Integer toInteger(Object obj, Integer defaultValue) {
        /*  34 */
        Integer value = toInteger(obj);
        /*  35 */
        if (value != null) {
            /*  36 */
            return value;
        }
        /*  38 */
        return defaultValue;
    }

    public static Long toLong(Object obj) {
        /*  42 */
        if (obj == null) {
            /*  43 */
            return null;
        }
        /*  45 */
        if ((obj instanceof Long)) {
            /*  46 */
            return (Long) obj;
        }
        /*  48 */
        String str = obj.toString().trim();
        /*  49 */
        if (str.length() > 0) {
            try {
                /*  51 */
                return new Long(str);
            } catch (Exception localException) {
            }
        }
        /*  55 */
        return null;
    }

    public static Long toLong(Object obj, Long defaultValue) {
        /*  59 */
        Long value = toLong(obj);
        /*  60 */
        if (value != null) {
            /*  61 */
            return value;
        }
        /*  63 */
        return defaultValue;
    }

    public static BigDecimal toBigDecimal(Object obj) {
        /*  67 */
        if (obj == null) {
            /*  68 */
            return null;
        }
        /*  70 */
        if ((obj instanceof BigDecimal)) {
            /*  71 */
            return (BigDecimal) obj;
        }
        /*  73 */
        String str = obj.toString().trim();
        /*  74 */
        if (str.length() > 0) {
            try {
                /*  76 */
                return new BigDecimal(str);
            } catch (Exception localException) {
            }
        }
        /*  80 */
        return null;
    }

    public static BigDecimal toBigDecimal(Object obj, BigDecimal defaultValue) {
        /*  84 */
        BigDecimal value = toBigDecimal(obj);
        /*  85 */
        if (value != null) {
            /*  86 */
            return value;
        }
        /*  88 */
        return defaultValue;
    }

    public static Boolean toBoolean(Object obj) {
        /*  92 */
        if (obj == null) {
            /*  93 */
            return null;
        }
        /*  95 */
        if ((obj instanceof Boolean)) {
            /*  96 */
            return (Boolean) obj;
        }
        /*  98 */
        String str = obj.toString().trim();
        /*  99 */
        if (str.length() > 0) {
            try {
                /* 101 */
                return new Boolean(str);
            } catch (Exception localException) {
            }
        }
        /* 105 */
        return null;
    }

    public static String toString(Object obj) {
        /* 109 */
        if (obj == null) {
            /* 110 */
            return null;
        }
        /* 112 */
        if ((obj instanceof String)) {
            /* 113 */
            return (String) obj;
        }
        /* 115 */
        return obj.toString();
    }

    public static String toString(Object obj, String defaultValue) {
        /* 119 */
        if (obj == null) {
            /* 120 */
            return defaultValue;
        }
        /* 122 */
        if ((obj instanceof String)) {
            /* 123 */
            return (String) obj;
        }
        /* 125 */
        return obj.toString();
    }

    public static String toStringTrim(Object obj) {
        /* 129 */
        if (obj == null) {
            /* 130 */
            return null;
        }
        /* 132 */
        if ((obj instanceof String)) {
            /* 133 */
            return ((String) obj).trim();
        }
        /* 135 */
        return obj.toString().trim();
    }

    public static String toStringTrim(Object obj, String defaultValue) {
        /* 139 */
        if (obj == null) {
            /* 140 */
            return defaultValue;
        }
        /* 142 */
        if ((obj instanceof String)) {
            /* 143 */
            return ((String) obj).trim();
        }
        /* 145 */
        return obj.toString().trim();
    }

    public static Map<String, Object> toHashMap(Object obj) {
        /* 149 */
        Map<String, Object> map = new HashMap();

        /* 151 */
        Field[] fields = obj.getClass().getDeclaredFields();
        /* 152 */
        int i = 0;
        for (int len = fields.length; i < len; i++) {
            /* 153 */
            String varName = fields[i].getName();
            try {
                /* 156 */
                boolean accessFlag = fields[i].isAccessible();

                /* 158 */
                fields[i].setAccessible(true);

                /* 160 */
                Object o = fields[i].get(obj);
                /* 161 */
                if (o != null) {
                    /* 162 */
                    logger.debug("{} value:{}", varName, o.toString());
                    /* 163 */
                    map.put(varName, o.toString());
                } else {
                    /* 165 */
                    map.put(varName, null);
                }


                /* 169 */
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                /* 171 */
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                /* 173 */
                ex.printStackTrace();
            }
        }
        /* 176 */
        return map;
    }


    public static double division(double a, double b, int n) {
        /* 187 */
        double i = Math.pow(10.0D, n);
        /* 188 */
        return Math.round(a * i / b) / i;
    }


    public static String getRandom(int length) {
        /* 197 */
        Random r = new Random();
        /* 198 */
        Double d = Double.valueOf(r.nextDouble());
        /* 199 */
        String s = d + "";
        /* 200 */
        s = s.substring(3, 3 + length);
        /* 201 */
        return s;
    }

    public static boolean isInteger(String intStr) {
        try {
            Integer.valueOf(intStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 任意对象为空判断
     * <br/>
     * @author Mr.Lming
     * @date 2019/5/28 9:32
     * @param obj 	Object
     * @return
     **/
    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        return false;
    }
    /**
     * 任意对象非空判断
     * <br/>
     * @author Mr.Lming
     * @date 2019/5/28 9:32
     * @param obj 	Object
     * @return
     **/
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }
}
