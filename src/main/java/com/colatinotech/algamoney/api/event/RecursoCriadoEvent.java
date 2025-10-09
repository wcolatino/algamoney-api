package com.colatinotech.algamoney.api.event;

import org.springframework.context.ApplicationEvent;

import jakarta.servlet.http.HttpServletResponse;

/*RecursoCriadoEvent - Evento. Toda vez que for necessário adicionar o Header Location, esse evento será disparado, receberá os dados e retornará o código no location;*/
public class RecursoCriadoEvent extends ApplicationEvent{


	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private Long codigo;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.response = response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	

}
