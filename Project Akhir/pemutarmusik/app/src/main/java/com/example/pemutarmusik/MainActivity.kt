package com.example.pemutarmusik


import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //change the seekbar position while the song is playing
    //to do this we need to create a runnable object an a handler
    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create a raw folder inside our res directory and import our music
        val mediaplayer = MediaPlayer.create(this,R.raw.tulus_gajah)
        // add seekbar functionalities
        seekbar.progress = 0
        //add the maximum value of our seekbar the duration of the music
        seekbar.max = mediaplayer.duration
        // create  play button event
        play_btn.setOnClickListener {
            // check that the media player is not playing
            if(!mediaplayer.isPlaying) {
                mediaplayer.start()
                // change the button image
                play_btn.setImageResource(R.drawable.ic_pause)
            }else { // the media player is playing and we can pause it
                mediaplayer.pause()
                play_btn.setImageResource(R.drawable.ic_play)

            }
        }
        //add the seek bar event
        // when we change our seek bar progress the song will change the position
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, pos: Int, changed: Boolean) {
                //Now when change the position of seekbar the music will go to the position
                if(changed){
                    mediaplayer.seekTo(pos)
                    
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {


            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {


            }
        })

        runnable = Runnable {
            seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable, 1000)
        // we want that when the music finish to play the seekbar will back to 0 and the button image change
        mediaplayer.setOnCompletionListener {
            play_btn.setImageResource(R.drawable.ic_play)
            seekbar.progress = 0
        }

    }
}
