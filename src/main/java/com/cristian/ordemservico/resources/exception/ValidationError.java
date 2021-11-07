package com.cristian.ordemservico.resources.exception;

import java.util.ArrayList;
import java.util.List;

//Será ultilizada na classe ResourceExceptionHandler

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	

	public ValidationError() {
		super();
	}

	public ValidationError(Long timeStamp, Integer status, String error) {
		super(timeStamp, status, error);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldname, String message) {
		this.errors.add(new FieldMessage(fieldname, message));
	}
	

}
