package com.cristian.ordemservico.resources.exception;

import java.io.Serializable;

/*Classe de apoio para utlização na classe Rersouce Exception Handler*/

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timeStamp;
	private Integer status;
	private String error;

	public StandardError() {

		super();
	}

	public StandardError(Long timeStamp, Integer status, String error) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.error = error;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
