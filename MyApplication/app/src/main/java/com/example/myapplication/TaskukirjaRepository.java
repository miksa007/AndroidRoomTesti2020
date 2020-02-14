package com.example.myapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskukirjaRepository {
    private TaskukirjaDao mTaskukirjaDao;
    private LiveData<List<Taskukirja>> mKaikkiTaskukirjat;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    TaskukirjaRepository(Application application) {
        TaskukirjaRoomDatabase db = TaskukirjaRoomDatabase.getDatabase(application);
        mTaskukirjaDao=db.taskukirjaDao();
        mKaikkiTaskukirjat=mTaskukirjaDao.getKaikkiTaskukirjat();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Taskukirja>> getKaikkiTaskukirjat() {
        return mKaikkiTaskukirjat;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Taskukirja taskukirja) {
        TaskukirjaRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTaskukirjaDao.insert(taskukirja);
        });
    }
}
