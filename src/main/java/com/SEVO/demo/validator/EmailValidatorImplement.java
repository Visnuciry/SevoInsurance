package com.SEVO.demo.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidatorImplement implements ConstraintValidator<EmailValidator, String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	@Override
	public void initialize(EmailValidator constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String Email, ConstraintValidatorContext context) {
		if (!validateEmail(Email)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Emailadress " + Email + " not vaild")
					.addConstraintViolation();
			return false;
		}
		return true;
	}

	private boolean validateEmail(String Email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(Email);
		return matcher.matches();
	}

}
