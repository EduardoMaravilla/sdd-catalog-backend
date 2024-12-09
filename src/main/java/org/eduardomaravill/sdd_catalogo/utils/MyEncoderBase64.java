package org.eduardomaravill.sdd_catalogo.utils;

import java.util.Base64;

public class MyEncoderBase64 {

    private MyEncoderBase64(){}

    public static String encoderDoubleToBase64(Double value){
        if (value != null){
            return Base64.getEncoder().encodeToString(Double.toString(value).getBytes());
        }else {
            return  "MC4w";
        }

    }

    public static Double decodeBase64ToDouble(String value){
        if (!value.isBlank()){
            return Double.parseDouble(new String(Base64.getDecoder().decode(value)));
        }else{
            return 0.0;
        }

    }
}
