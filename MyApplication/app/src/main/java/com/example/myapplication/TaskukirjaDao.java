package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskukirjaDao {



        /*
         allowing the insert of the same word multiple times by passing a
         conflict resolution strategy
        */
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Taskukirja taskukirja) ;

        @Query("DELETE FROM taskukirja_table")
        void deleteAll();

        @Query("SELECT * from taskukirja_table ORDER BY numero ASC")
        LiveData<List<Taskukirja>> getKaikkiTaskukirjat();



    }