package us.hyalen.trippmember.config;

import us.hyalen.trippmember.core.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.ValidatorFactory;

@Component
public class DomainConfig {
    @Autowired
    private ValidatorFactory validatorFactory;

    @PostConstruct
    void injectDependencies() {
        Domain.setValidator(validatorFactory.getValidator());
    }
}
