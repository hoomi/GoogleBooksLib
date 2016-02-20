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

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {
    private final Context context;
    private List<Volume> volumes;
    private final View.OnClickListener onClickListener;

    public BooksAdapter(Context context, List<Volume> volumes, View.OnClickListener onClickListener) {
        this.context = context;
        this.volumes = volumes;
        this.onClickListener = onClickListener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Using this instead of LayoutInflater.from(context) for testing purposes
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.book_item, parent, true);
        v.setOnClickListener(onClickListener);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Volume volume = volumes.get(position);
        holder.bookTitle.setText(volume.getTitle());
        String imageUrl = volume.getImageLinks().get(Volume.KEY_THUMBNAIL);
        if (!StringUtils.isEmpty(imageUrl)) {
            Picasso.with(context).load(imageUrl).fit().centerInside().into(holder.bookImage);
        } else {
            holder.bookImage.setImageBitmap(null);
        }
        holder.itemView.setTag(volume);
    }

    @Override
    public int getItemCount() {
        return volumes == null ? 0 : volumes.size();
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
        notifyDataSetChanged();
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
