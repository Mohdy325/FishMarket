package com.example.fishmarket.view.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fishmarket.R;
import com.example.fishmarket.adapter.HomeCategoryAdapter;
import com.example.fishmarket.adapter.ProductCategoryAdapter;
import com.example.fishmarket.model.CategoryPOJO;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public ArrayList<CategoryPOJO> categoryPOJOS=new ArrayList<>();
    public HomeCategoryAdapter categoryAdapter=new HomeCategoryAdapter(categoryPOJOS);

    public void setUpData(){
        if (categoryPOJOS.size()>0){
            return;
        }
        // categoryPOJOS =new ArrayList<>();
        categoryPOJOS.add(new CategoryPOJO("AQUACULTURE EQUIPMENT", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("AQUARIUM", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("AQUACULTURE MEDICINE", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FISH FEED", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FISH PRODUCTS", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FISH SEEDS", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FRESH FROZEN FISH (ice BOX)", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("LIVE FISH", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("RAW MATERIALS", R.drawable.feed));
       // categoryPOJOS.add(new CategoryPOJO("TABLE SIZE FISH", R.drawable.feed));

        // categoryPOJOS.add(new CategoryPOJO("AQUARIUM", R.drawable.feed));

        categoryAdapter.notifyDataSetChanged();

    }
}