package com.example.tiki.viewmodule;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailProductViewModelFactory implements ViewModelProvider.Factory {

    private CategoryModel categoryModel;

    public DetailProductViewModelFactory(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailProductViewModel.class)) {
            return (T) new DetailProductViewModel(categoryModel);
        }
        throw new IllegalArgumentException("Argument is invalid");

    }
}
