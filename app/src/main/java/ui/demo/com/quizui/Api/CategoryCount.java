package ui.demo.com.quizui.Api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ui.demo.com.quizui.Fragment.ModelCount;

/**
 * Created by Design-4 on 3/6/2018.
 */

public class CategoryCount {

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public ModelCount getCategory_questcount() {
        return category_questcount;
    }

    public void setCategory_questcount(ModelCount category_questcount) {
        this.category_questcount = category_questcount;
    }

    @SerializedName("category_id")
    public String category_id;
    @SerializedName("category_question_count")
    public ModelCount category_questcount;
}
