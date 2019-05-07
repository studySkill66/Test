package com.syex.api.tools;

import java.util.regex.Pattern;

/**
 * 正则匹配类
 *
 * @program: syex-api-parent
 * @description: 正则匹配工具类类
 * @author: Mr.Lming
 * @create: 2019-04-25 10:21
 **/

public class RegexUtils {
    private RegexUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 正则验证
     *
     * @param regex 正则表达式
     * @param input 待验证数据
     * @return
     * @author Mr.Lming
     * @date 2019/4/25 13:50
     */
    public static final boolean isMatches(String regex, String input) {
        return Pattern.matches(regex, input);
    }

    /**
     * 正则验证【取isMatches相反值】
     *
     * @param regex 正则表达式
     * @param input 待验证数据
     * @return
     * @author Mr.Lming
     * @date 2019/4/25 13:59
     */
    public static final boolean isNotMatches(String regex, String input) {
        return !Pattern.matches(regex, input);
    }
}
