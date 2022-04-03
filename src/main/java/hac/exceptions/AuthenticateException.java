package hac.exceptions;

import java.util.Arrays;

public class AuthenticateException extends Exception{
    public AuthenticateException(String... authenticatorFailed){
        super("Authenticate failed: " + authenticatorFailed.toString());
    }
}