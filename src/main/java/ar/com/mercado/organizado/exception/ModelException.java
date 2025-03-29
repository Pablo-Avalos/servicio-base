package ar.com.mercado.organizado.exception;

public class ModelException extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModelException(String errorMessage) {
        super(errorMessage);
    }

}

