package com.example.pemutarmusik

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //now we'll need to change the seekbar position while the song is playing
    //to do this we need to create a runnable object an a handler
    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Let's create a new pemutarmusik
        //but before let's create a raw folder inside our res directory and import our music
        val mediaplayer:MediaPlayer = MediaPlayer.create(this,R.raw.TULUS_Gajah)
        //Now let's add our seekbar functionalities
        seekbar.progress = 0
        //and now we will add the maximum value of our seekbar the duration of the music
        seekbar.max = mediaplayer.duration
        //now leet,s create our play button event
        play_btn.setOnClickListener {
            //first let,s check that the media player is not playing
            if(!mediaplayer.isPlaying) {
                mediaplayer.start()
                //and we will change the button image
                play_btn.setImageResource(R.drawable.ic_pause)
            }else { // the media player is playing and we can pause it
                mediaplayer.pause()
                play_btn.setImageResource(R.drawable.ic_play)
                //let's see it now
            }
        }
        //Now we will add the seek bar event
        // when we change our seek bar progress the song will change the position
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                //Now when change the position of seekbar the music will go to the position
                if(changed){
                    mediaplayer.seekTo(pos)
                    //let's test it
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        //now let's code it
        runnable = Runnable {
            seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable, 1000)
        //now we want that when the music finish to play the seekbar will back to 0 and the button image change
        mediaplayer.setOnCompletionListener {
            play_btn.setImageResource(R.drawable.ic_play)
            seekbar.progress = 0
            //now let's see the sinal result
        }

    }
}
