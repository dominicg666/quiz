package ui.demo.com.quizui.Api;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;
import ui.demo.com.quizui.Quest;
import ui.demo.com.quizui.R;
import ui.demo.com.quizui.SwipeStackAdapter;

/**
 * Created by Design-4 on 3/6/2018.
 */

public class AsyncQuest extends AsyncTask<String, Void, JSONObject> {
    public Activity mActivity;
    public List<Button> btList;
    public List<Quest> mData;
    public SwipeStack swipeStack;
    Dialog mDailog;

    public AsyncQuest(Activity activity,List<Button> bt, Dialog dailog){
        mActivity=activity;
        btList=bt;
        mDailog=dailog;
        mDailog.show();
        swipeStack = (SwipeStack) mActivity.findViewById(R.id.swipeStack);
    }
    @Override
    protected JSONObject doInBackground(String... strings) {
       JSONObject jsonData=null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            jsonData = new JSONObject(builder.toString());

            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return jsonData;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        //textView.setText("Current Weather: " + temp);
        mData=new ArrayList<Quest>();
        try {
            Toast.makeText(mActivity,json.getString("response_code"),Toast.LENGTH_LONG);
            if(json.getString("response_code").equals("0")){
                JSONArray data=json.getJSONArray("results");
                for (int i=0;i<data.length();i++){
                    JSONObject item=data.getJSONObject(i);
                    String score="0";
                    if(item.getString("difficulty").equals("medium")){
                        score="2";
                    }else if(item.getString("difficulty").equals("easy")){
                        score="1";
                    }else {
                        score="3";
                    }
                    JSONArray itemOption=item.getJSONArray("incorrect_answers");
                    mData.add(new Quest(item.getString("question"),item.getString("correct_answer"),itemOption.getString(0),itemOption.getString(1),itemOption.getString(2),score,item.getString("correct_answer")));

                }


                swipeStack.setAdapter(new SwipeStackAdapter(mActivity,mData,swipeStack,btList));
                mDailog.hide();
            }else{
                Toast.makeText(mActivity,"success",Toast.LENGTH_LONG);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
