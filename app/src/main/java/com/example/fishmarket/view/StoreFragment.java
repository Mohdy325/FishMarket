package com.example.fishmarket.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;

import com.example.fishmarket.R;
import com.example.fishmarket.adapter.banners.CustomAdapter;
import com.example.fishmarket.databinding.FragStoreBinding;
import com.example.fishmarket.utils.BaseFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StoreFragment extends BaseFragment {
FragStoreBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragStoreBinding.inflate(inflater, container, false);
        setUpSlider();


        return binding.getRoot();
    }



    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    Integer[] imageId={R.drawable.logo_top,R.drawable.fish1,R.drawable.logo_top,
            };
    private void setUpSlider(){
        PagerAdapter adapter = new CustomAdapter(getActivity(),imageId);
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager, true);

        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == imageId.length-1) {
                    currentPage = 0;
                }
                if (binding==null){
                    handler.removeCallbacks(this::run);
                    return;
                }
                binding.viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
        new Handler().postDelayed(()->{
            binding.nestedScrollView.scrollTo(0,0);
        },1500);
    }

    /*ArrayList<CategoryPOJO> categoryPOJOS=new ArrayList<>();
    HomeCategoryAdapter categoryAdapter;
    private void setUpCategory(){
        categoryPOJOS=new ArrayList<>();
        categoryPOJOS.add(new CategoryPOJO("Business\nServices",R.drawable.business_services));
        categoryPOJOS.add(new CategoryPOJO("Rentals",R.drawable.rentals));
        categoryPOJOS.add(new CategoryPOJO("Community\nEvents",R.drawable.events));

        categoryAdapter=new HomeCategoryAdapter(fragment,categoryPOJOS);
        binding.rvCategory.setAdapter(categoryAdapter);
    }
    ArrayList<ProductPOJO> trendyProudcts=new ArrayList<>();
    ProductAdapter trendyAdapter;
    private void setTrendyAdapter(){
        trendyProudcts=new ArrayList<>();
        trendyProudcts.add(new ProductPOJO("Service 1","150",R.drawable.service1));
        trendyProudcts.add(new ProductPOJO("Service 2","100",R.drawable.service2));
        trendyProudcts.add(new ProductPOJO("Service 3","160",R.drawable.service3));

        trendyAdapter=new ProductAdapter(context,trendyProudcts);
        binding.rvTrendy.setAdapter(trendyAdapter);

    }

    ArrayList<ProductPOJO> featuredProudcts=new ArrayList<>();
    ProductAdapter featuresAdapter;
    private void setFeaturedProudctsAdapter(){
        featuredProudcts=new ArrayList<>();
        featuredProudcts.add(new ProductPOJO("Service 1","150",R.drawable.service3));
        featuredProudcts.add(new ProductPOJO("Service 2","100",R.drawable.service2));
        featuredProudcts.add(new ProductPOJO("Service 3","160",R.drawable.service1));

        featuresAdapter=new ProductAdapter(context,featuredProudcts);
        binding.rvFeatured.setAdapter(featuresAdapter);

    }
    ProductAdapter latestFleaAdapter;
    ArrayList<ProductPOJO> latestProudcts=new ArrayList<>();
    private void setLatestFleaAdapter(){
        latestProudcts=new ArrayList<>();
        latestProudcts.add(new ProductPOJO("Recent Service 3","150",R.drawable.service3));
        latestProudcts.add(new ProductPOJO("Recent Service 1","100",R.drawable.service1));
        latestProudcts.add(new ProductPOJO("Recent Service 2","160",R.drawable.service2));

        latestFleaAdapter=new ProductAdapter(context,latestProudcts);
        binding.rvRecently.setAdapter(latestFleaAdapter);
    }


    ProductAdapter latestServiceAdapter;
    ArrayList<ProductPOJO> latestServiceProudcts=new ArrayList<>();
    private void setLatestServiceProudctsAdapter(){
        latestServiceProudcts=new ArrayList<>();
        latestServiceProudcts.add(new ProductPOJO("Services 1","150",R.drawable.service2));
        latestServiceProudcts.add(new ProductPOJO("Services 2","100",R.drawable.service3));
        latestServiceProudcts.add(new ProductPOJO("Services 3","160",R.drawable.service1));

        latestServiceAdapter=new ProductAdapter(context,latestServiceProudcts);
        binding.rvLatestServices.setAdapter(latestServiceAdapter);
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
