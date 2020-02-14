package com.example.myapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskukirjaViewModel extends AndroidViewModel {
    private TaskukirjaRepository mRepository;

    private LiveData<List<Taskukirja>> mKaikkiTaskukirjat;

    public TaskukirjaViewModel(Application application) {
        super(application);
        mRepository = new TaskukirjaRepository(application);
        mKaikkiTaskukirjat = mRepository.getKaikkiTaskukirjat();
    }

    LiveData<List<Taskukirja>> getKaikkiTaskukirjat() { return mKaikkiTaskukirjat; }

    public void insert(Taskukirja taskukirja) { mRepository.insert(taskukirja); }
}
