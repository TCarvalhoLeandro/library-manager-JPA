package com.leandro.library_manager_JPA.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException (Object id) {// Recebe o id do objeto que eu queria encontrar e não encontrei
		super("Resource not found. Id: " + id);
	}
	
}
