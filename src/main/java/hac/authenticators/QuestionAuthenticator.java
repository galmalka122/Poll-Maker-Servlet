package hac.authenticators;

import hac.exceptions.AuthenticateException;

public class QuestionAuthenticator implements Authenticator {

    private final String question;

    public QuestionAuthenticator(String question){
        this.question = question;
    }

    public void execute()  throws AuthenticateException {
        if(this.question.charAt(this.question.length() - 1) != '?'){
            throw new AuthenticateException("No question found.\nPlease add to the first line with question mark at the end");
        }
    }


}
