package com.snowapp.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * @项目名    Zhbj
 * @包名 	  com.snowapp.zhbj.utils
 * @类名 	  CacheUtils
 * @创建者	    俊聪
 * @创建时间  2016-2-15
 *
 * @描述 TODO
 */
public class CacheUtils
{
	private final static String SP_NAME = "zhbj";
	private static SharedPreferences mPreferences;//SharedPreferences的实例
	
	private static SharedPreferences getSp(Context context){
		mPreferences = context.getSharedPreferences(SP_NAME , Context.MODE_PRIVATE);
		return mPreferences;
	}
	
	/**
	 * 通过SP获得boolean 类型的数据,没有默认为false
	 * @param context
	 * 				：上下文
	 * @param key
	 * 				：存储的key
	 * @param defValue
	 * 				：默认值
	 * @return
	 */
	public static boolean getBoolean(Context context,String key,boolean defValue){
		
		SharedPreferences sp = getSp(context);
		return sp.getBoolean(key, defValue);
		
	}
	
	
	/**
	 * 设置boolean的缓存数据
	 * @param context
	 * @param key
	 * 				：缓存对应的key
	 * @param value
	 * 				：缓存对应的值
	 */
	public static void setBoolean(Context context,String key,boolean value){
		SharedPreferences sp = getSp(context);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
		
	}
}
