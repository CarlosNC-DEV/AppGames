package com.wscauca.retooneapp.model.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.wscauca.retooneapp.model.Constantes

class HelperDb(context: Context) : SQLiteOpenHelper(context, Constantes.DB_NAME, null, Constantes.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constantes.CREATE_TBL_USUARIOS)
        db?.execSQL(Constantes.CREATE_TBL_HISTORIAL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}