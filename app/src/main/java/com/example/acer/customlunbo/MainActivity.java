package com.example.acer.customlunbo;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private List<ImageView> listshape;
    private ImageAdapter imageAdapter;
    private List<Integer> list;
      private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){

                int index = viewPager.getCurrentItem();
                //向右轮播
                viewPager.setCurrentItem(index+=1);
                //间隔一秒
                handler.sendEmptyMessageDelayed(0, 2000);

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
         list.add(R.drawable.img11);
         list.add(R.drawable.img12);
         list.add(R.drawable.img13);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        viewPager.setCurrentItem(100000);
        setAdapter();
        handler.sendEmptyMessageDelayed(0,2000);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                for (int i =0;i<listshape.size();i++){
                    if(i==position%listshape.size()){
                        listshape.get(i).setImageResource(R.drawable.shape2);
                    }else{
                        listshape.get(i).setImageResource(R.drawable.shape1);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initshape();
    }
    public void setAdapter(){
        if(imageAdapter==null) {
            imageAdapter = new ImageAdapter(MainActivity.this, list,handler);
            viewPager.setAdapter(imageAdapter);
        }else{
            imageAdapter.notifyDataSetChanged();
        }
    }
    //初始化小圆点的方法
    private void initshape(){
        //创建装着小圆点的集合
        listshape = new ArrayList<>();

        //清空布局和集合
        linearLayout.removeAllViews();
        listshape.clear();

        for (int i=0;i<3;i++){
            ImageView imageView = new ImageView(MainActivity.this);
            if(i==0){
                //如果当前是第一页,就设置选中的图片
                imageView.setImageResource(R.drawable.shape2);
            }else{
                imageView.setImageResource(R.drawable.shape1);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(9,0,9,0);

            //添加到集合和布局里
            listshape.add(imageView);
            linearLayout.addView(imageView,layoutParams);
        }
    }
}
