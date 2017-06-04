package com.botonera.youtube;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends YouTubeBaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    String[] videos = {"fhWaJi1Hsfo", "fhWaJi1Hsfo"};

    private static Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (adapter == null) {
            adapter = new Adapter(this, videos);
        } else {
            adapter.setContext(this);
        }
        recyclerView.setAdapter(adapter);
    }
}
