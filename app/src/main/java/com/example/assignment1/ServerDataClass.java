package com.example.assignment1;

public class ServerDataClass {

    static String BASE_URL = "http://";
    static String IP_Address;
    static String SUB_URL_HOME = "/machine_monitoring/mobile_app/home_dashboard";
    static String SUB_URL_DOWNTIME = "/machine_dashboard/mobile_app/home_dashboard";
    static String SUB_URL_PRODUCTION = "/machine_dashboard/mobile_app/home_dashboard";


    public static String getUrlHome() {
        return BASE_URL + IP_Address + SUB_URL_HOME;
    }

    public static String getUrlDowntime() {
        return BASE_URL + IP_Address + SUB_URL_DOWNTIME;
    }

    public static String getUrlProduction() {
        return BASE_URL + IP_Address + SUB_URL_PRODUCTION;
    }


    public static void setIP_Address(String IP_Address) {
        ServerDataClass.IP_Address = IP_Address;
    }
}
