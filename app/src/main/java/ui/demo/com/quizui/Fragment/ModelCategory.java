package ui.demo.com.quizui.Fragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Design-4 on 3/3/2018.
 */

public class ModelCategory {
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("name")
    public String category;
    @SerializedName("id")
    public String id;

//    public  ModelCategory(String category, int id){
//        this.category=category;
//        this.id=id;
//    }
}
