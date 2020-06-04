package com.example.hime.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.hime.R;
import com.example.hime.base.BaseActivity;
import com.example.hime.base.BasePresenter;
import com.example.hime.ui.contact.ContractFragment;
import com.example.hime.ui.dynamic.DynamicFragment;
import com.example.hime.ui.main.adapter.MainViewPager;

import com.example.hime.ui.message.MessageFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBarImgMain;
    private TextView mBarTextMain;
    private TextView mBarRightMain;
    private Toolbar mBarMain;
    private TabLayout mTabMain;
    private ConstraintLayout mParentLayoutMain;
    private NavigationView mNavigationMain;
    private DrawerLayout mDrawerMain;
    private ArrayList<String> tabs;
    private ArrayList<Fragment> fragments;
    private MessageFragment messageFragment;
    private MessageFragment messageFragment1;
    private ContractFragment contractFragment;
    private DynamicFragment dynamicFragment;
    private DynamicFragment dynamicFragment1;
    private ViewPager mViewpagerMain;
    private ArrayList<Integer> imgs;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

        mBarImgMain = (ImageView) findViewById(R.id.main_bar_img);
        mBarTextMain = (TextView) findViewById(R.id.main_bar_text);
        mBarRightMain = (TextView) findViewById(R.id.main_bar_right);
        mBarMain = (Toolbar) findViewById(R.id.main_bar);
        mTabMain = (TabLayout) findViewById(R.id.main_tab);
        mParentLayoutMain = (ConstraintLayout) findViewById(R.id.main_parent_layout);
        mNavigationMain = (NavigationView) findViewById(R.id.main_navigation);
        mDrawerMain = (DrawerLayout) findViewById(R.id.main_drawer);
        mViewpagerMain = (ViewPager) findViewById(R.id.main_viewpager);

        //ActionBarDrawerToggle是和DrawerLayout搭配使用的,监听drawer的显示和隐藏
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerMain, mBarMain, R.string.open, R.string.close);
        mDrawerMain.addDrawerListener(toggle);
        //ActionBarDrawerToggle的syncState()方法会和Toolbar关联，将图标放入到Toolbar上
//        toggle.syncState();

        setLayoutNavigation();
        openNavigation();

        initImg();
        initTab();
        initFragment();

        for (int i = 0; i < tabs.size(); i++) {
            mTabMain.addTab(mTabMain.newTab().setText(tabs.get(i)));
        }

        MainViewPager mainViewPager = new MainViewPager(getSupportFragmentManager(), fragments, tabs);
        mViewpagerMain.setAdapter(mainViewPager);
        mTabMain.setupWithViewPager(mViewpagerMain);

        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tabAt = mTabMain.getTabAt(i);
            tabAt.setCustomView(setView(i));
        }
    }

    private void initImg() {
        imgs = new ArrayList<>();
        imgs.add(R.drawable.message_sel);
        imgs.add(R.drawable.contact_sel);
        imgs.add(R.drawable.dynamice_sel);
    }

    private View setView(int i) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.main_tab_layout, null);
        ImageView img = inflate.findViewById(R.id.tab_img);
        TextView text = inflate.findViewById(R.id.tab_text);
        text.setText(tabs.get(i));
        img.setImageResource(imgs.get(i));
        return inflate;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        messageFragment1 = new MessageFragment();
        contractFragment = new ContractFragment();
        dynamicFragment1 = new DynamicFragment();
        fragments.add(messageFragment);
        fragments.add(contractFragment);
        fragments.add(dynamicFragment);
    }

    private void initTab() {
        tabs = new ArrayList<>();
        tabs.add(getResources().getString(R.string.message));
        tabs.add(getResources().getString(R.string.contact));
        tabs.add(getResources().getString(R.string.dybamic));
    }

    private void openNavigation() {
        mBarImgMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerMain.openDrawer(mNavigationMain);
            }
        });
    }

    /*
        布局侧滑联动
     */
    private void setLayoutNavigation() {
        mDrawerMain.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                int right = mNavigationMain.getRight();
                mParentLayoutMain.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bar_img:
                // TODO 20/05/28
//                mDrawerMain.openDrawer(mNavigationMain);
                break;
            default:
                break;
        }
    }
}
