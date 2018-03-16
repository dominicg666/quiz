package ui.demo.com.quizui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ui.demo.com.quizui.MainActivity;
import ui.demo.com.quizui.R;

/**
 * Created by Design-4 on 3/3/2018.
 */

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder>  {
    private List<ModelCategory> mModelList;
    private Context mContext;
    int indexData=0;
    Integer[] colors = new Integer[] { R.color.colorRanomDark1,R.color.colorRanomDark2,R.color.colorRanomDark3,R.color.colorRandomDark4,R.color.colorRandomDark5 };

    public AdapterCategory(Context context, List<ModelCategory> list) {
        mModelList = list;
        mContext = context;
    }
    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.category_list_item, parent, false);

        // Return a new holder instance
        AdapterCategory.ViewHolder viewHolder = new AdapterCategory.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterCategory.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ModelCategory list = mModelList.get(position);

        // Set item views based on your views and data model
        if(colors.length==indexData){
            indexData=0;
        }
        Integer randomColor = colors[indexData];
        indexData++;
        viewHolder.mTitle.setText(list.getCategory());
        viewHolder.mLayout.setBackgroundColor(ContextCompat.getColor(mContext, randomColor));
        viewHolder.mImage.setColorFilter(ContextCompat.getColor(mContext, randomColor));
        viewHolder.mTitle.setTextColor(ContextCompat.getColor(mContext, randomColor));
        final String category=list.getCategory();
        final String code=list.getId();
        viewHolder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MainActivity.class);
                intent.putExtra("category",category);
                intent.putExtra("code",code);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private FrameLayout mLayout;
        private ImageView mImage;
        private CardView mCard;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.mTitle);
            mLayout=(FrameLayout) itemView.findViewById(R.id.frame_left);
            mImage=(ImageView) itemView.findViewById(R.id.imageView3);
            mCard=(CardView) itemView.findViewById(R.id.category_item);
        }
    }
}
