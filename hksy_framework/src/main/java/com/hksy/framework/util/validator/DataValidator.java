//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hksy.framework.util.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hksy.framework.util.validator.ValidatorEnum;
import org.apache.commons.lang3.StringUtils;

public class DataValidator {
    private static Pattern PATTERN = null;
    private static Matcher MATCHER = null;
    private static Map<ValidatorEnum, String> validator = new HashMap<ValidatorEnum, String>() {
        {
            this.put(ValidatorEnum.IS_INTEGER, "^-?[1-9]\\d*$");
            this.put(ValidatorEnum.IS_NUMBER, "^([+-]?)\\d*\\.?\\d+$");
            this.put(ValidatorEnum.IS_DECIMAL, "^([+-]?)\\d*\\.\\d+$");
            this.put(ValidatorEnum.IS_EMAIL, "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
            this.put(ValidatorEnum.IS_URL, "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$");
            this.put(ValidatorEnum.IS_CHINESE, "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");
            this.put(ValidatorEnum.IS_ZIPCODE, "^\\d{6}$");
            this.put(ValidatorEnum.IS_IP, "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$");
            this.put(ValidatorEnum.IS_PICTURE, "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$");
            this.put(ValidatorEnum.IS_RAR, "(.*)\\.(rar|zip|7zip|tgz)$");
            this.put(ValidatorEnum.IS_DATE, "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
            this.put(ValidatorEnum.IS_TELEPHONE, "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$");
            this.put(ValidatorEnum.IS_LETTER, "^[A-Za-z]+$");
            this.put(ValidatorEnum.IS_LETTER_U, "^[A-Z]+$");
            this.put(ValidatorEnum.IS_LETTER_L, "^[a-z]+$");
            this.put(ValidatorEnum.IS_IDCARDNUMBER_OLD, "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
            this.put(ValidatorEnum.IS_IDCARDNUMBER_NEW, "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
        }
    };

    public DataValidator() {
    }

    public static boolean valid(ValidatorEnum validatorEnum, String value) {
        if (validatorEnum != null && !StringUtils.isBlank(value)) {
            PATTERN = Pattern.compile((String)validator.get(validatorEnum));
            MATCHER = PATTERN.matcher(value);
            return MATCHER.matches();
        } else {
            return false;
        }
    }

    public static boolean pass_Intensity(String pass, DataValidator.PassLevelEnum minLevel) {
        String regexThree = "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9⺀-\u9fff]).{6,}";
        String regexTwo = "(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,}";
        String regexOne = "([A-Za-z]{6,})|(\\d{6,})";
        if (!pass.matches(regexThree)) {
            if (minLevel.equals(DataValidator.PassLevelEnum.THREE_LEVEL)) {
                return false;
            } else if (!pass.matches(regexTwo)) {
                if (minLevel.equals(DataValidator.PassLevelEnum.TOW_LEVEL)) {
                    return false;
                } else {
                    return pass.matches(regexOne);
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static enum PassLevelEnum {
        ONE_LEVEL,
        TOW_LEVEL,
        THREE_LEVEL;

        private PassLevelEnum() {
        }
    }
}
