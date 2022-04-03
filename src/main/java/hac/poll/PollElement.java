package hac.poll;

public interface PollElement {
    PoolQuestion getAsPollQuestion()throws ClassCastException;
    PollAnswer getAsPollAnswer()throws ClassCastException;
}
