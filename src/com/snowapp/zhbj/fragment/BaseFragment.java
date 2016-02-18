package com.snowapp.zhbj.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @项目名    Zhbj
 * @包名 	  com.snowapp.zhbj.fragment
 * @类名 	  BaseFragment
 * @创建者	    俊聪
 * @创建时间  2016-2-17
 *
 * @描述 fragment的基类
 */
public abstract class BaseFragment extends Fragment
{
	protected Activity mActivity;//宿主的Activity
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		
		return initView();
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		//加载数据
		initData();
	}
	
	/**
	 * 如果孩子要加载数据就复写此方法
	 */
	protected void initData(){
		
	}
	protected abstract View initView();
	
	
	
}
