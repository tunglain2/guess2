package com.tunglain.guess2;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Random;

public class GuessViewModel extends ViewModel {
    private MutableLiveData<Integer> counterLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageLiveData = new MutableLiveData<>();
    private int secret;

    public MutableLiveData<Integer> getCounter() {
        return counterLiveData;
    }

    public MutableLiveData<String> getMessage() {
        return messageLiveData;
    }

    public void resetGame() {
        counterLiveData.setValue(0);
        secret = new Random().nextInt(10) + 1 ;
    }

    public void setNumber(int number) {
        String msg = "Yes, you got it";
        if (number > secret) {
            msg = "Smaller";
        }else if (number < secret) {
            msg = "Bigger";
        }
        messageLiveData.setValue(msg);
        counterLiveData.setValue(counterLiveData.getValue()+1);
    }
}
