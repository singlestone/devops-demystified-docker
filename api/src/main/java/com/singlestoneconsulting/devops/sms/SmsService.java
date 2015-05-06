package com.singlestoneconsulting.devops.sms;

public interface SmsService {

    void sendText(String to, String message);

    String getResponse(String message);
}