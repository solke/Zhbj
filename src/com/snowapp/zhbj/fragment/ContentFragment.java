package com.snowapp.zhbj.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.snowapp.zhbj.MainUI;
import com.snowapp.zhbj.R;
import com.snowapp.zhbj.base.TabController;
import com.snowapp.zhbj.base.tab.GovTabController;
import com.snowapp.zhbj.base.tab.HomeTabController;
import com.snowapp.zhbj.base.tab.NewCenterTabController;
import com.snowapp.zhbj.base.tab.SettingTabController;
import com.snowapp.zhbj.base.tab.SmartServiceTabController;

/**
 * @项目名 Zhbj
 * @包名 com.snowapp.zhbj.fragment
 * @类名 ContentFragment
 * @创建者 俊聪
 * @创建时间 2016-2-17
 * 
 * @描述 主页的内容
 */
public class ContentFragment extends BaseFragment implements OnCheckedChangeListener
{
	@ViewInject(R.id.content_viewpager)
	private ViewPager			mPager;		// ViewPager

	@ViewInject(R.id.content_rg)
	private RadioGroup			mRadioGroup;	// 底部的RadioGroup

	private List<TabController>	mPagerDatas;

	@Override
	protected View initView()
	{
		// TextView tv = new TextView(mActivity);
		//
		// tv.setText("主页面");
		// tv.setTextSize(24);
		// tv.setGravity(Gravity.CENTER);
		//
		// return tv;

		View view = View.inflate(mActivity, R.layout.content, null);

		// mPager = (ViewPager) view.findViewById(R.id.content_viewpager);
		// 注入ViewUtils工具
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	protected void initData()
	{
		// 数据初始化
		mPagerDatas = new ArrayList<TabController>();
		mPagerDatas.add(new HomeTabController(mActivity));// 首页
		mPagerDatas.add(new NewCenterTabController(mActivity));// 新闻
		mPagerDatas.add(new SmartServiceTabController(mActivity));// 智慧服务
		mPagerDatas.add(new GovTabController(mActivity));// 政务
		mPagerDatas.add(new SettingTabController(mActivity));// 设置

		// 给viewpager 加载数据
		mPager.setAdapter(new ContentPagerAdapter());// adapter---> list<数据类型>

		mRadioGroup.setOnCheckedChangeListener(this);
		
		//设置默认选中RadioButton
		mRadioGroup.check(R.id.content_rb_home);
		
	}

	class ContentPagerAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mPagerDatas != null) { return mPagerDatas.size(); }
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
			// 获得视图
			TabController controller = mPagerDatas.get(position);
			View rootView = controller.getRootView();
			container.addView(rootView);

			// 设置数据
			controller.initData();

			return rootView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}

	}

	// 1.radiogroup本身
	// 2.第一个被选中的RadioButton Id
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		int currentTab = -1;
		switch (checkedId)
		{
			case R.id.content_rb_home:
				currentTab = 0;
				//设置菜单不可以滑动
				setSlidingMenuTouchEnable(false);
				break;
			case R.id.content_rb_news:
				currentTab = 1;
				//设置菜单可以滑动
				setSlidingMenuTouchEnable(true);
				break;
			case R.id.content_rb_smartservice:
				currentTab = 2;
				//设置菜单可以滑动
				setSlidingMenuTouchEnable(true);
				break;
			case R.id.content_rb_gov:
				currentTab = 3;
				//设置菜单可以滑动
				setSlidingMenuTouchEnable(true);
				break;
			case R.id.content_rb_setting:
				currentTab = 4;
				//设置菜单不可以滑动
				setSlidingMenuTouchEnable(false);
				break;

			default:
				break;
		}
		//设置ViewPager选中的页面
		mPager.setCurrentItem(currentTab);
	}
	
	/**
	 * 设置滑动菜单是否可以滑动
	 * @param enable
	 */
	private void setSlidingMenuTouchEnable(boolean enable){
		MainUI ui = (MainUI) mActivity;
		SlidingMenu menu = ui.getSlidingMenu();
		menu.setTouchModeAbove(enable ?SlidingMenu.TOUCHMODE_FULLSCREEN:SlidingMenu.TOUCHMODE_NONE);
	}
}
