package com.example.nsas_app.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nsas_app.R;
import com.example.nsas_app.activities.PdfActivity;
import com.example.nsas_app.modelclasses.DashboardModelClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<DashboardModelClass> detailsListCategory;

    public GridAdapter(Activity activity, ArrayList<DashboardModelClass> mAll) {
        this.detailsListCategory = mAll;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.detail_adapter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridAdapter.ViewHolder viewHolder, final int position) {
        setAnimation(viewHolder.itemView, position);
        if (!detailsListCategory.get(position).equals("")) {
            Picasso.get()
                    .load(R.drawable.images)
                    .placeholder(R.drawable.images)
                    .error(R.drawable.images)
                    .into(viewHolder.imageView);
        }

        viewHolder.textView.setText(detailsListCategory.get(position).getDocumentname());
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdf_url=detailsListCategory.get(position).getDocurl();
                Bundle b=new Bundle();
                b.putString("all",detailsListCategory.get(position).getDocurl());
                Intent intent=new Intent(activity, PdfActivity.class);
                intent.putExtras(b);
                activity.startActivity(intent);

               /* File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "52613.pdf");
                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(Uri.fromFile(file), "application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    activity.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }*/

                /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
                activity.startActivity(browserIntent);*/
            }
        });
    }

    private int mLastPosition = -1;

    private void setAnimation(View viewToAnimate, int position) {
        if (position > mLastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            mLastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return detailsListCategory.size();
    }


    /**
     * View holder to display each RecylerView item
     */
    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private ImageView imageSet;
        private TextView add_group;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            textView = (TextView) view.findViewById(R.id.text);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
