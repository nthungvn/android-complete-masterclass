package vn.vl.nthung.androidcm;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener((view) -> {
            MediaPlayer player = MediaPlayer.create(this, R.raw.tone_sms);
            player.start();

            Toast.makeText(this, "Audio is playing", Toast.LENGTH_LONG).show();
        });
    }
}