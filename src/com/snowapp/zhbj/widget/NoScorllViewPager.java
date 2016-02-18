package com.snowapp.zhbj.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * @项目名    Zhbj
 * @包名 	  com.snowapp.zhbj.widget
 * @类名 	  NoScorllViewPager
 * @创建者	    俊聪
 * @创建时间  2016-2-18
 *
 * @描述 不可以滑动的ViewPager
 */
public class NoScorllViewPager extends ViewPager
{

	public NoScorllViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoScorllViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		// 不拦截
		return false;
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		// 不消费
		return false;
	}
	

}
