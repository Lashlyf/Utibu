package com.example.hospital;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter  extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
        private List<Article> articles;
        private OnItemClickListener listener;

        public ArticleAdapter(List<Article> articles) {
            this.articles = articles;
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rv_layout, parent, false);
            return new ArticleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
            Article article = articles.get(position);
            holder.bind(article);
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }

        public interface OnItemClickListener {
            void onItemClick(Article article);
        }

        public class ArticleViewHolder extends RecyclerView.ViewHolder {
            //mageView iv_child_image;
            public ArticleViewHolder(@NonNull View itemView) {
                super(itemView);
                //iv_child_image=itemView.findViewById(R.id.iv_child_item);
            }

            public void bind(Article article) {
                // Bind the article data to the views in the item_article layout
                // e.g., TextView textView = itemView.findViewById(R.id.textView);
                // textView.setText(article.getTitle());
                // ImageView imageView = itemView.findViewById(R.id.imageView);
                // Picasso.get().load(article.getImageUrl()).into(imageView);

                itemView.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onItemClick(article);
                    }
                });
            }
        }
    }
}
