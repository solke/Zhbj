package com.snowapp.zhbj;



import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.snowapp.zhbj.fragment.ContentFragment;
import com.snowapp.zhbj.fragment.MenuFragment;

/**
 * @项目名 Zhbj
 * @包名 com.snowapp.zhbj
 * @类名 MainUI
 * @创建者 俊聪
 * @创建时间 2016-2-16
 * 
 * @描述 TODO
 */
public class MainUI extends SlidingFragmentActivity
{
	private static final String	TAG_CONTENT	= "content";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// 1.设置主页内容
		setContentView(R.layout.content_container);

		// 2.设置菜单的布局
		setBehindContentView(R.layout.menu_container);

		// 菜单的实例
		SlidingMenu mMenu = getSlidingMenu();
		// 3.设置SlidingMenu的mode
		mMenu.setMode(SlidingMenu.LEFT);

		// 4.如果有右菜单
		// mMenu.setSecondaryMenu(int Res);设置右菜单

		// 5.设置菜单的宽度
		mMenu.setBehindWidth(150);
		// mMenu.setBehindOffset(150);
		
		// 6.设置Touch Mode Above
		mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		
		//7.设置Touch Mode Behind
		mMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		//加载菜摊和主页
		initFragment();
	}

	private void initFragment()
	{
		FragmentManager manager = getSupportFragmentManager();
		
		//1.开启事务
		FragmentTransaction transaction = manager.beginTransaction();
		
		//加载切换页面
		transaction.replace(R.id.main_container_content, new ContentFragment(), TAG_CONTENT);
		
		//加载菜单页面
		transaction.replace(R.id.main_container_menu, new MenuFragment(), TAG_CONTENT);
		
		transaction.commit();
	}
}
