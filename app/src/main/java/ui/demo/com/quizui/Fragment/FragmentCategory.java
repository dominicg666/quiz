package ui.demo.com.quizui.Fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.demo.com.quizui.Api.CategoryClient;
import ui.demo.com.quizui.Api.CategoryInterface;
import ui.demo.com.quizui.Api.CategoryResponse;
import ui.demo.com.quizui.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Design-4 on 3/3/2018.
 */

public class FragmentCategory extends Fragment {
    View rootView;
    RecyclerView listView;
    Dialog dialog;
    private final static String API_KEY = "";
    public FragmentCategory(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_category, container, false);
//        ArrayList<ModelCategory> category = new ArrayList<ModelCategory>();
//        category.add(new ModelCategory("sfsg",2));
//        category.add(new ModelCategory("sfsg",2));
//        category.add(new ModelCategory("sfsg",2));category.add(new ModelCategory("sfsg",2));category.add(new ModelCategory("sfsg",2));category.add(new ModelCategory("sfsg",2));category.add(new ModelCategory("sfsg",2));category.add(new ModelCategory("sfsg",2));category.add(new ModelCategory("sfsg",2));


        dialog = new Dialog(rootView.getContext());
        dialog.setContentView(R.layout.custom_dailog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        listView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
//        listView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager   mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(mLayoutManager);

        CategoryInterface apiService =
                CategoryClient.getClient().create(CategoryInterface.class);

        Call<CategoryResponse> call = apiService.getCategoriesList(API_KEY);
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse>call, Response<CategoryResponse> response) {
                dialog.hide();
                List<ModelCategory> category = response.body().getResults();
                AdapterCategory adapter = new AdapterCategory(getActivity(), category);
                int resId = R.anim.layout_animation_fall_down;
                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(rootView.getContext(), resId);
                listView.setLayoutAnimation(animation);
                listView.setAdapter(adapter);
                Log.d(TAG, "Number of movies received: " + category.size());
            }

            @Override
            public void onFailure(Call<CategoryResponse>call, Throwable t) {
                // Log error here since request failed
                dialog.hide();
                Log.e(TAG, t.toString());
            }
        });




        return rootView;
    }
}
