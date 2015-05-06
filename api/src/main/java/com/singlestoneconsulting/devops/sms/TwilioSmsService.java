package com.singlestoneconsulting.devops.sms;

import com.singlestoneconsulting.devops.util.Provider;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.verbs.Sms;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public final class TwilioSmsService implements SmsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsService.class);
    private static final String FROM = "+18046328573";

    private final TwilioRestClient twilio;

    @Autowired
    public TwilioSmsService(final Provider<TwilioRestClient> twilioRestClientProvider) {
        this.twilio = twilioRestClientProvider.provide();
    }

    @Override
    public void sendText(final String to, final String message) {
        HashMap<String, String> params = new HashMap<>();
        params.put("From", FROM);
        params.put("To", to);
        params.put("Body", message);

        try {
            twilio.getAccount().getSmsFactory().create(params);
        } catch (TwilioRestException e) {
            LOGGER.error("Failed to send SMS.", e);
        }
    }

    @Override
    public String getResponse(final String message) {
        TwiMLResponse twiml = new TwiMLResponse();
        try {
            twiml.append(new Sms(message));
        } catch (TwiMLException e) {
            LOGGER.error("Failed to construct response.", e);
            throw new RuntimeException(e);
        }
        return twiml.toXML();
    }
}
