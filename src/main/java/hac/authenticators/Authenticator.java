package hac.authenticators;

import hac.exceptions.AuthenticateException;

public interface Authenticator {

    void execute() throws AuthenticateException;
}
