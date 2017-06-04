package com.botonera.youtube;

import android.content.Context;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

/**
 * Created by diego on 04/06/17.
 */

public class PlayerListener implements YouTubePlayer.OnInitializedListener {

    Context context;
    String videoId;

    public PlayerListener(Context context, String videoId) {
        this.context = context;
        this.videoId = videoId;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.loadVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        Toast.makeText(context, errorReason.toString(), Toast.LENGTH_LONG).show();
    }

}
