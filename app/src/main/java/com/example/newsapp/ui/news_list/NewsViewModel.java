package com.example.newsapp.ui.news_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.common.Resource;
import com.example.newsapp.data.model.MainResponse;
import com.example.newsapp.data.repositories.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NewsViewModel extends ViewModel {

    private MainRepository repository;

    public LiveData<Resource<MainResponse>> newsLiveData;

    @Inject
    public NewsViewModel (MainRepository repository) {
        this .repository = repository;
    }
    public void getTopNews () {
        newsLiveData = repository.getTopNews();
    }
}
