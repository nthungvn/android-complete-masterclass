package vn.vl.nthung.androidcm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button playButton;
    private Button captureButton;
    private ImageView imageResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(onPlayClickHandler);

        captureButton = findViewById(R.id.capture_button);
        captureButton.setOnClickListener(onCaptureClickHandler);

        imageResult = findViewById(R.id.image_result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_IMAGE_CAPTURE == requestCode && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap thumbnail = (Bitmap) extras.get("data");
            imageResult.setImageBitmap(thumbnail);
        }
    }

    private View.OnClickListener onCaptureClickHandler = view -> {
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camIntent, REQUEST_IMAGE_CAPTURE);
    };

    private View.OnClickListener onPlayClickHandler = view -> {
        MediaPlayer player = MediaPlayer.create(this, R.raw.tone_sms);
        player.start();

        Toast.makeText(this, "Audio is playing", Toast.LENGTH_LONG).show();
    };
}