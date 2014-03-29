package jant.gunreloads.app.utility;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import jant.gunreloads.app.R;

/**
 * Created by Adam on 3/29/2014.
 */
public class QRUtility {
    public static void generateQrCode(Activity a, String qrText) {
        MultiFormatWriter writer = new MultiFormatWriter();
        String finalData = Uri.encode(qrText, "utf-8");

        int qrWidth = 150;
        int qrHeight = 150;
        BitMatrix bm = null;
        try {
            bm = writer.encode(finalData, BarcodeFormat.QR_CODE, qrWidth, qrHeight);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        Bitmap ImageBitmap = Bitmap.createBitmap(qrWidth, qrHeight, Bitmap.Config.ARGB_8888);

        for (int i = 0; i < qrWidth; i++) {//width
            for (int j = 0; j < qrHeight; j++) {//height
                ImageBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK: Color.WHITE);
            }
        }

        final ImageView imageView = (ImageView) a.findViewById(R.id.imageView);
        if (ImageBitmap != null) {
            imageView.setImageBitmap(ImageBitmap);
        } else {
            Toast.makeText(a.getApplicationContext(), a.getResources().getString(R.string.userInputError),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
