package com.snowapp.zhbj;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.snowapp.zhbj.utils.CacheUtils;

/**
 * @项目名 Zhbj
 * @包名 com.snowapp.zhbj
 * @类名 GuideUI
 * @创建者 俊聪
 * @创建时间 2016-2-16
 * 
 * @描述 TODO
 */
public class GuideUI extends Activity implements OnPageChangeListener, OnClickListener
{
	private ViewPager		mPager;			// 页面中的ViewPager

	private List<ImageView>	mPageDatas;		// 页面对应的数据

	private Button			mBtnStart;			// 开始按钮

	private LinearLayout	mContainerPoint;	// 静态点的容器

	private View			mFocusPoint;		// 动态点

	private int				mPointSpace;		// 两点间的距离

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guide);

		// 初始化View
		initView();

		// 初始化数据
		initData();

	}

	private void initView()
	{
		mPager = (ViewPager) findViewById(R.id.guide_pager);

		mBtnStart = (Button) findViewById(R.id.start_button);

		mContainerPoint = (LinearLayout) findViewById(R.id.guide_container_point);

		mFocusPoint = findViewById(R.id.guide_focus);

		mContainerPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout()
			{
				mContainerPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				mPointSpace = mContainerPoint.getChildAt(1).getLeft() - mContainerPoint.getChildAt(0).getLeft();

			}
		});
		mBtnStart.setOnClickListener(this);
	}

	private void initData()
	{
		int[] imgRes = new int[] {
				R.drawable.guide_1,
				R.drawable.guide_2,
				R.drawable.guide_3
		};
		// 初始化List数据
		mPageDatas = new ArrayList<ImageView>();

		ImageView iv;
		View point;
		for (int i = 0; i < imgRes.length; i++)
		{
			iv = new ImageView(this);
			iv.setImageResource(imgRes[i]);
			iv.setScaleType(ScaleType.FIT_XY);// 设置图片填充
			// 添加到list中
			mPageDatas.add(iv);
			point = new View(this);
			point.setBackgroundResource(R.drawable.guide_point_normal);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
			mContainerPoint.addView(point, params);
			if (i != 0)
			{
				params.leftMargin = 10;
			}
		}
		// 给ViewPager设置数据
		mPager.setAdapter(new GuideAdapter());

		// 监听ViewPager的滑动
		mPager.setOnPageChangeListener(this);
	}

	class GuideAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mPageDatas != null) { return mPageDatas.size(); }
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			// 展示ImageView
			ImageView iv = mPageDatas.get(position);
			// 将ImageView 加到ViewPager中
			container.addView(iv);
			// 返回ImageView
			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
		// positionOffset:滑动的百分比
		int leftMargin = (int) (mPointSpace * positionOffset + position * mPointSpace + 0.5f);
		RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) mFocusPoint.getLayoutParams();
		params.leftMargin = leftMargin;
		mFocusPoint.setLayoutParams(params);
	}

	@Override
	public void onPageSelected(int position)
	{
		// // 当页面选中时回调
		// if (position == mPageDatas.size() - 1)
		// {
		// // 显示Button
		// mBtnStart.setVisibility(View.VISIBLE);
		// }
		// else
		// {
		// // 隐藏Button
		// mBtnStart.setVisibility(View.GONE);
		// }

		mBtnStart.setVisibility(position == mPageDatas.size() - 1 ? View.VISIBLE : View.GONE);

	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v)
	{
		if (v == mBtnStart)
		{
			// 页面跳转
			clickStart();
		}

	}

	private void clickStart()
	{
		// 设置已经开启过
		CacheUtils.setBoolean(this, WelComeUI.KEY_FIRST_START, false);

		// 页面跳转
		Intent intent = new Intent(this, MainUI.class);
		startActivity(intent);
		finish();
	}

}
