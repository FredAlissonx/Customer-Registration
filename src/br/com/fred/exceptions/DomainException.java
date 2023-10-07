package br.com.fred.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class DomainException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public DomainException(String msg){
        super(msg);
    }
}
