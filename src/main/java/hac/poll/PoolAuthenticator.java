package hac.poll;

import hac.authenticators.AnswersCountAuthenticator;
import hac.authenticators.QuestionAuthenticator;
import hac.exceptions.AuthenticateException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoolAuthenticator {

    private final String question;
    private final String[] answers;


    PoolAuthenticator(final String[] pollText) {
        question = pollText[0];
        answers = Arrays.copyOfRange(pollText, 1, pollText.length);
    }

    public final List<PollElement> authenticate() throws AuthenticateException {
        try {
            List<PollElement> elements = new ArrayList<>();
            new QuestionAuthenticator(question).execute();
            elements.add(new PoolQuestion(question));
            new AnswersCountAuthenticator(answers).execute();
            for (String answer : answers) {
                elements.add(new PollAnswer(answer));
            }
            return elements;
        }
        catch (AuthenticateException e){
            throw new AuthenticateException("Authenticate failed\n" + e.getMessage());
        }
    }


}