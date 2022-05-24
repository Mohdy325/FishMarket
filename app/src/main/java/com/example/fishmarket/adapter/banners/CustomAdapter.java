package com.example.fishmarket.adapter.banners;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.fishmarket.R;


public class CustomAdapter extends PagerAdapter {

    private Activity activity;
    private Integer[] imagesArray;

    public CustomAdapter(Activity activity, Integer[] imagesArray){

        this.activity = activity;
        this.imagesArray = imagesArray;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (activity).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.list_slider_image, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.iv_slider_image);
        imageView.setImageResource(imagesArray[position]);

        ((ViewPager)container).addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imagesArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }
}