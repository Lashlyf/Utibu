package com.example.hospital.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hospital.Domain.SliderItems;
import com.example.hospital.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private List<SliderItems> sliderItems;
    private ViewPager2 viewPager2;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public SliderAdapter(List<SliderItems> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_item_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
            imageView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        void setImage(SliderItems sliderItems) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.override(600, 400);
            Glide.with(context)
                    .load(sliderItems.getImage())
                    .apply(requestOptions)
                    .into(imageView);
        }
    }
}






//package com.example.hospital.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.resource.bitmap.CenterCrop;
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//import com.bumptech.glide.request.RequestOptions;
//import com.example.hospital.Domain.SliderItems;
//import com.example.hospital.R;
//
//import java.util.List;
//
//public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
//    private List<SliderItems> sliderItems;
//    private ViewPager2 viewPager2;
//    private Context context;
//    private OnItemClickListener onItemClickListener;
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.onItemClickListener = listener;
//    }
//    public SliderAdapter(List<SliderItems> sliderItems,  ViewPager2 viewPager2) {
//        this.sliderItems = sliderItems;
//        this.viewPager2 = viewPager2;
//    }
//
//    @NonNull
//    @Override
//    public SliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
//                R.layout.slide_item_container,parent,false
//        ));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {
//    holder.setImage(sliderItems.get(position));
//    if(position == sliderItems.size() - 2){
//        viewPager2.post(runnable);
//    }
//
//        holder.setImage(sliderItems.get(position));
//
////        holder.itemView.setOnClickListener(v -> {
////            if (onItemClickListener != null) {
////                onItemClickListener.onItemClick(position);
////            }
////        });
//    }
//    @Override
//    public int getItemCount() {
//        return sliderItems.size();
//    }
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    public class SliderViewHolder extends RecyclerView.ViewHolder {
//        private ImageView imageView;
//        public SliderViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.imageSlide);
//            imageView.setOnClickListener(v -> {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(getAdapterPosition());
//                }
//            });
//        }
//
////        public void bindData(SliderItems sliderItem) {
////            imageView.setImageResource(sliderItem.getImage());
////            Glide.with(context)
////                    .load(sliderItem.getImage())
////                    .into(imageView);
////        }
//        void setImage(SliderItems sliderItems){
//            RequestOptions requestOptions = new RequestOptions();
//            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(60));
//            Glide.with(context)
//                    .load(sliderItems.getImage())
//                    .apply(requestOptions)
//                    .into(imageView);
//            }
//    } private Runnable runnable = new Runnable(){
//        @Override public void run(){
//        sliderItems.addAll(sliderItems);
//        notifyDataSetChanged();
//    }
//    };
//}
