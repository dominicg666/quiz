package ui.demo.com.quizui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import link.fls.swipestack.SwipeStack;

/**
 * Created by Design-4 on 2/28/2018.
 */

public class SwipeStackAdapter extends BaseAdapter  implements SwipeStack.SwipeStackListener{

    private List<Quest> mData;
    private Activity mAct;
    SwipeStack swipeStack;
    public ArrayList<Answer> mAnswer;
    int indexData=0;
    int mActiveQust=0;
    Animation startAnimation;
    CountDownTimer mTimer;
    List<Button> btList;
    Integer[] colors = new Integer[] { R.color.colorWhite,R.color.colorRanom1,R.color.colorRanom2,R.color.colorRanom3 };
    ViewHolder viewHolder;
    Quest swipedElement;

    public SwipeStackAdapter(Activity act,List<Quest> data,SwipeStack swipe,List<Button> btlist) {
        this.mData = data;
        this.mAct=act;
        swipeStack=swipe;
        swipeStack.setListener(this);
        btList=btlist;
        startAnimation = AnimationUtils.loadAnimation(mAct, R.anim.blinking_animation);
        mTimer=  new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                // textTimer.setText(String.valueOf(millisUntilFinished / 1000));
                btList.get(0).setText(String.valueOf(millisUntilFinished / 1000));
                if((millisUntilFinished / 1000)==10){
                    btList.get(1).startAnimation(startAnimation);
                }
            }

            public void onFinish() {
                //mTextField.setText("done!");
                btList.get(0).setText(String.valueOf(0));
                swipeStack.swipeTopViewToRight();
                btList.get(1).clearAnimation();

            }
        };
        getAnswer(0);

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {

        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       viewHolder= new ViewHolder();
        Quest quest=mData.get(position);
        convertView = LayoutInflater.from(mAct).inflate(R.layout.question_list, parent, false);
        viewHolder.cardView=(CardView) convertView.findViewById(R.id.cardview);
        viewHolder.linearLayout=(LinearLayout) convertView.findViewById(R.id.linearLayout);

        if(colors.length==indexData){
            indexData=0;
        }


        Integer randomColor = colors[indexData];
        indexData++;
        viewHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(mAct, randomColor));
        viewHolder.linearLayout.setBackgroundResource(R.drawable.score_bottom);
        GradientDrawable drawable = (GradientDrawable) viewHolder.linearLayout.getBackground();
        drawable.setColor(ContextCompat.getColor(mAct, randomColor));
        viewHolder.textViewCard = (TextView) convertView.findViewById(R.id.textView);
        viewHolder.score_text = (TextView) convertView.findViewById(R.id.score_text);
        viewHolder.question_no = (TextView)convertView.findViewById(R.id.question_no);
        viewHolder.textViewCard.setText(Html.fromHtml(quest.getQuestion()));
        viewHolder.question_no.setText(String.valueOf(position+1));
        viewHolder.score_text.setText(quest.getScore());

        if(indexData==1){
            //score_text.setTextColor(ContextCompat.getColor(mAct,R.color.colorGold));
            //textViewCard.setTextColor(ContextCompat.getColor(mAct,R.color.colorBlack));
        }else{
            //score_text.setTextColor(ContextCompat.getColor(mAct,R.color.colorWhite));
           // textViewCard.setTextColor(ContextCompat.getColor(mAct,R.color.colorWhite));
        }

        return convertView;
    }
    public class ViewHolder{
        CardView cardView;
        LinearLayout linearLayout;
        TextView textViewCard;
        TextView score_text;
        TextView question_no;
    }
    public static String[] Randomize(String[] arr) {
        String[] randomizedArray = new String[arr.length];
        System.arraycopy(arr, 0, randomizedArray, 0, arr.length);
        Random rgen = new Random();

        for (int i = 0; i < randomizedArray.length; i++) {
            int randPos = rgen.nextInt(randomizedArray.length);
            String tmp = randomizedArray[i];
            randomizedArray[i] = randomizedArray[randPos];
            randomizedArray[randPos] = tmp;
        }

        return randomizedArray;
    }


    public void getAnswer( int position){
        int questno = (mData.size());
        if(position!=questno) {
            Quest swipedElement = mData.get(position);
            mAnswer = new ArrayList<Answer>();
            String[] answer={swipedElement.getAns_one(),swipedElement.getAns_two(),swipedElement.getAns_three(),swipedElement.getAns_four()};
            answer= Randomize(answer);
            for (int i=0;i<answer.length;i++){
                mAnswer.add(new Answer(answer[i],""));
            }
            mTimer.cancel();
            TimerQuest();
            this.swipedElement=swipedElement;
        }else{
            mAnswer = new ArrayList<Answer>();

        }
        ObjectAnimator flip2 = ObjectAnimator.ofFloat(btList.get(0), "rotationY", 0f, 360f);
        flip2.setDuration(1000);
        flip2.start();

        AnswerAdapter adapter = new AnswerAdapter(mAct, mAnswer,swipeStack,swipedElement,btList);
        RecyclerView listView = (RecyclerView) mAct.findViewById(R.id.list_view);
        listView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mAct, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(adapter);


    }
    public void TimerQuest(){


        mTimer.start();
    }
    @Override
    public void onViewSwipedToRight(int position) {
        getAnswer(position+1);

    }
    @Override
    public void onViewSwipedToLeft(int position) {
//        String swipedElement = mAdapter.getItem(position);
//        Toast.makeText(this, getString(R.string.view_swiped_left, swipedElement),
//                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStackEmpty() {

        Toast.makeText(mAct, "empty", Toast.LENGTH_SHORT).show();
        for (int i=0;i<btList.size();i++){
            btList.get(i).clearAnimation();
        }
        mTimer.cancel();
    }
}
