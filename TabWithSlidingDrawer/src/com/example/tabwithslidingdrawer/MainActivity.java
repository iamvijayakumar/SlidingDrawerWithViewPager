package com.example.tabwithslidingdrawer;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements
		android.support.v7.app.ActionBar.OnNavigationListener, TabListener {
	String[] tabsName_thatstamil = { "சற்று முன்", "சினிமா", "வர்த்தகம்",
			"கலாச்சாரம் ", "பெண்கள்" };

	ViewPager viewPager;
	TapFragmentManager fragmentManger;

	ActionBar actionBar;
	String[] menutitles;
	TypedArray menuIcons;
	// nav drawer title
	private CharSequence mTitle;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private List<DrawingRowItem> rowItems;
	private CustomAdapter adapter;
	int tabsize;

	int selectedpositoin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewPager = (ViewPager) findViewById(R.id.pager);

		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		fragmentManger = new TapFragmentManager(getSupportFragmentManager());
		viewPager.setAdapter(fragmentManger);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		menutitles = getResources().getStringArray(R.array.news_Sites);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.slider_list);

		rowItems = new ArrayList<DrawingRowItem>();

		for (int i = 0; i < menutitles.length; i++) {
			DrawingRowItem items = new DrawingRowItem(menutitles[i]);
			rowItems.add(items);
		}

		getSupportActionBar().setTitle(
				Html.fromHtml("<font color=\"black\">" + mTitle + " - "
						+ menutitles[0] + "</font>"));
		// getActionBar().setTitle(mTitle +menutitles[0]);
		adapter = new CustomAdapter(getApplicationContext(), rowItems);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new SlideitemListener());
		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				// getActionBar().setTitle(mTitle
				// +menutitles[selectedpositoin]);
				getSupportActionBar().setTitle(
						Html.fromHtml("<font color=\"black\">" + mTitle + " - "
								+ menutitles[selectedpositoin] + "</font>"));
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(
						Html.fromHtml("<font color=\"grey\">"
								+ "Select News papers" + "</font>"));
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		for (String str : tabsName_thatstamil) {
			actionBar.addTab(actionBar.newTab().setText(str)
					.setTabListener(this));
		}

		viewPager
				.setOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int tabPosition) {
						actionBar.setSelectedNavigationItem(tabPosition);

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});

	}

	class SlideitemListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			// / Do Here your fragment things

			fragmentManger = new TapFragmentManager(getSupportFragmentManager());
			viewPager.setAdapter(fragmentManger);
			mDrawerLayout.closeDrawer(mDrawerList);
		}

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}

	public class TapFragmentManager extends FragmentStatePagerAdapter {

		public TapFragmentManager(FragmentManager fm) {
			super(fm);

		}

		@Override
		public SampleFragment getItem(int tab_position) {
			return new SampleFragment();

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return tabsName_thatstamil.length;
		}

	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
		// viewPager.notifyAll();
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startActivity(new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("https://play.google.com/store/apps/details?id=com.flashnews.tamilflashnews")));
			return true;
		}
		return super.onOptionsItemSelected(item);

	}

	/***
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);

		return super.onPrepareOptionsMenu(menu);
	}

}
