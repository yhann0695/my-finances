package br.com.investimento.financas.exception;

public class FileException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}