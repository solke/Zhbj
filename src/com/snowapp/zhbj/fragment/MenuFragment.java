package com.snowapp.zhbj.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


/**
 * @项目名    Zhbj
 * @包名 	  com.snowapp.zhbj.fragment
 * @类名 	  MenuFragment
 * @创建者	    俊聪
 * @创建时间  2016-2-17
 *
 * @描述 TODO
 */
public class MenuFragment extends BaseFragment
{

	@Override
	protected View initView()
	{
TextView tv = new TextView(mActivity);
		
		tv.setText("菜单页面");
		tv.setTextSize(24);
		tv.setGravity(Gravity.CENTER);
		
		return tv;
	}

}
