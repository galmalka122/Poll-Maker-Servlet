package hac.authenticators;

import hac.exceptions.AuthenticateException;

public class AnswersCountAuthenticator implements Authenticator {

    private final String[] answers;

    public AnswersCountAuthenticator(final String[] answers){
        this.answers = answers;
    }

    @Override
    public void execute() throws AuthenticateException {
        if(answers.length < 2)
            throw new AuthenticateException("Wrong number of answers: " + answers.length);
    }
}
