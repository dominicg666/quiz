package ui.demo.com.quizui.Fragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Design-4 on 3/6/2018.
 */

public class ModelCount {
    @SerializedName("total_question_count")
    public String QuestCount;

    public String getQuestCount() {
        return QuestCount;
    }

    public void setQuestCount(String questCount) {
        QuestCount = questCount;
    }

    public String getEasyCount() {
        return EasyCount;
    }

    public void setEasyCount(String easyCount) {
        EasyCount = easyCount;
    }

    public String getMediumCount() {
        return MediumCount;
    }

    public void setMediumCount(String mediumCount) {
        MediumCount = mediumCount;
    }

    public String getHardCount() {
        return HardCount;
    }

    public void setHardCount(String hardCount) {
        HardCount = hardCount;
    }

    @SerializedName("total_easy_question_count")
    public String EasyCount;
    @SerializedName("total_medium_question_count")
    public String MediumCount;
    @SerializedName("total_hard_question_count")
    public String HardCount;

}
