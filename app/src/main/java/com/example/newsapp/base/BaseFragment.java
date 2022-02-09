package com.example.newsapp.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;

public abstract class BaseFragment<VB extends ViewBinding > extends Fragment   {

    protected VB binding;
    protected abstract VB bind();
    protected NavController navController;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding = bind();
        navController = Navigation.findNavController(requireActivity(),R.id.nav_host);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        setupViews();
        callRequests();
        setupListeners();
        setupObservers();
    }

    protected abstract void setupObservers();

    protected abstract void setupListeners();

    protected abstract void callRequests();

    protected abstract void setupViews();

    protected abstract void initViews();
}