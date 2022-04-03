package hac.poll;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hac.exceptions.AuthenticateException;
import java.util.List;

public class PollManager {
    private final List<PollElement> pollElements;
    private final JsonArray chart;
    private final JsonObject poll;

    public PollManager(String[] pollText) throws AuthenticateException {
        PoolAuthenticator auth = new PoolAuthenticator(pollText);
        pollElements = auth.authenticate();
        chart = new JsonArray();
        poll = new JsonObject();
        initJson();
    }

    public synchronized void updateVotes(String ans) throws IllegalArgumentException {
        int index = pollElements.indexOf(new PollAnswer(ans));
        if (index != -1) {
            PollAnswer curAnswer = pollElements.get(index).getAsPollAnswer().setCount();
            chart.get(index - 1).getAsJsonObject().remove("votes");
            chart.get(index - 1).getAsJsonObject().addProperty("votes",curAnswer.getCount());
        }
        else
            throw new IllegalArgumentException("Such answer does not exists");
    }

    private void initJson() {
        poll.addProperty("question",pollElements.get(0).getAsPollQuestion().toString());
        JsonArray pollArr = new JsonArray();
        for (PollElement ans : pollElements.subList(1,pollElements.size())) {
            pollArr.add(ans.getAsPollAnswer().toString());
            JsonObject a = new JsonObject();
            a.addProperty("answer",ans.getAsPollAnswer().getTitle());
            a.addProperty("votes",ans.getAsPollAnswer().getCount());
            chart.add(a);
        }
        poll.add("answers",pollArr);
    }
    public final String getChartJson(){
        Gson s = new Gson();
        return s.toJson(this.chart);
    }

    public final String getPollJson(){
        Gson s = new Gson();
        return s.toJson(this.poll);
    }

}


