package com.example.fishmarket.utils;

import android.widget.EditText;

public class ValidateField {
    public static Boolean isEmptyField(EditText editText,String content){
        if (editText.getText().toString().isEmpty()){
            editText.requestFocus();
            editText.setError("Please Enter "+content);
            return true;
        }
        return false;
    }
    public static void error(EditText editText,String content){
            editText.requestFocus();
            editText.setError(content);

    }
    public static Boolean isValidCount(EditText editText,int count,String content){
        if (editText.getText().toString().length()<count){
            editText.requestFocus();
            editText.setError("Please Enter "+content);
            return true;
        }
        return false;
    }

}
