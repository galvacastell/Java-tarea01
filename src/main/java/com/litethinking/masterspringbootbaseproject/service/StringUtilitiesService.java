package com.litethinking.masterspringbootbaseproject.service;

import com.litethinking.masterspringbootbaseproject.domain.StringPayload;
import com.litethinking.masterspringbootbaseproject.repository.StringUtilitiesRedisHash;
import com.litethinking.masterspringbootbaseproject.repository.StringUtilitiesRedisRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StringUtilitiesService {
    final Logger logger = LoggerFactory.getLogger(StringUtilitiesService.class);
    final StringUtilitiesRedisRepository stringUtilitiesRedisRepository;

    @Autowired
    public StringUtilitiesService(StringUtilitiesRedisRepository stringUtilitiesRedisRepository) {
        this.stringUtilitiesRedisRepository = stringUtilitiesRedisRepository;
    }

    
    public StringPayload concatenaNombre(StringPayload payload){
        String temp = payload.string().concat(" ");
        
        temp= "Hola ".concat(temp.trim()).concat(" bienvenido a spring boot");
        
        logger.debug("OK concatenaNombre");
        return new StringPayload(temp);
    }
    
    public StringPayload toUpperCase(StringPayload payload){
        String temp = payload.string().toUpperCase();
        logger.debug("OK upperCase");
        return new StringPayload(temp);
    }

    public StringPayload toUpperCaseWithPersistence(StringPayload payload){
        String temp = payload.string().toUpperCase();
        StringPayload result = new StringPayload(temp);
        stringUtilitiesRedisRepository.save(new StringUtilitiesRedisHash(result));
        return result;
    }
}
