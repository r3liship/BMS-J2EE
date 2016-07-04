package wang.relish.bms.util;

public class TextUtils {

    /**
     * 字符串非空判断
     * 属于空的：（NULL，‘’， ‘null’,'NULL'）
     *
     * @param paramStr 待判断的字符串
     * @return true：非空，false：空
     */
    public static boolean isNotEmpty(String paramStr) {
        if (paramStr == null) {
            return false;
        }
        if (paramStr.isEmpty()) {
            return false;
        }
        paramStr = paramStr.trim();
        if (paramStr.equals("")) {
            return false;
        }
        if (paramStr.equals("null")) {
            return false;
        }
        if (paramStr.equals("NULL")) {
            return false;
        }

        return true;
    }

    public static boolean isNotEmpty(Number o) {
        if (o == null) {
            return false;
        }
        if (o.equals(0)) {
            return false;
        }
        if (String.valueOf(o).equals("")) {
            return false;
        }
        if (String.valueOf(o).equals("null")) {
            return false;
        }
        if (String.valueOf(o).equals("NULL")) {
            return false;
        }
        return true;
    }

    /**
     * 针对搜索添加字符串进行处理，把'null'、'NULL'处理成''空字符串，非空的字符串会执行trim
     *
     * @param paramStr 待处理的字符串
     * @return 处理后的字符串
     */
    public static String stringNullHandle(String paramStr) {
        if (isNotEmpty(paramStr)) {
            return paramStr.trim();
        } else {
            return "";
        }
    }

}
