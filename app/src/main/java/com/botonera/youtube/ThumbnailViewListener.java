package com.botonera.youtube;

import android.content.Context;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

/**
 * Created by diego on 04/06/17.
 */

public class ThumbnailViewListener implements YouTubeThumbnailView.OnInitializedListener {

    Context context;
    String videoId;

    public ThumbnailViewListener(Context context, String videoId) {
        this.context = context;
        this.videoId = videoId;
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader loader) {
        loader.setVideo(videoId);
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult errorReason) {
        Toast.makeText(context, errorReason.toString(), Toast.LENGTH_LONG).show();
    }
}
