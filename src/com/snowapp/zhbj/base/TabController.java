package com.snowapp.zhbj.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.snowapp.zhbj.R;

/**
 * @项目名 Zhbj
 * @包名 com.snowapp.zhbj.base
 * @类名 TabController
 * @创建者 俊聪
 * @创建时间 2016-2-17
 * 
 * @描述 tab页面对应的控制器
 */
public abstract class TabController
{
	protected View			mRootView;
	protected Context		mContext;
	protected ImageButton	mIbMenu;
	protected TextView		mTvTitle;
	protected FrameLayout	mContentContainer;

	public TabController(Context context) {
		this.mContext = context;
		mRootView = initView(context);
	}

	/**
	 * 初始化视图
	 * 
	 * @return
	 */
	protected View initView(Context context)
	{
		View view = View.inflate(mContext, R.layout.base_tab, null);
		
		mIbMenu = (ImageButton) view.findViewById(R.id.tab_ib_menu);
		mTvTitle=  (TextView) view.findViewById(R.id.tab_tv_title);
		mContentContainer = (FrameLayout) view.findViewById(R.id.tab_container_content);
		
		//初始化内容的View
		mContentContainer.addView(initContentView(context));
		return view;
	}

	
	protected abstract View initContentView(Context context); 
	
	public View getRootView()
	{
		return mRootView;
	}

	/**
	 * 获取上下文
	 * 
	 * @return
	 */
	public Context getContext()
	{
		return mContext;
	}

	/**
	 * 初始化数据的方法，孩子如果有数据初始化，就复写
	 */
	public void initData()
	{

	}
}
