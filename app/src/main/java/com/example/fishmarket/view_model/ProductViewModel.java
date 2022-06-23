package com.example.fishmarket.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fishmarket.R;
import com.example.fishmarket.adapter.ProductCategoryAdapter;
import com.example.fishmarket.model.CategoryPOJO;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {
    public CategoryPOJO categoryPOJO;
    public ArrayList<CategoryPOJO> categoryPOJOS=new ArrayList<>();
    public ProductCategoryAdapter categoryAdapter=new ProductCategoryAdapter(categoryPOJOS);

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
        categoryPOJOS.add(new CategoryPOJO("TABLE SIZE FISH", R.drawable.feed));

        // categoryPOJOS.add(new CategoryPOJO("AQUARIUM", R.drawable.feed));

        categoryAdapter.notifyDataSetChanged();

    }

    public ArrayList<CategoryPOJO> subCategoryPOJOS;

    public ArrayList<CategoryPOJO> addDataInSubCategory(){
        subCategoryPOJOS =new ArrayList<>();
        subCategoryPOJOS.add(new CategoryPOJO("FISHERIES EQUIPMENTS", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("COLD CHAIN FACILITIES", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("BIO FLOC/ RAS", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("FISH FEED MILL/ PLANT EQUIPMENT", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("VEHICLES WITH ICE BOX", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("MARINE FISHERIES", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("SEA CAGE / RESERVOIRS CAGE/PEN", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("HATCHERY", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("FISH VALUE ADD ENTERPRISE UNITS", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("FABRICATION MATERIAL ", R.drawable.department_fish));


        return  subCategoryPOJOS;
    }

    MutableLiveData<ArrayList<CategoryPOJO>> mutableLiveData;


}
