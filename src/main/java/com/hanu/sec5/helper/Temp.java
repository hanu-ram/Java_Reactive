package com.hanu.sec5.helper;

public class Temp {
    public static void main(String[] args) {
        int index = 0;
        String text = "Hanu kadjf;ad , sskda kajdjafadjfiajfkmm asdvasdkma Hanum fijsdfaj Hanum dnksd";
        index = text.indexOf("Hanum", index);
        System.out.println(index);
        index++;
        index = text.indexOf("Hanum", index);
        System.out.println(index);
        index++;
        index = text.indexOf("Hanum", index);
        System.out.println(index);
        Integer i1 = 11;
        Integer i2 = 1;
        System.out.println(i1 == i2);
    }
}
