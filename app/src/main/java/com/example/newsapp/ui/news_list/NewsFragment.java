package com.example.newsapp.ui.news_list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.base.BaseFragment;
import com.example.newsapp.common.Resource;
import com.example.newsapp.data.model.MainResponse;
import com.example.newsapp.data.repositories.MainRepository;
import com.example.newsapp.databinding.FragmentNewsBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NewsFragment extends BaseFragment<FragmentNewsBinding> {

    private NewsViewModel viewModel;
    private NewsAdapter adapter;

    @Override
    protected FragmentNewsBinding bind() {
        return FragmentNewsBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news, container, false);
    }
    @Override
    protected void setupObservers()  {
        viewModel.newsLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
            @Override
            public void onChanged(Resource<MainResponse> resource) {
                switch (resource.status) {
                case LOADING: {
                    binding.recycler.setVisibility(View.GONE);
                    binding.progress.setVisibility(View.VISIBLE);
                }
                    case SUCCESS:{
                        binding.recycler.setVisibility(View.VISIBLE);
                        binding.progress.setVisibility(View.GONE);
                        adapter.setNewsList(resource.data.getArticles());
                    }
                    case ERROR:
                        Snackbar.make(binding.getRoot(),resource.msg, BaseTransientBottomBar.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    @Override
    protected void setupListeners() {
    }

    @Override
    protected void callRequests() {
        viewModel.getTopNews();
    }

    @Override
    protected void setupViews() {
        binding.recycler.setAdapter(adapter);
    }

    @Override
    protected void initViews() {
        adapter=new NewsAdapter();
        viewModel= new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
    }
}
