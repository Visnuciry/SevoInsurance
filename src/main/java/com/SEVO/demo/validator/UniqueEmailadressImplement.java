package com.SEVO.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.SEVO.demo.dao.UserRepository;

public class UniqueEmailadressImplement implements ConstraintValidator<UniqueEmailadress,String> {
	@Autowired
	private UserRepository userRepository;
	@Override
		public void initialize(UniqueEmailadress constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (userRepository == null) {
		return true;
		}
		if (userRepository.existsByEmailAddress(value)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Emailadress " + value + " already exists")
					.addConstraintViolation();
			return false;
		}
		return true;
	}
	
	

}
