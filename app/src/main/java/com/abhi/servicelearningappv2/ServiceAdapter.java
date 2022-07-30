package com.abhi.servicelearningappv2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private Activity mActivity;
    private ArrayList<ServiceModel> mContentList;
    private OnImageClickListener onImageClickListener;


    public ServiceAdapter(Context mContext, Activity mActivity, ArrayList<ServiceModel> mContentList,OnImageClickListener onImageClickListener) {
        Toast.makeText(mContext, "Inside ServiceAdapter", Toast.LENGTH_SHORT).show();
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mContentList = mContentList;
        this.onImageClickListener = onImageClickListener;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflator, parent, false);
        return new ViewHolder(view, viewType);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder, int position) {
        ViewHolder holder = (ViewHolder) mainHolder;


        final ServiceModel model = mContentList.get(position);
        // setting data over views
        String imgUrl = model.getServiceImage();
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .into(holder.imgPost);
        }

        holder.tvTitle.setText(model.getServiceName());

    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPost;
        private TextView tvTitle;



        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            // Find all views ids
            imgPost = (ImageView) itemView.findViewById(R.id.post_img);
            tvTitle = (TextView) itemView.findViewById(R.id.title_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onImageClickListener.onImageClick(getAdapterPosition());
                }
            });
        }
    }

    interface OnImageClickListener{
        void onImageClick(int positionOfTheImage);
    }
}


