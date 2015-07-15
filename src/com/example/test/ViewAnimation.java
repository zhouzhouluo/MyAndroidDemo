package com.example.test;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class ViewAnimation extends Animation{
	public ViewAnimation(){}
	public int centerX;
	public int centerY;
	public Camera camera = new Camera();
	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		// TODO Auto-generated method stub
		super.initialize(width, height, parentWidth, parentHeight);
		centerX= width/2;
		centerY=height/2;
		setDuration(2500);
		setFillAfter(true);
		setInterpolator(new LinearInterpolator());
		System.out.println("111111111111来了吗？点哦idufoadsu 哦idasugioadsuy");
	}
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		// TODO Auto-generated method stub
		final Matrix matrix = t.getMatrix();
//		matrix.setScale(interpolatedTime, interpolatedTime);
//		matrix.preTranslate(-centerX, -centerY);
//		matrix.postTranslate(centerX, centerY);
//		System.out.println("22222222222222来了吗？点哦idufoadsu 哦idasugioadsuy："+interpolatedTime);
		camera.save();
		camera.translate(0.0f, 0.0f, (1900-1900.0f*interpolatedTime));
		camera.rotateX(360*interpolatedTime);
		camera.getMatrix(matrix);
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
		camera.restore();
	}

}
