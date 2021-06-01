package com.example.pemutarmusik;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import co.mobiwise.library.InteractivePlayerView;
import co.mobiwise.library.OnActionClickedListener;

public class MainActivity extends Activity implements OnActionClickedListener, AdapterView.OnItemClickListener {

    private InteractivePlayerView ipv;
    private Button control;
    private MediaPlayer mediaPlayer;

    private ListView listView;
    private  String[] number;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.Where_Are_You_Now);

        number = getResources().getStringArray(R.array.listmusic);
        listView = (ListView) findViewById(R.id.listMusic);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.simple_list_item_1, number);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    // call music
        uri = Uri.parse("android.resource://com.example.pemutarmusik/raw/Where_Are_You_Now");
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(MainActivity.this, uri);

        String durastionString = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        int milli = Integer.parseInt(durastionString);
        milli = milli/1000;

        ipv = (InteractivePlayerView) findViewById(R.id.ipv);
        ipv.setMax(milli);
        ipv.setProgress(0);
        ipv.setOnActionClickedListener(this);

        control = (Button) findViewById(R.id.btnPlay);
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ipv.isPlaying()){
                    ipv.start();
                    control.setText("PAUSE");

                    mediaPlayer.start();
                }
                else{
                    ipv.stop();
                    control.setText("PLAY");

                    mediaPlayer.pause();
                }
            }
        });
    }

    @Override
    public void onActionClicked(int id) {
        switch (id){
            case 1:
                Toast.makeText(MainActivity.this, "Pilihan Shuffled", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "Pilihan Love", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "Pilihan Repeat", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView =(TextView) view;
        String parameterNilai = String.valueOf(textView.getText());

        if (parameterNilai.equals("Lagu A")) {
            cekMusikPlay();

            uri = Uri.parse("android.resource://com.example.pemutarmusik/raw/Where_Are_You_Now");
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.Where_Are_You_Now);

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(MainActivity.this, uri);

            String durastionString = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            int milli = Integer.parseInt(durastionString);
            milli = milli / 1000;
            ipv.setMax(milli);
            ipv.setProgress(0);

            cekMusikEnd();

        } else if (parameterNilai.equals("Lagu B")) {
            cekMusikPlay();

            uri = Uri.parse("android.resource://com.example.pemutarmusik/raw/TULUS_Gajah");
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.TULUS_Gajah);

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(MainActivity.this, uri);

            String durastionString = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            int milli = Integer.parseInt(durastionString);
            milli = milli / 1000;
            ipv.setMax(milli);
            ipv.setProgress(0);

            cekMusikEnd();

        } else if (parameterNilai.equals("Lagu C")) {
            cekMusikPlay();

            uri = Uri.parse("android.resource://com.example.pemutarmusik/raw/TULUS_Monokrom");
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.TULUS_Monokrom);

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(MainActivity.this, uri);

            String durastionString = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            int milli = Integer.parseInt(durastionString);
            milli = milli / 1000;
            ipv.setMax(milli);
            ipv.setProgress(0);

            cekMusikEnd();

        } else if (parameterNilai.equals("Lagu D")) {
            cekMusikPlay();

            uri = Uri.parse("android.resource://com.example.pemutarmusik/raw/TULUS_Pamit");
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.TULUS_Pamit);

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(MainActivity.this, uri);

            String durastionString = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            int milli = Integer.parseInt(durastionString);
            milli = milli / 1000;
            ipv.setMax(milli);
            ipv.setProgress(0);

            cekMusikEnd();

        } else  {
            cekMusikPlay();

            uri = Uri.parse("android.resource://com.example.pemutarmusik/raw/Where_Are_You_Now");
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.Where_Are_You_Now);

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(MainActivity.this, uri);

            String durastionString = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            int milli = Integer.parseInt(durastionString);
            milli = milli / 1000;
            ipv.setMax(milli);
            ipv.setProgress(0);

            cekMusikEnd();

        }
    }

    private void cekMusikPlay() {
        if (!ipv.isPlaying()) {
            ipv.stop();
            control.setText("PLAY");
            mediaPlayer.pause();
        }
    }

    private void cekMusikEnd() {
        ipv.start();
        control.setText("PAUSE");
        mediaPlayer.start();
        }
}
