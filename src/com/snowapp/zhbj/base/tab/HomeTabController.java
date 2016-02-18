package com.snowapp.zhbj.base.tab;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.snowapp.zhbj.base.TabController;


/**
 * @项目名    Zhbj
 * @包名 	  com.snowapp.zhbj.base.tab
 * @类名 	  HomeTabController
 * @创建者	    俊聪
 * @创建时间  2016-2-17
 *
 * @描述 主页面对应的Controller
 */
public class HomeTabController extends TabController
{

	private TextView	tv;

	public HomeTabController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View initContentView(Context context)
	{
		tv = new TextView(context);
		
		
		tv.setTextSize(24);
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(Color.RED);
		return tv;
	}
	
	@Override
	public void initData()
	{
		//设置menu按钮是否科技
		mIbMenu.setVisibility(View.GONE);
		//设置title
		mTvTitle.setText("智慧北京");
		//设置内容数据
		tv.setText("首页的内容");
	}

	

}
