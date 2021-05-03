package com.example.roomdemo2.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdemo2.Model.BazarData;
import com.example.roomdemo2.Repository.BazarRepo;

import java.util.List;

public class BazarViewModel extends AndroidViewModel {
    private BazarRepo mRepo;
    private LiveData<List<BazarData>> mList;

    public BazarViewModel(@NonNull Application application) {
        super(application);
        mRepo = new BazarRepo(application);
        mList = mRepo.getAllBazarList();
    }

    public void insert(BazarData bazarData) {
        mRepo.insert(bazarData);
    }

    public void delete(BazarData bazarData) {
        mRepo.delete(bazarData);
    }

    public void reset() {
        mRepo.reset();
    }

    public LiveData<List<BazarData>> getAllBazarList() {
        return mList;
    }
}
