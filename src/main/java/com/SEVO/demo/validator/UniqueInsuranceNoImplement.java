package com.SEVO.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.SEVO.demo.dao.UserDetailRepository;

public class UniqueInsuranceNoImplement implements ConstraintValidator<UniqueInsuranceNo, Integer>{

	@Autowired
	UserDetailRepository userDetailRepository;
	
	@Override
	public void initialize(UniqueInsuranceNo constraintAnnotation) {
	
}
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (userDetailRepository == null) {
			return true;
			}
			if (userDetailRepository.existsByInsuranceNo(value)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Insurance number " + value + " already exists")
						.addConstraintViolation();
				return false;
			}
			return true;
		
	}

}
