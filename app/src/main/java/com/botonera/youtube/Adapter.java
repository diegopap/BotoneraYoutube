package com.botonera.youtube;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diego on 13/05/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    // Your developer key goes here
    private static final String KEY = "AIzaSQZZQWQQWMGziK9H_qRxz8g-V6eDL3QW_Us";

    private Context context;
    private String[] videos;
    private int playing = -1;

    public Adapter(Context context, String[] videos) {
        this.context = context;
        this.videos = videos;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.thumb_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playing = position;
                notifyDataSetChanged();
            }
        });
        if (playing == position) {
            holder.player_view.bringToFront();
            holder.player_view.initialize(KEY, new PlayerListener(context, videos[position]));
        } else {
            holder.thumb_view.bringToFront();
            holder.thumb_view.initialize(KEY, new ThumbnailViewListener(context, videos[position]));
        }
    }

    @Override
    public int getItemCount() {
        return videos.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumb_view)
        YouTubeThumbnailView thumb_view;

        @BindView(R.id.player_view)
        YouTubePlayerView player_view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}
