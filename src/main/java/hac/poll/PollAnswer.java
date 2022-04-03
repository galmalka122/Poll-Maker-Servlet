package hac.poll;

import java.util.Objects;

public class PollAnswer implements PollElement{

    private final String title;
    private Integer count;

    PollAnswer(String t){
        this.title = t;
        count = 0;
    }

    @Override
    public final String toString(){
        return this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PollAnswer that = (PollAnswer) o;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, count);
    }

    public String getTitle() {
        return title;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public PoolQuestion getAsPollQuestion() throws ClassCastException{
        throw new ClassCastException("The element is not an answer");
    }

    @Override
    public PollAnswer getAsPollAnswer() throws ClassCastException{
        return this;
    }

    public final PollAnswer setCount() {
        this.count += 1;
        return this;
    }


}
