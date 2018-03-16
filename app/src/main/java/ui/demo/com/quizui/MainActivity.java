package ui.demo.com.quizui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.transition.Fade;
import android.util.Log;
import android.widget.Button;

import java.util.List;

import link.fls.swipestack.SwipeStack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.demo.com.quizui.Api.AsyncQuest;
import ui.demo.com.quizui.Api.CategoryClient;
import ui.demo.com.quizui.Api.CategoryCount;
import ui.demo.com.quizui.Api.CategoryInterface;
import ui.demo.com.quizui.Fragment.ModelCount;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private final static String API_KEY = "";
    Dialog dialog;
    String category;
    String code;
    List<Button> btList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        category = intent.getStringExtra("category");
        getSupportActionBar().setTitle(category);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dailog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        btList=new ArrayList<Button>();
        btList.add((Button) findViewById(R.id.question_level_no));
        btList.add((Button) findViewById(R.id.question_timeout));
        btList.add((Button) findViewById(R.id.quest_success));
        btList.add((Button) findViewById(R.id.quest_fail));
        btList.add((Button) findViewById(R.id.score_point));
        btList.add((Button) findViewById(R.id.total_quest));


        CategoryInterface apiService =
                CategoryClient.getClient().create(CategoryInterface.class);

        Call<CategoryCount> call = apiService.getCategoryCount(code,API_KEY);
        call.enqueue(new Callback<CategoryCount>() {
            @Override
            public void onResponse(Call<CategoryCount>call, Response<CategoryCount> response) {
                dialog.hide();
                ModelCount category = response.body().getCategory_questcount();
                btList.get(5).setText(category.getQuestCount());
                String url = String.format("https://opentdb.com/api.php?amount=%s&category=%s&type=multiple",
                        String.valueOf(category.getQuestCount()), String.valueOf(code));
                new AsyncQuest(MainActivity.this,btList,dialog).execute(url);
//                AdapterCategory adapter = new AdapterCategory(getActivity(), category);
//                int resId = R.anim.layout_animation_fall_down;
//                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(rootView.getContext(), resId);
//                listView.setLayoutAnimation(animation);
//                listView.setAdapter(adapter);
//                Log.d(TAG, "Number of movies received: " + category.size());
            }

            @Override
            public void onFailure(Call<CategoryCount>call, Throwable t) {
                // Log error here since request failed
                dialog.hide();
                Log.e(TAG, t.toString());
            }
        });

        //swipeStack.setListener(this);
        //getAnswer(0);



    }
    @Override
    public boolean onSupportNavigateUp() {
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
        onBackPressed();
        return true;
    }
    private void changeLayout() {

    }



}
