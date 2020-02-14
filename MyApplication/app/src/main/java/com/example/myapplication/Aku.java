package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "taskukirja_table")
public class Aku {

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "numero")
        private int mNro;

        @NonNull
        @ColumnInfo(name = "nimi")
        private String mNimi;

        public Aku(@NonNull int nro, @NonNull String nimi ) {
            this.mNimi = nimi;
            this.mNro=nro;
        }

        public String getmNimi(){return this.mNimi;}
    public String getWord(){return this.mNimi;}
    }
