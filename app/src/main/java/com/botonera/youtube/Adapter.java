package com.botonera.youtube;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diego on 13/05/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private static final int NORMAL = 1;
    private static final int FOOTER = 2;

    // Your developer key goes here
    private static final String KEY = "AIzaSQZZQWQQWMGziK9H_qRxz8g-V6eDL3QW_Us";

    private Context context;
    private ArrayList<String> videos = new ArrayList<>();
    private int playing = -1;

    public Adapter(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == videos.size()) {
            return FOOTER;
        } else {
            return NORMAL;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            default:
            case NORMAL:
                v = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
                break;
            case FOOTER:
                v = LayoutInflater.from(context).inflate(R.layout.item_add, parent, false);
                break;
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == videos.size()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText editText = new EditText(context);
                    new AlertDialog.Builder(context)
                            .setTitle("Enter youtube url")
                            .setView(editText)
                            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String videoId = editText.getEditableText().toString();
                                    videoId = videoId.substring(videoId.lastIndexOf('/') + 1);
                                    videos.add(videoId);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .create()
                            .show();
                }
            });
        } else {
            holder.thumb_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playing = position;
                    notifyDataSetChanged();
                }
            });
            if (playing == position) {
                holder.player_view.bringToFront();
                holder.player_view.initialize(KEY, new PlayerListener(context, videos.get(position)));
            } else {
                holder.thumb_view.bringToFront();
                holder.thumb_view.initialize(KEY, new ThumbnailViewListener(context, videos.get(position)));
            }
        }
    }

    @Override
    public int getItemCount() {
        return videos.size() + 1; // Add footer
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.thumb_view)
        YouTubeThumbnailView thumb_view;

        @Nullable
        @BindView(R.id.player_view)
        YouTubePlayerView player_view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}
