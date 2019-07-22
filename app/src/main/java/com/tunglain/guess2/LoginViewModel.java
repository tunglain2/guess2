package com.tunglain.guess2;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.util.Patterns;

public class LoginViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    private MutableLiveData<LoginState> loginStateLiveData = new MutableLiveData<>();

    public MutableLiveData<LoginState> getLoginStateLiveData(){
        return loginStateLiveData;
    }


    public void validate(String email,String password) {
        String emailMsg = "";
        String pwdMsg = "";
        int number = 0;
        int capital = 0;
        int small = 0 ;
        int other = 0 ;
        int sum = 0;
        if ("".equals(email)) {
            emailMsg = "email是空白";
        }else {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailMsg = "email 格式錯誤";
            }
        }
        if ("".equals(password)) {
            pwdMsg = "密碼是空白";
        }else {
            if(password.length() < 8) {
                pwdMsg = "密碼應該大於8碼以上";
            }else {
                for (int i = 0; i < password.length(); i++) {
                    //數字
                    if (password.charAt(i) > 47 && password.charAt(i) < 58) {
                        number = 1;
                    }
                    //大寫字母
                    if (password.charAt(i) > 64 && password.charAt(i) < 91) {
                        capital = 1;
                    }
                    //小寫字母
                    if (password.charAt(i) > 96 && password.charAt(i) < 123) {
                        small = 1;
                    }
                    //其它符號
                    if (password.charAt(i) > 26 && password.charAt(i) < 48) {
                        other = 1;
                    }
                    if (password.charAt(i) > 57 && password.charAt(i) < 65) {
                        other = 1;
                    }
                    if (password.charAt(i) > 90 && password.charAt(i) < 97) {
                        other = 1;
                    }
                    if (password.charAt(i) > 122 && password.charAt(i) < 127) {
                        other = 1;
                    }
                }
                sum = number + capital + small + other;

                if (sum < 3) {
                    pwdMsg = "密碼欄位必需3種不同字元";
                }
            }
        }
        Log.d(TAG, "validate: " + sum + "/" + emailMsg + "/" + pwdMsg);
        loginStateLiveData.setValue(new LoginState(emailMsg,pwdMsg));
        if("".equals(emailMsg) && "".equals(pwdMsg)) {
            loginStateLiveData.setValue(new LoginState(true));
        }
    }
}
