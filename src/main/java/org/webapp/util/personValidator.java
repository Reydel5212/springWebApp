package org.webapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.webapp.dao.personDAO;
import org.webapp.models.person;

@Component
public class personValidator implements Validator {
    private final personDAO personDAO;

    @Autowired
    public personValidator(personDAO personDAO){
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass){
        return person.class.equals(aClass);
    }
    @Override
    public void validate(Object o, Errors errors){
        person person = (person) o;

        if(personDAO.show(person.getEmail()).isPresent()){
            errors.rejectValue("email","","This email is already taken");
        }
    }
}
