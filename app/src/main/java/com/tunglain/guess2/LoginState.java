package com.tunglain.guess2;

public class LoginState {
    private String emailMessage;
    private String passwordMessage;
    private boolean dataValid;

    public LoginState(boolean dataValid) {
        this.dataValid = dataValid;
    }

    public LoginState(String emailMessage, String passwordMessage) {
        this.emailMessage = emailMessage;
        this.passwordMessage = passwordMessage;
        this.dataValid = false;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public String getPasswordMessage() {
        return passwordMessage;
    }

    public void setPasswordMessage(String passwordMessage) {
        this.passwordMessage = passwordMessage;
    }

    public boolean isDataValid() {
        return dataValid;
    }

    public void setDataValid(boolean dataValid) {
        this.dataValid = dataValid;
    }
}
