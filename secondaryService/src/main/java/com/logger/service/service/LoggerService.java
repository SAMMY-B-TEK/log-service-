package com.logger.service.service;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    private static final Logger log = LoggerFactory.getLogger(LoggerService.class);
    public String getMobileNumber() {
        log.debug("getMobileNumber method entered");
        Faker instance = Faker.instance();
        String mobileNumber = instance.phoneNumber().cellPhone();
        log.info("mobile number is {}" , mobileNumber);
        log.debug("getMobileNumber method ends");
        return mobileNumber;
    }
}
