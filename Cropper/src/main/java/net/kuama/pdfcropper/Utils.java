package net.kuama.pdfcropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import me.pqpo.smartcropperlib.SmartCropper;

public class Utils {
   static PdfCropperImageView imageView;

    public Bitmap ImageEffects(Bitmap src, int colorMode) {
        Mat mat = convertBitmapToMat(src);
        switch (colorMode) {
            case 1:
                Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGBA2GRAY);
                break;
            case 2:
                Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGBA2GRAY);
                Imgproc.GaussianBlur(mat, mat, new Size(5, 5), 0);
                Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 15);
                break;
            case 3:
                Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGBA2GRAY);
                Imgproc.GaussianBlur(mat, mat, new Size(5, 5), 0);
                Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 1);
                break;
            case 4:
                Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGBA2GRAY);
                Imgproc.GaussianBlur(mat, mat, new Size(5, 5), 0);
                Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 3);
                break;
        }
        Bitmap bm = Bitmap.createBitmap(mat.width(), mat.height(), Bitmap.Config.ARGB_8888);
        org.opencv.android.Utils.matToBitmap(mat, bm);
        return bm;
    }

    public Mat convertBitmapToMat(Bitmap bitmap) {
        Mat mat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC4);
        Bitmap bmp32 = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        org.opencv.android.Utils.bitmapToMat(bmp32, mat);
        return mat;
    }

    public Bitmap convertMatToBitmap(Mat m) {
        Bitmap bm = Bitmap.createBitmap(m.cols(), m.rows(), Bitmap.Config.ARGB_8888);
        org.opencv.android.Utils.matToBitmap(m, bm);
        return bm;
    }

    public static void initializeLibs(Context context) {
        if (!OpenCVLoader.initDebug())
            Log.e("OpenCv", "Unable to load OpenCV");
        else
            Log.d("OpenCv", "OpenCV loaded");

        SmartCropper.buildImageDetector(context);
    }

    public static Bitmap getCroppedBitmap() {
        Bitmap bitmap = null;
        if (imageView.canRightCrop()) {
            bitmap = imageView.crop();
        }
        return bitmap;
    }

    public static void setImage(Bitmap bitmap) {
        imageView.setImageToCrop(bitmap);
    }

    public static void setCropView(RelativeLayout parent, Context context) {
        imageView = new PdfCropperImageView(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(lp);
        parent.removeAllViews();
        parent.addView(imageView);
    }
}
