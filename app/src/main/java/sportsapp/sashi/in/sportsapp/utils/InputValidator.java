package sportsapp.sashi.in.sportsapp.utils;

import android.text.TextUtils;

public class InputValidator {

    public static boolean isEmpty(String string){
        if (TextUtils.isEmpty(string)){
            return false;
        }
        return true;
    }

}
