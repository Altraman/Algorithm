package com.hdu.genetic.genetic2;

public class MathUtils {
    public static double binToTen(String binary) {
        //查找该二进制是否存在小数点
        int index = binary.indexOf('.');
        //转化成的十进制
        double ten = 0;
        //整数部分
        double integer = 0;
        //小数部分
        double decimal = 0;
        //index等于-1，说明没有小数部分
        if (index == -1) {
            for (int i = 0; i < binary.length(); i++) {
                //48为字符'0'对应的ASCII值；
                //ten += (binary.charAt(i) - 48) * Math.pow(2, -(i-(binary.length() - 1)));
                //或者可以将字符转化为字符串，再由字符串转化为数字
                ten += Integer.parseInt(String.valueOf(binary.charAt(i))) * Math.pow(2, -(i - (binary.length() - 1)));
            }
        } else {
            //计算整数部分
            for (int i = 0; i < index; i++) {
                integer += Integer.parseInt(String.valueOf(binary.charAt(i))) * Math.pow(2, -(i - (index - 1)));
            }
            //计算小数部分
            for (int j = index + 1; j < binary.length(); j++) {
                decimal += Integer.parseInt(String.valueOf(binary.charAt(j))) * Math.pow(2, (index - j));
            }
            ten = integer + decimal;
        }
        return ten;
    }
}
