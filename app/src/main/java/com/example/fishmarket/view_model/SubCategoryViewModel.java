package com.example.fishmarket.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fishmarket.adapter.ProductSubCategoryAdapter;
import com.example.fishmarket.api_services.ApiManager;
import com.example.fishmarket.model.CommonModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryViewModel extends ViewModel {
    public MutableLiveData<CommonModel> successLiveData=new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData=new MutableLiveData<>();
    public ProductSubCategoryAdapter subCategoryAdapter=new ProductSubCategoryAdapter();

    public void getSubCategory(String id){
        ApiManager.getApiService().getSubCategory(id).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful() && response.body().status){

                    subCategoryAdapter.updateList(response.body().sub_category);
                    successLiveData.postValue(response.body());

                }else if (response.message()!=null){
                    errorLiveData.postValue(response.message());
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

}
