package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.WriterException;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import android.os.Bundle;

public class QrcodeActivity extends AppCompatActivity {

    private ImageView qrCodeIV;
    private EditText dataEdt;
    private EditText dataNo;
    private  EditText dataExp;
    private EditText dataCvv;
    private Button generateQrBtn;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        // initializing all variables.
        qrCodeIV = findViewById(R.id.idIVQrcode);
        dataEdt = findViewById(R.id.nameTxt);
        dataNo = findViewById(R.id.numTxt);
        dataExp = findViewById(R.id.idTxt);
        dataCvv = findViewById(R.id.cvvTxt);
        
        
        generateQrBtn = findViewById(R.id.idBtnGenerateQR);

        // initializing onclick listener for button.
        generateQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(dataEdt.getText().toString())) {
                    Toast.makeText(QrcodeActivity.this, "Enter all fields to generate QR Code", Toast.LENGTH_SHORT).show();

                }   else if (TextUtils.isEmpty(dataNo.getText().toString())){
                    Toast.makeText(QrcodeActivity.this, "Enter all fields to generate QR Code", Toast.LENGTH_SHORT).show();

                }   else if (TextUtils.isEmpty(dataExp.getText().toString())){
                    Toast.makeText(QrcodeActivity.this, "Enter all fields to generate QR Code", Toast.LENGTH_SHORT).show();

                }   else if (TextUtils.isEmpty(dataCvv.getText().toString())){
                    Toast.makeText(QrcodeActivity.this, "Enter all fields to generate QR Code", Toast.LENGTH_SHORT).show();
                }


                else {
                    // below line is for getting
                    // the windowmanager service.
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

                    // initializing a variable for default display.
                    Display display = manager.getDefaultDisplay();

                    // creating a variable for point which
                    // is to be displayed in QR Code.
                    Point point = new Point();
                    display.getSize(point);

                    // getting width and
                    // height of a point
                    int width = point.x;
                    int height = point.y;

                    // generating dimension from width and height.
                    int dimen = width < height ? width : height;
                    dimen = dimen * 3 / 4;

                    // setting this dimensions inside our qr code
                    // encoder to generate our qr code.
                    qrgEncoder = new QRGEncoder(dataEdt.getText().toString(), null, QRGContents.Type.TEXT, dimen);
                    try {
                        // getting our qrcode in the form of bitmap.
                        bitmap = qrgEncoder.encodeAsBitmap();
                        // the bitmap is set inside our image
                        // view using .setimagebitmap method.
                        qrCodeIV.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        // this method is called for
                        // exception handling.
                        Log.e("Tag", e.toString());
                    }
                    SetValidation();
                }
            }

            private void SetValidation() {
            }
        });
    }
}