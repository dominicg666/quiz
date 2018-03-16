package ui.demo.com.quizui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

import static ui.demo.com.quizui.R.id.imageView;

/**
 * Created by Design-4 on 2/28/2018.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {
    private ArrayList<Answer> mModelList;
    private Activity mContext;
    public CardView cardView;
    SwipeStack swipeStack;
    Quest quest;
    List<Button> btList;
    Animation startAnimation;
    int Score=0;

    public AnswerAdapter(Activity context, ArrayList<Answer> list,SwipeStack swipeStack,Quest quest,List<Button> btList) {
        mModelList = list;
        mContext = context;
        this.swipeStack=swipeStack;
        this.quest=quest;
        this.btList=btList;
        startAnimation = AnimationUtils.loadAnimation(context, R.anim.blinking_animation);

    }

    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.answer_list, parent, false);

        ObjectAnimator flip = ObjectAnimator.ofFloat(contactView, "rotationX", 0f, 180f);
        flip.setDuration(1000);
        flip.start();


        // Return a new holder instance
        AnswerAdapter.ViewHolder viewHolder = new AnswerAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnswerAdapter.ViewHolder viewHolder,final int position) {
        // Get the data model based on position
        Answer list = mModelList.get(position);
        final String currectAns=list.getAnswer();
        // Set item views based on your views and data model
        viewHolder.mAnswer.setText(Html.fromHtml(list.getAnswer()));
        viewHolder.mOption.setText(Html.fromHtml(list.getOption()));
        ObjectAnimator flip = ObjectAnimator.ofFloat(viewHolder.mAnswer, "rotationX", 0f, 180f);
        flip.setDuration(1000);
        flip.start();
        ObjectAnimator flip2 = ObjectAnimator.ofFloat(viewHolder.mOption, "rotationX", 0f, 180f);
        flip2.setDuration(1000);
        flip2.start();
        cardView=viewHolder.cardViewanswer;
        viewHolder.cardViewanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, quest.getAnsCorr().toString(),
              Toast.LENGTH_SHORT).show();
                Score=Integer.valueOf(btList.get(4).getText().toString());

                if(quest.getAnsCorr().toString().equals(currectAns)){
                    btList.get(2).startAnimation(startAnimation);
                    btList.get(3).clearAnimation();
                    Score=Score+Integer.valueOf(quest.getScore());
                    view.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorSuccess));
                }else{
                    btList.get(2).clearAnimation();
                    btList.get(3).startAnimation(startAnimation);
                    view.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorDanger));
                }
                btList.get(4).setText(String.valueOf(Score));
                swipeStack.swipeTopViewToRight();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mAnswer;
        private TextView mOption;
        public CardView cardViewanswer;

        public ViewHolder(View itemView) {
            super(itemView);
            mAnswer = (TextView) itemView.findViewById(R.id.answer_text);
            mOption = (TextView) itemView.findViewById(R.id.answer_option);
            cardViewanswer=(CardView) itemView.findViewById(R.id.cardViewanswer);
        }
    }
}
