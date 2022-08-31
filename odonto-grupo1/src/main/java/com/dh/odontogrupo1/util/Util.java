package com.dh.odontogrupo1.util;

import java.sql.Timestamp;
import java.util.Date;

public class Util {
    public static Timestamp DateToTimeStamp(Date date){
        Timestamp timestamp = new Timestamp (date.getTime());
        return timestamp;
    }
}
