package com.example.test;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatrixActivity extends Activity{
	private Button selectBn;
	private ImageView imageShow;
	private ImageView imageCreate;
	private TextView textview1;
	private TextView textview2;
	private Bitmap bmp; //原始图片
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_matrix);
	    selectBn = (Button) findViewById(R.id.button1);
	    imageShow = (ImageView) findViewById(R.id.imageView1);
	    imageCreate = (ImageView) findViewById(R.id.imageView2);
	    textview1 = (TextView) findViewById(R.id.textView1);
	    textview2 = (TextView) findViewById(R.id.textView2);
	     
	    //选择图片
	    selectBn.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Intent intent = new Intent(Intent.ACTION_PICK, 
	                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	            startActivityForResult(intent, 0 );
	        }
	    });
	    if (savedInstanceState == null) {
//	        getFragmentManager().beginTransaction()
//	                .add(R.id.container, new PlaceholderFragment())
//	                .commit();
	    }
	}
	//显示两张图片
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    super.onActivityResult(requestCode, resultCode, data);
	    if(resultCode==RESULT_OK) {
	        ShowPhotoByImageView(data);     //显示照片
	        CreatePhotoByImageView();          //创建图片
	    }
	}
	//自定义函数 显示打开的照片在ImageView1中
	public void ShowPhotoByImageView(Intent data) {
	    Uri imageFileUri = data.getData();
	    DisplayMetrics dm = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(dm);
	    int width = dm.widthPixels;    //手机屏幕水平分辨率
	    int height = dm.heightPixels;  //手机屏幕垂直分辨率
	    Log.v("height", ""+height );
	    Log.v("width", ""+width);
	    try {
	        // Load up the image's dimensions not the image itself
	        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
	        bmpFactoryOptions.inJustDecodeBounds = true;
	        bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null, bmpFactoryOptions);
	        int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/(float)height);
	        int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/(float)width);
	        Log.v("bmpheight", ""+bmpFactoryOptions.outHeight);
	        Log.v("bmpheight", ""+bmpFactoryOptions.outWidth);
	        if(heightRatio>1&&widthRatio>1) {
	            if(heightRatio>widthRatio) {
	                bmpFactoryOptions.inSampleSize = heightRatio*2;
	            }
	            else {
	                bmpFactoryOptions.inSampleSize = widthRatio*2;
	            }
	        }
	         //图像真正解码   
	        bmpFactoryOptions.inJustDecodeBounds = false;               
	        bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null, bmpFactoryOptions);  
	        imageShow.setImageBitmap(bmp); //将剪裁后照片显示出来  
	        textview1.setVisibility(View.VISIBLE);
	    } catch(FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	//创建第二张图片并显示
	public void CreatePhotoByImageView() {
	    try {
	        Bitmap createBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
	        Canvas canvas = new Canvas(createBmp); //画布 传入位图用于绘制
	        Paint paint = new Paint(); //画刷 改变颜色 对比度等属性
	        canvas.drawBitmap(bmp, 0, 0, paint);    //错误:没有图片 因为参数bmp写成createBmp
	        imageCreate.setImageBitmap(createBmp);
	        textview2.setVisibility(View.VISIBLE);
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
}
