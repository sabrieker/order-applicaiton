package com.boom.validation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckTimeFormatValidator implements ConstraintValidator<CheckTimeFormat, String> {

    
    @Override
    public void initialize(CheckTimeFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        try {
        	SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        	Date eightAM = parser.parse("08:00");
        	Date eightPM = parser.parse("20:00");
        	
            Date time = parser.parse(object);
            return (time.equals(eightAM) || time.after(eightAM)) && (time.equals(eightPM) || time.before(eightPM)) ;
        } catch (Exception e) {
            return false;
        }
    }
}