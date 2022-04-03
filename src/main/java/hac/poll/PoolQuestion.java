package hac.poll;

public class PoolQuestion implements PollElement{

    private final String title;

    PoolQuestion(String t){
        this.title = t;
    }

    @Override
    public final String toString(){
        return this.title;
    }

    @Override
    public PoolQuestion getAsPollQuestion() throws ClassCastException{
        return this;
    }

    @Override
    public PollAnswer getAsPollAnswer() throws ClassCastException{
        throw new ClassCastException("The element is not a question");
    }
}
