package ch.uzh.ifi.hase.soprafs24.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExternalApiService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final RestTemplate restTemplate; //Bean created in Application.java

    public void getInfo() {
        // TODO implement logout function of http only cookies used
    }
}
