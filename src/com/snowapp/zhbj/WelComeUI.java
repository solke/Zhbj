package com.snowapp.zhbj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.snowapp.zhbj.utils.CacheUtils;

/**
 * 
 * @项目名 Zhbj
 * @包名 com.snowapp.zhbj
 * @类名 MainActivity
 * @创建者 俊聪
 * @创建时间 2016-2-12
 * 
 * @描述 TODO
 */
public class WelComeUI extends Activity
{
	private final static long	ANIMATION_DURATION	= 1500;
	private static final String	TAG					= "WelComeUI";
	public static final String	KEY_FIRST_START		= "is first start";
	private View				mRootView;								// 根视图

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// //出去title
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// //去除状态栏
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.welcome);
		//初始化View
		mRootView = findViewById(R.id.welcome_root);

		// 1.旋转动画
		RotateAnimation rotateAnimation = new RotateAnimation(0,// 起始角度
																360,// 终止角度
																Animation.RELATIVE_TO_SELF,
																0.5f,
																Animation.RELATIVE_TO_SELF,
																0.5f);
		rotateAnimation.setDuration(ANIMATION_DURATION);
		rotateAnimation.setFillAfter(true);

		// 2.缩放动画
		ScaleAnimation scaleAnimation = new ScaleAnimation(0f,
		                                                   1f, 
		                                                   0f, 
		                                                   1f, 
		                                                   Animation.RELATIVE_TO_SELF,
															0.5f,
															Animation.RELATIVE_TO_SELF,
															0.5f);
		rotateAnimation.setDuration(ANIMATION_DURATION);
		rotateAnimation.setFillAfter(true);
		
		//3.透明度的动画
		AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
		rotateAnimation.setDuration(ANIMATION_DURATION);
		rotateAnimation.setFillAfter(true);
		
		
		//动画集合
		AnimationSet set = new AnimationSet(false);
		set.addAnimation(rotateAnimation);
		set.addAnimation(scaleAnimation);
		set.addAnimation(alphaAnimation);
		
		mRootView.startAnimation(set);
		
		//监听动画执行
		set.setAnimationListener(new WelcomeAnimationListener());
	}

	private void doNavgation()
	{
		// 页面跳转
		// 根据情况进行页面跳转
		// 如果第一次打开应用程序，那么久进入引导页面，否则就进入主页
		boolean isFirstStart = CacheUtils.getBoolean(this, KEY_FIRST_START, true);
		if (isFirstStart)
		{
			Log.d(TAG, "进入引导界面");
			Intent intent = new Intent(this,GuideUI.class);
			startActivity(intent);
		}
		else
		{
			Log.d(TAG, "进入主界面");
			Intent intent = new Intent(this,MainUI.class);
			startActivity(intent);
		}
		finish();
	}

	class WelcomeAnimationListener implements AnimationListener
	{

		protected static final long	ANIMATION_DELAY	= 500;

		@Override
		public void onAnimationStart(Animation animation)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation)
		{
			// 动画结束后，等待一会儿再进行页面跳转
			new Thread(new Runnable() {

				@Override
				public void run()
				{
					// 等待
					try
					{
						Thread.sleep(ANIMATION_DELAY);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}).start();

			// 页面跳转
			doNavgation();
		}

		@Override
		public void onAnimationRepeat(Animation animation)
		{
			// TODO Auto-generated method stub

		}

	}
}
