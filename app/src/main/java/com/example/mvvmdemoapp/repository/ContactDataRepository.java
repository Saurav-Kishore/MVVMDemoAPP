package com.example.mvvmdemoapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mvvmdemoapp.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactDataRepository {
    private static ContactDataRepository mInstance;
    private ArrayList<ContactData> mList = new ArrayList<>();
    private MutableLiveData<List<ContactData>> mData = new MutableLiveData<>();

    private ContactDataRepository() {
        setContactDataList();
        mData.setValue(mList);
    }

    public static ContactDataRepository getInstance() {
        if (mInstance == null)
            mInstance = new ContactDataRepository();
        return mInstance;
    }

    public LiveData<List<ContactData>> getContactDataList() {
        return mData;
    }

    public void sortDataByName() {
        mList.sort(new Comparator<ContactData>() {
            @Override
            public int compare(ContactData o1, ContactData o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        mData.setValue(mList);
    }

    private void setContactDataList() {
        mList.add(new ContactData("Rahul", "12378"));
        mList.add(new ContactData("Manoj", "67236"));
        mList.add(new ContactData("Saurav", "78679"));
        mList.add(new ContactData("Ram", "46612"));
        mList.add(new ContactData("Abhishek", "34687"));
        mList.add(new ContactData("Test", "72878"));
        mList.add(new ContactData("Hello", "63282"));
        mList.add(new ContactData("Krishna", "74973"));
    }
}
