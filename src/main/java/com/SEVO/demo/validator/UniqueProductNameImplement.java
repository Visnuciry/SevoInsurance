package com.SEVO.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.SEVO.demo.dao.ProductRepository;

public class UniqueProductNameImplement implements ConstraintValidator<UniqueProductName, String>{

	ProductRepository productrepository;
	@Override
	public void initialize(UniqueProductName constraintAnnotation) {
	
}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (productrepository == null) {
			return true;
			}
			if (productrepository.findByProductName(value)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("productname " + value + " already exists")
						.addConstraintViolation();
				return false;
			}
			return true;
		}
		
	
}
