package com.example.mvvmdemoapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvvmdemoapp.R;
import com.example.mvvmdemoapp.view.adapter.ListAdapter;
import com.example.mvvmdemoapp.viewmodel.ListViewModel;

import java.util.List;

import static com.example.mvvmdemoapp.util.constants.Constants.NAME_LIST_FRAGMENT_TAG;

public class ListFragment extends Fragment {
    private String mType;
    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private ListViewModel mViewModel;

    public ListFragment() {
    }

    public ListFragment(String dataValueType) {
        mType = dataValueType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        if (savedInstanceState != null) {
            mType = savedInstanceState.getString("mType");
        }
        mRecyclerView = view.findViewById(R.id.list);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.init();
        LiveData<List<String>> listLiveData =
                mType.equals(NAME_LIST_FRAGMENT_TAG) ? mViewModel.getNamesList() : mViewModel.getNumbersList();
        listLiveData.observe(this.getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> newList) {
                mAdapter.setList(newList);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
        mAdapter = new ListAdapter(mType.equals(NAME_LIST_FRAGMENT_TAG) ? mViewModel.getNamesList().getValue() :
                mViewModel.getNumbersList().getValue());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void sortDataByName() {
        mViewModel.sortDataByName();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("mType", mType);
        super.onSaveInstanceState(outState);
    }
}
