package com.creativeshare.registerme.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeshare.registerme.R;
import com.creativeshare.registerme.activities_fragments.activities.home_activity.activity.Home_Activity;
import com.creativeshare.registerme.activities_fragments.activities.home_activity.fragments.fragment_home.fragment_create_email.Fragment_Create_Email;
import com.creativeshare.registerme.models.AllInFo_Model;
import com.creativeshare.registerme.tags.Tags;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Mail_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ITEM_DATA = 1;
    private final int ITEM_LOAD = 2;

    private List<AllInFo_Model.Data.EmailTypes> data;
    private Context context;
    private Home_Activity activity;
    private Fragment fragment;
    private String current_lang;
    private Fragment_Create_Email fragment_create_email;
    private int i=-1;

    public Mail_Adapter(List<AllInFo_Model.Data.EmailTypes> data, Context context, Fragment fragment) {

        this.data = data;
        this.context = context;
        activity = (Home_Activity) context;
        this.fragment = fragment;
        Paper.init(activity);
        current_lang = Paper.book().read("lang", Locale.getDefault().getLanguage());


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM_DATA) {
            View view = LayoutInflater.from(context).inflate(R.layout.mail_row, parent, false);
            return new MyHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.load_more_row, parent, false);
            return new LoadMoreHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyHolder) {
            AllInFo_Model.Data.EmailTypes data1 = data.get(position);

            final MyHolder myHolder = (MyHolder) holder;
myHolder.tv_tittle.setText(data1.getName());
            Picasso.with(context).load(Uri.parse(Tags.IMAGE_URL+data1.getLogo())).fit().into(myHolder.imageView);

myHolder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(fragment instanceof Fragment_Create_Email){
            fragment_create_email=(Fragment_Create_Email)fragment;
            fragment_create_email.setid(data.get(myHolder.getLayoutPosition()).getId());
        }
        i=position;
        notifyDataSetChanged();
    }
});
if(i==position){

 myHolder.cardView.setBackgroundResource(R.drawable.item_selected);
}
else {
    myHolder.cardView.setBackgroundResource(R.drawable.item_unselected);

}
            //Log.e("msg",advertsing.getMain_image());
        } else {
            LoadMoreHolder loadMoreHolder = (LoadMoreHolder) holder;
            loadMoreHolder.progBar.setIndeterminate(true);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
private TextView tv_tittle;
private RoundedImageView imageView;
private FrameLayout cardView;
        public MyHolder(View itemView) {
            super(itemView);
imageView=itemView.findViewById(R.id.im_mail);
tv_tittle=itemView.findViewById(R.id.tv_name);
cardView=itemView.findViewById(R.id.frame);

        }

    }

    public class LoadMoreHolder extends RecyclerView.ViewHolder {
        private ProgressBar progBar;

        public LoadMoreHolder(View itemView) {
            super(itemView);
            progBar = itemView.findViewById(R.id.progBar);
            progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public int getItemViewType(int position) {
        AllInFo_Model.Data.EmailTypes data1 = data.get(position);
        if (data1 == null) {
            return ITEM_LOAD;
        } else {
            return ITEM_DATA;

        }


    }
}
