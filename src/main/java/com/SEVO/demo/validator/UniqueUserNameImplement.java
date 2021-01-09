package com.SEVO.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.SEVO.demo.dao.UserRepository;

public class UniqueUserNameImplement implements ConstraintValidator<UniqueUserName, String> {

	@Autowired
	private UserRepository userRepository;
	@Override
		public void initialize(UniqueUserName constraintAnnotation) {
		
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (userRepository == null) {
			return true;
			}
			if (userRepository.existsByUserName(value)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Username " + value + " already exists")
						.addConstraintViolation();
				return false;
			}
			return true;
		}
		
	}

