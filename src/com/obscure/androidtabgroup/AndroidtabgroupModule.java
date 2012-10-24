/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package com.obscure.androidtabgroup;

import java.util.HashMap;
import java.util.Map;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.proxy.TiBaseWindowProxy;
import org.appcelerator.titanium.proxy.TiViewProxy;

import ti.modules.titanium.ui.ActivityWindowProxy;
import ti.modules.titanium.ui.TabGroupProxy;
import ti.modules.titanium.ui.TabProxy;
import android.app.Activity;
import android.util.Log;

@Kroll.module(name = "Androidtabgroup", id = "com.obscure.androidtabgroup")
public class AndroidtabgroupModule extends KrollModule {
	private static final String[] UIMODULE_PROXY_FORMATS = {
			"ti.modules.titanium.ui.%sProxy",
			"org.appcelerator.titanium.proxy.Ti%sProxy" };

	private static final String LCAT = "AndroidtabgroupModule";

	private static final boolean DBG = TiConfig.LOGD;

	public AndroidtabgroupModule() {
		super();
	}
	
	private TiViewProxy createProxy(String key, Map<String, Object> params) {
		if (key == null || key.length() < 1) {
			Log.e(LCAT, "key error: " + key);
		}

		TiViewProxy result = null;

		if ("Window".equals(key)) {
			// TODO determine if this is a lightweight or heavyweight window
			result = new ActivityWindowProxy(); // heavyweight?
		} else if ("BaseWindow".equals(key)) {
			result = new TiBaseWindowProxy(); // lightweight
			// maybe add LW window to root activity
			((TiBaseWindowProxy) result).addSelfToStack();
		} else {
			// dynamically locate the class
			for (String fmt : UIMODULE_PROXY_FORMATS) {
				try {
					Class<?> clazz = Class.forName(String.format(fmt, key));
					result = (TiViewProxy) clazz.newInstance();
					break;
				} catch (ClassNotFoundException e) {
					// ignore, might just be looking in the wrong package
				} catch (IllegalAccessException e) {
					Log.e(LCAT, e.getMessage());
				} catch (InstantiationException e) {
					Log.e(LCAT, e.getMessage());
				}
			}
		}

		if (result == null) {
			Log.w(LCAT, String.format("Could not load UI element '%s'", key));
			return null;
		}

		// TODO is this required for all proxies?
		result.setActivity(this.getActivity());

		if (params != null) {
			result.handleCreationDict(new KrollDict(params));
		}

		return result;
	}

	private TiViewProxy createTabWithLabelText(String name, String color) {
		Map<String,Object> labelParams = new HashMap<String,Object>();
		labelParams.put("text", "I am tab "+name);
		labelParams.put("height", 24);
		labelParams.put("width", 200);
		TiViewProxy label = createProxy("Label", labelParams);
		
		Map<String,Object> windowParams = new HashMap<String,Object>();
		windowParams.put("layout", "vertical");
		windowParams.put("backgroundColor", color);
		TiViewProxy window = createProxy("BaseWindow", windowParams);
		
		window.add(label);
		
		Map<String,Object> tabParams = new HashMap<String,Object>();
		tabParams.put("window", window);
		tabParams.put("title", "Tab "+name);
		TiViewProxy tab = createProxy("Tab", tabParams);
		
		return tab;
	}
	
	@Kroll.method(runOnUiThread=true)
	public TiViewProxy createTabGroup() {
		TabProxy tab1 = (TabProxy) createTabWithLabelText("1", "blue");
		TabProxy tab2 = (TabProxy) createTabWithLabelText("2", "yellow");
		
		Map<String,Object> tabGroupParams = new HashMap<String,Object>();
		tabGroupParams.put("backgroundColor", "red");
		
		TabGroupProxy tabGroup = (TabGroupProxy) createProxy("TabGroup", tabGroupParams);
		
		tabGroup.addTab(tab1);
		tabGroup.addTab(tab2);

		return tabGroup;
	}
	
	@Kroll.method(runOnUiThread=true)
	public TiViewProxy createWindow() {
		Map<String,Object> labelParams = new HashMap<String,Object>();
		labelParams.put("text", "I am tab "+"foo");
		labelParams.put("height", 24);
		labelParams.put("width", 200);
		TiViewProxy label = createProxy("Label", labelParams);
		
		Map<String,Object> windowParams = new HashMap<String,Object>();
		windowParams.put("layout", "vertical");
		windowParams.put("backgroundColor", "green");
		TiViewProxy window = createProxy("Window", windowParams);
		
		window.add(label);
		
		return window;
	}
	
	@Kroll.method(runOnUiThread=true)
	public TiViewProxy createLightweightWindow() {
		Map<String,Object> labelParams = new HashMap<String,Object>();
		labelParams.put("text", "this is a lightweight window");
		labelParams.put("height", 24);
		labelParams.put("width", 200);
		TiViewProxy label = createProxy("Label", labelParams);
		
		Map<String,Object> windowParams = new HashMap<String,Object>();
		windowParams.put("layout", "vertical");
		windowParams.put("backgroundColor", "orange");
		TiViewProxy window = createProxy("BaseWindow", windowParams);
		
		window.add(label);
		
		return window;
	}

}