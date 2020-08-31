package sakr.mahmoodsakr.mediacollection;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import  android.view.View.OnClickListener;
public class MainActivity extends AppCompatActivity {

    Context context = this;
    MediaPlayer mp;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.playsound);
        Button bt2 = (Button) findViewById(R.id.stopsound);
        Button bt3 = (Button) findViewById(R.id.pause);
        Button bt4 = (Button) findViewById(R.id.playvideo);
        videoView = (VideoView) findViewById(R.id.videoview);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp == null) {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.come_on_in);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                            mp = null;
                        }
                    });
                }
                mp.start();  // resume the paused sound
            }
        });
        // Stop sound
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.stop();
                    mp.release();
                    mp = null;
                }
            }
        });
        //pause sound
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.pause();
                }
            }
        });

        OnClickListener x = new  OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        bt4.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Playing video", Toast.LENGTH_SHORT).show();

                String path = "android.resource://" + getPackageName() + "/" + R.raw.vodafone;
                Uri uripath = Uri.parse(path);
                videoView.setVideoURI(uripath);
                MediaController controler = new MediaController(context);
                videoView.setMediaController(controler);
                controler.setAnchorView(videoView);  // controller button
                // you can use the VideoView class to use its embeded methods like play,pause,stop
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp != null) {
            mp.stop();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Back Button is clicked", Toast.LENGTH_SHORT).show();

        if (mp != null) {
            mp.stop();
        }
    }
}