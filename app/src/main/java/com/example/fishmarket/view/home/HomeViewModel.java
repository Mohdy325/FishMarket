package com.example.fishmarket.view.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fishmarket.R;
import com.example.fishmarket.adapter.HomeCategoryAdapter;
import com.example.fishmarket.adapter.HomeProductAdapter;
import com.example.fishmarket.adapter.ProductCategoryAdapter;
import com.example.fishmarket.api_services.ApiManager;
import com.example.fishmarket.model.CategoryPOJO;
import com.example.fishmarket.model.CommonModel;
import com.example.fishmarket.model.ProductPOJO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public HomeCategoryAdapter categoryAdapter=new HomeCategoryAdapter();


    public void setUpData(){
        ApiManager.getApiService().callHomeApi().enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful() && response.body().status){

                    categoryAdapter.updateList(response.body().categoryPOJOS);
                    homeAdapter.updateList(response.body().in_trending);
                    successLiveData.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                errorLiveData.postValue("Something went wrong...");
            }
        });
        /*if (categoryPOJOS.size()>0){
            return;
        }*/

        // categoryPOJOS =new ArrayList<>();
       /* categoryPOJOS.add(new CategoryPOJO("AQUACULTURE EQUIPMENT", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("AQUARIUM", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("AQUACULTURE MEDICINE", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FISH FEED", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FISH PRODUCTS", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FISH SEEDS", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FRESH FROZEN FISH (ice BOX)", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("LIVE FISH", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("RAW MATERIALS", R.drawable.feed));*/
       // categoryPOJOS.add(new CategoryPOJO("TABLE SIZE FISH", R.drawable.feed));

        // categoryPOJOS.add(new CategoryPOJO("AQUARIUM", R.drawable.feed));


    }
    public MutableLiveData<CommonModel> successLiveData=new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData=new MutableLiveData<>();

    public HomeProductAdapter homeAdapter = new HomeProductAdapter();

    public void getProducts(){
        ArrayList<ProductPOJO> arrayList=new ArrayList<>();
        arrayList.add(new ProductPOJO("AIR BLOWER"));
        arrayList.add(new ProductPOJO("OXYGEN GENERATOR/DIFUSER"));
        arrayList.add(new ProductPOJO("ELECTRICAL PUMPS"));
        arrayList.add(new ProductPOJO("PUMP ACCESSORIES"));
        arrayList.add(new ProductPOJO("AERATOR/ PADDLE WHEEL AERATOR"));
        arrayList.add(new ProductPOJO("DO METER"));
        arrayList.add(new ProductPOJO("ELECTRONIC WEIGHING BALANCE"));
        arrayList.add(new ProductPOJO("COMPRESSORS"));
        arrayList.add(new ProductPOJO("GENERATOR SET"));
        arrayList.add(new ProductPOJO("FEED DISPENSER"));
        arrayList.add(new ProductPOJO("PLASTIC CRATES"));


       // mutableLiveData.postValue(arrayList);
    }
}