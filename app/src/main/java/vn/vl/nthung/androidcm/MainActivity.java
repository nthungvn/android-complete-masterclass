package vn.vl.nthung.androidcm;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button playButton;
    private Button captureButton;
    private ImageView imageResult;
    private String currentPhotoPath;

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
            Uri imageUri = Uri.fromFile(new File(currentPhotoPath));
            imageResult.setImageURI(imageUri);
            Log.i(this.getLocalClassName(), currentPhotoPath);

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScanIntent.setData(imageUri);
            sendBroadcast(mediaScanIntent);
        }
    }

    private File createImageFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timestamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private View.OnClickListener onCaptureClickHandler = view -> {
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (camIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
                Uri photoUri = FileProvider.getUriForFile(this, "vn.vl.nthung.androidcm", photoFile);
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(camIntent, REQUEST_IMAGE_CAPTURE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener onPlayClickHandler = view -> {
        MediaPlayer player = MediaPlayer.create(this, R.raw.tone_sms);
        player.start();

        Toast.makeText(this, "Audio is playing", Toast.LENGTH_LONG).show();
    };
}