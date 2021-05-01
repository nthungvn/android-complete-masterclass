package vn.vl.nthung.androidcm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileDescriptor;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST = 1;

    private Button selectImageBtn;
    private Button previewBtn;
    private Button saveBtn;

    private ImageView memeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectImageBtn = findViewById(R.id.select_image_button);
        previewBtn = findViewById(R.id.preview_button);
        saveBtn = findViewById(R.id.save_button);
        memeImage = findViewById(R.id.meme_image);

        selectImageBtn.setOnClickListener(onSelectImageClickedHandler);
        previewBtn.setOnClickListener(onPreviewClickedHandler);
        saveBtn.setOnClickListener(onSaveClickedHandler);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            Bitmap image = null;
            try {
                image = getImageFromUri(imageUri);
                memeImage.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getImageFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    private View.OnClickListener onSelectImageClickedHandler = (view) -> {
        Intent imagePicker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        imagePicker.addCategory(Intent.CATEGORY_OPENABLE);
        imagePicker.setType("image/jpeg");
        startActivityForResult(imagePicker, PICK_FILE_REQUEST);
    };

    private View.OnClickListener onPreviewClickedHandler = (view) -> {
    };

    private View.OnClickListener onSaveClickedHandler = (view) -> {
    };
}