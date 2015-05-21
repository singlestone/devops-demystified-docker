package com.singlestoneconsulting.devops.rest;

import com.singlestoneconsulting.devops.AppSettings;
import com.singlestoneconsulting.devops.participant.Participant;
import com.singlestoneconsulting.devops.participant.ParticipantRepository;
import com.singlestoneconsulting.devops.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public final class ParticipantController {

    private static final String MSG_REPLY = "Thanks %s! Check it out: %s";
    private static final String MSG_DEPLOY = "Hey %s, check out the new version: %s";

    private final ParticipantRepository participantRepository;
    private final SmsService smsService;
    private final AppSettings appSettings;

    @Autowired
    public ParticipantController(final ParticipantRepository participantRepository,
                                 final SmsService smsService,
                                 final AppSettings appSettings) {
        this.participantRepository = participantRepository;
        this.smsService = smsService;
        this.appSettings = appSettings;
    }

    @RequestMapping(value = "/participants", method = GET, produces = APPLICATION_JSON_VALUE)
    public Iterable<Participant> participants() {
        return participantRepository.findAll();
    }

    @RequestMapping(value = "/broadcast", method = POST)
    public ResponseEntity<String> broadcast(final String message) {
        for (Participant p : participantRepository.findAll()) {
            smsService.sendText(p.getPhoneNumber(), message);
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(value = "/twilio", method = POST)
    public ResponseEntity<String> sms(@RequestParam("From") final String from,
                                      @RequestParam("Body") final String body) {
        Participant participant = participantRepository.findOne(from);
        if (participant == null) {
            participant = new Participant(from);
        }
        participant.setName(body);

        participantRepository.save(participant);

        return sendXml(smsService.getResponse(String.format(MSG_REPLY, participant.getName(), appSettings.getUrl())));
    }

    @PostConstruct
    public void postConstruct() {
        for (Participant p : participantRepository.findAll()) {
            smsService.sendText(p.getPhoneNumber(), String.format(MSG_DEPLOY, p.getName(), appSettings.getUrl()));
        }
    }

    private ResponseEntity<String> sendXml(final String xml) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_XML);
        return new ResponseEntity<>(xml, headers, HttpStatus.OK);
    }
}
