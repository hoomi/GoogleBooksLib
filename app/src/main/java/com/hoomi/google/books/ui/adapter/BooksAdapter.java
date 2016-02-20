package com.hoomi.google.books.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoomi.google.books.R;
import com.hoomi.books.lib.model.Volume;
import com.hoomi.google.books.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {
    private static final int LOAD_MARGIN = 10;
    private final Context context;
    private List<Volume> volumes;
    private final View.OnClickListener onClickListener;
    private LoadMoreListener loadMoreListener;

    public BooksAdapter(Context context, List<Volume> volumes, View.OnClickListener onClickListener, LoadMoreListener loadMoreListener) {
        this.context = context;
        this.volumes = volumes;
        this.onClickListener = onClickListener;
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Using this instead of LayoutInflater.from(context) for testing purposes
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.book_item, parent, false);
        v.setOnClickListener(onClickListener);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Volume volume = volumes.get(position);
        holder.bookTitle.setText(volume.getTitle());
        loadImage(holder, volume);

        holder.itemView.setTag(volume);

        if (getItemCount() - position < LOAD_MARGIN) {
            loadMoreListener.loadMore(getItemCount());
        }
    }


    @Override
    public int getItemCount() {
        return volumes == null ? 0 : volumes.size();
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    public void addVolumes(List<Volume> volumes) {
        if (this.volumes == null) {
            setVolumes(volumes);
        } else {
            int index = getItemCount();
            this.volumes.addAll(volumes);
            notifyItemRangeInserted(index,volumes.size());
        }

    }

    private void loadImage(BookViewHolder holder, Volume volume) {
        Map<String, String> imageLinks = volume.getImageLinks();
        if (imageLinks != null) {
            String imageUrl = imageLinks.get(Volume.KEY_THUMBNAIL);
            if (!StringUtils.isEmpty(imageUrl)) {
                Picasso.with(context).load(imageUrl).fit().centerInside().into(holder.bookImage);
            } else {
                holder.bookImage.setImageBitmap(null);
            }
        } else {
            holder.bookImage.setImageBitmap(null);
        }
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView bookTitle;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookImage = (ImageView) itemView.findViewById(R.id.bookImageView);
            bookTitle = (TextView) itemView.findViewById(R.id.bookTextView);
        }
    }
}
