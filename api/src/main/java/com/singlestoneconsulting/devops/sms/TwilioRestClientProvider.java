package com.singlestoneconsulting.devops.sms;

import com.singlestoneconsulting.devops.util.Provider;
import com.twilio.sdk.TwilioRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class TwilioRestClientProvider implements Provider<TwilioRestClient> {

    private final TwilioRestClient twilioRestClient;

    @Autowired
    public TwilioRestClientProvider(final TwilioSettings smsSettings) {
        this.twilioRestClient = new TwilioRestClient(smsSettings.getSid(), smsSettings.getAuthToken());
    }

    @Override
    public TwilioRestClient provide() {
        return twilioRestClient;
    }
}
