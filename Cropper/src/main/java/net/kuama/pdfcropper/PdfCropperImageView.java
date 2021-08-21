package net.kuama.pdfcropper;

import android.content.Context;
import android.util.AttributeSet;

public class PdfCropperImageView extends me.pqpo.smartcropperlib.view.CropImageView {
    public PdfCropperImageView(Context context) {
        super(context);
        setShowGuideLine(false);
    }

    public PdfCropperImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PdfCropperImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
