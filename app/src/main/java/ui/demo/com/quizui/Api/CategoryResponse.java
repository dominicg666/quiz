package ui.demo.com.quizui.Api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ui.demo.com.quizui.Fragment.ModelCategory;

/**
 * Created by Design-4 on 3/5/2018.
 */

public class CategoryResponse {
    public List<ModelCategory> getResults() {
        return results;
    }

    public void setResults(List<ModelCategory> results) {
        this.results = results;
    }

    @SerializedName("trivia_categories")
    private List<ModelCategory> results;
}
