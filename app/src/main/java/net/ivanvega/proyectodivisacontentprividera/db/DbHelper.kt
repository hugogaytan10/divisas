package net.ivanvega.proyectodivisacontentprividera.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "midbmonedas.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MonedaDao.CREATE_TABLE_MONEDAS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MonedaDao.DROP_TABLE_MONEDAS)
        onCreate(db)
    }
}