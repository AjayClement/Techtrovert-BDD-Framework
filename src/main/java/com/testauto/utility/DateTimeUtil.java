package com.testauto.utility;


import java.sql.Timestamp;

public class DateTimeUtil {


public static String getTimeStamp(){
  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
  return timestamp.toString().replaceAll("[^a-zA-Z0-9]","");
}


}
