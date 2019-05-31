package com.hksy.framework.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




public class JsonUtils
{
  public static String stringToJson(String s)
  {
/*  28 */     if (s == null) {
/*  29 */       return nullToJson();
    }
/*  31 */     StringBuilder sb = new StringBuilder();
/*  32 */     for (int i = 0; i < s.length(); i++) {
/*  33 */       char ch = s.charAt(i);
/*  34 */       switch (ch) {
      case '"':
/*  36 */         sb.append("\\\"");
/*  37 */         break;
      case '\\':
/*  39 */         sb.append("\\\\");
/*  40 */         break;
      case '\b':
/*  42 */         sb.append("\\b");
/*  43 */         break;
      case '\f':
/*  45 */         sb.append("\\f");
/*  46 */         break;
      case '\n':
/*  48 */         sb.append("\\n");
/*  49 */         break;
      case '\r':
/*  51 */         sb.append("\\r");
/*  52 */         break;
      case '\t':
/*  54 */         sb.append("\\t");
/*  55 */         break;
      case '/':
/*  57 */         sb.append("\\/");
/*  58 */         break;
      default:
/*  60 */         if ((ch >= 0) && (ch <= '\037')) {
/*  61 */           String ss = Integer.toHexString(ch);
/*  62 */           sb.append("\\u");
/*  63 */           for (int k = 0; k < 4 - ss.length(); k++) {
/*  64 */             sb.append('0');
          }
/*  66 */           sb.append(ss.toUpperCase());
        } else {
/*  68 */           sb.append(ch);
        }
        break; }
    }
/*  72 */     return sb.toString();
  }

  public static String nullToJson() {
/*  76 */     return "";
  }






  public static String objectToJson(Object obj)
  {
/*  86 */     StringBuilder json = new StringBuilder();
/*  87 */     if (obj == null) {
/*  88 */       json.append("\"\"");
/*  89 */     } else if ((obj instanceof Number)) {
/*  90 */       json.append(numberToJson((Number)obj));
/*  91 */     } else if ((obj instanceof Boolean)) {
/*  92 */       json.append(booleanToJson((Boolean)obj));
/*  93 */     } else if ((obj instanceof String)) {
/*  94 */       json.append("\"").append(stringToJson(obj.toString())).append("\"");
/*  95 */     } else if ((obj instanceof Object[])) {
/*  96 */       json.append(arrayToJson((Object[])obj));
/*  97 */     } else if ((obj instanceof List)) {
/*  98 */       json.append(listToJson((List)obj));
/*  99 */     } else if ((obj instanceof Map)) {
/* 100 */       json.append(mapToJson((Map)obj));
/* 101 */     } else if ((obj instanceof Set)) {
/* 102 */       json.append(setToJson((Set)obj));
    } else {
/* 104 */       json.append(beanToJson(obj));
    }
/* 106 */     return json.toString();
  }

  public static String numberToJson(Number number) {
/* 110 */     return number.toString();
  }

  public static String booleanToJson(Boolean bool) {
/* 114 */     return bool.toString();
  }






  public static String beanToJson(Object bean)
  {
/* 124 */     StringBuilder json = new StringBuilder();
/* 125 */     json.append("{");
/* 126 */     PropertyDescriptor[] props = null;
    try
    {
/* 129 */       props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
    }
    catch (IntrospectionException localIntrospectionException) {}
/* 132 */     if (props != null) {
/* 133 */       for (int i = 0; i < props.length; i++) {
        try {
/* 135 */           String name = objectToJson(props[i].getName());
/* 136 */           String value = objectToJson(props[i].getReadMethod()
/* 137 */             .invoke(bean, new Object[0]));
/* 138 */           json.append(name);
/* 139 */           json.append(":");
/* 140 */           json.append(value);
/* 141 */           json.append(",");
        }
        catch (Exception localException) {}
      }
/* 145 */       json.setCharAt(json.length() - 1, '}');
    } else {
/* 147 */       json.append("}");
    }
/* 149 */     return json.toString();
  }






  public static String listToJson(List<?> list)
  {
/* 159 */     StringBuilder json = new StringBuilder();
/* 160 */     json.append("[");
/* 161 */     if ((list != null) && (list.size() > 0)) {
/* 162 */       for (Object obj : list) {
/* 163 */         json.append(objectToJson(obj));
/* 164 */         json.append(",");
      }
/* 166 */       json.setCharAt(json.length() - 1, ']');
    } else {
/* 168 */       json.append("]");
    }
/* 170 */     return json.toString();
  }






  public static String arrayToJson(Object[] array)
  {
/* 180 */     StringBuilder json = new StringBuilder();
/* 181 */     json.append("[");
/* 182 */     if ((array != null) && (array.length > 0)) {
/* 183 */       for (Object obj : array) {
/* 184 */         json.append(objectToJson(obj));
/* 185 */         json.append(",");
      }
/* 187 */       json.setCharAt(json.length() - 1, ']');
    } else {
/* 189 */       json.append("]");
    }
/* 191 */     return json.toString();
  }






  public static String mapToJson(Map<?, ?> map)
  {
/* 201 */     StringBuilder json = new StringBuilder();
/* 202 */     json.append("{");
/* 203 */     if ((map != null) && (map.size() > 0)) {
/* 204 */       for (Object key : map.keySet()) {
/* 205 */         json.append(objectToJson(key));
/* 206 */         json.append(":");
/* 207 */         json.append(objectToJson(map.get(key)));
/* 208 */         json.append(",");
      }
/* 210 */       json.setCharAt(json.length() - 1, '}');
    } else {
/* 212 */       json.append("}");
    }
/* 214 */     return json.toString();
  }






  public static String setToJson(Set<?> set)
  {
/* 224 */     StringBuilder json = new StringBuilder();
/* 225 */     json.append("[");
/* 226 */     if ((set != null) && (set.size() > 0)) {
/* 227 */       for (Object obj : set) {
/* 228 */         json.append(objectToJson(obj));
/* 229 */         json.append(",");
      }
/* 231 */       json.setCharAt(json.length() - 1, ']');
    } else {
/* 233 */       json.append("]");
    }
/* 235 */     return json.toString();
  }





  public static List<Map<String, Object>> parseJSONList(String jsonStr)
  {
/* 244 */     JSONArray jsonArr = JSONArray.fromObject(jsonStr);
/* 245 */     List<Map<String, Object>> list = new ArrayList();
/* 246 */     Iterator<JSONObject> it = jsonArr.iterator();
/* 247 */     while (it.hasNext()) {
/* 248 */       JSONObject JSON = (JSONObject)it.next();
/* 249 */       list.add(parseJSONMap(JSON.toString()));
    }
/* 251 */     return list;
  }






  public static Map<String, Object> parseJSONMap(String jsonStr)
  {
/* 261 */     Map<String, Object> map = new HashMap();

/* 263 */     JSONObject json = JSONObject.fromObject(jsonStr);
/* 264 */     for (Object k : json.keySet()) {
/* 265 */       Object v = json.get(k);

/* 267 */       if ((v instanceof JSONArray)) {
/* 268 */         List<Map<String, Object>> list = new ArrayList();
/* 269 */         Iterator<JSONObject> it = ((JSONArray)v).iterator();
/* 270 */         while (it.hasNext()) {
/* 271 */           JSONObject JSON = (JSONObject)it.next();
/* 272 */           list.add(parseJSONMap(JSON.toString()));
        }
/* 274 */         map.put(k.toString(), list);
      } else {
/* 276 */         map.put(k.toString(), v);
      }
    }
/* 279 */     return map;
  }






  public static List<Map<String, Object>> getListByUrl(String url)
  {
    try
    {
/* 291 */       InputStream in = new URL(url).openStream();
/* 292 */       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
/* 293 */       StringBuilder sb = new StringBuilder();
      String line;
/* 295 */       while ((line = reader.readLine()) != null) {
/* 296 */         sb.append(line);
      }
/* 298 */       return parseJSONList(sb.toString());
    } catch (Exception e) {
/* 300 */       e.printStackTrace();
    }
/* 302 */     return null;
  }






  public static Map<String, Object> getMapByUrl(String url)
  {
    try
    {
/* 314 */       InputStream in = new URL(url).openStream();
/* 315 */       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
/* 316 */       StringBuilder sb = new StringBuilder();
      String line;
/* 318 */       while ((line = reader.readLine()) != null) {
/* 319 */         sb.append(line);
      }
/* 321 */       return parseJSONMap(sb.toString());
    } catch (Exception e) {
/* 323 */       e.printStackTrace();
    }
/* 325 */     return null;
  }
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/JsonUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */