package ui.demo.com.quizui.Api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ui.demo.com.quizui.Quest;

/**
 * Created by Design-4 on 3/6/2018.
 */

public class QuestResponse {
    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public List<Quest> getQuest() {
        return quest;
    }

    public void setQuest(List<Quest> quest) {
        this.quest = quest;
    }

    @SerializedName("response_code")
    public boolean response;
    @SerializedName("results")
    public List<Quest> quest;
}
