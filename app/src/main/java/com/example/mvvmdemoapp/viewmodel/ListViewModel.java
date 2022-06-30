package com.example.mvvmdemoapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.example.mvvmdemoapp.model.ContactData;
import com.example.mvvmdemoapp.repository.ContactDataRepository;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {
    private static MutableLiveData<List<String>> mNames;
    private static MutableLiveData<List<String>> mNumbers;
    private ContactDataRepository mRepo;
    private Observer<List<ContactData>> mDataObserver;

    public LiveData<List<String>> getNamesList() {
        return mNames;
    }

    public LiveData<List<String>> getNumbersList() {
        return mNumbers;
    }

    @Override
    protected void onCleared() {
        mRepo.getContactDataList().removeObserver(mDataObserver);
        super.onCleared();
    }

    public void init() {
        if (mNames != null && mNumbers != null)
            return;
        mNames = new MutableLiveData<>();
        mNumbers = new MutableLiveData<>();
        mRepo = ContactDataRepository.getInstance();
        mDataObserver = new Observer<List<ContactData>>() {
            @Override
            public void onChanged(List<ContactData> contactDataList) {
                setData(contactDataList);
            }
        };
        mRepo.getContactDataList().observeForever(mDataObserver);
    }

    private void setData(List<ContactData> contactDataList) {
        List<String> names = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        contactDataList.stream().forEach(it -> {
            names.add(it.getName());
            numbers.add(it.getNumber());
        });
        mNames.setValue(names);
        mNumbers.setValue(numbers);
    }

    public void sortDataByName() {
        mRepo.sortDataByName();
    }

}
