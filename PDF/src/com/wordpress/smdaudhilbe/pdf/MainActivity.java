package com.wordpress.smdaudhilbe.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import com.pdfjet.A4;
import com.pdfjet.Image;
import com.pdfjet.ImageType;
import com.pdfjet.PDF;
import com.pdfjet.Page;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        FileOutputStream fos = null;

        File exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"dummy");
        
        //	if the external storage directory does not exists, we create it
        //	will create a folder named "dummy" at /sdcard0/Pictures/dummy
        if (!exportDir.exists())      
        	exportDir.mkdirs();
        
        
//        File mediaFile = new File(exportDir.getPath() + File.separator+ "IMG_Audhil" + "dummy" + ".pdf");
        File mediaFile = new File(exportDir.getPath() + File.separator+ "PDF_Audhil.pdf");
        
        try{
        	
            fos = new FileOutputStream(mediaFile);
            
            PDF pdf = new PDF(fos);
            
            //	get image from asset folder of project
            InputStream f = getAssets().open("audhil.jpg");
            
            //	PDFJet class files to make pdf of image
            Image image = new Image(pdf, f, ImageType.JPG);
            
            Page page = new Page(pdf, A4.PORTRAIT);
            
            image.setPosition(100, 100);
            image.drawOn(page);
            
            pdf.flush();
            fos.close();
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}