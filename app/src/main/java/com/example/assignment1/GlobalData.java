package com.example.assignment1;

public class GlobalData {

    static String BASE_URL = "https://";
    static String IP_Address;
    static String SUB_URL = "";


    public static String getUrl(){
        return BASE_URL + IP_Address + SUB_URL;
    }

    public static void setIP_Address(String IP_Address) {
        GlobalData.IP_Address = IP_Address;
    }
}
