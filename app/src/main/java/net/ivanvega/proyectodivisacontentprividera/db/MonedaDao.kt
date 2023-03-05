package net.ivanvega.proyectodivisacontentprividera.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import java.util.concurrent.Flow

@Dao
interface MonedaDao {

    @Insert
    suspend fun insertar(moneda: Moneda)

    @Query("select * from Moneda")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<Moneda>>

    @Query("DELETE FROM Moneda")
    suspend fun deleteAll() : Int

    @Query("select _ID, codeMoneda,nombreMoneda,pais from Moneda")
    fun getAllCursor(): Cursor


    companion object {
        const val TABLE_NAME = "monedas"
        const val CREATE_TABLE_MONEDAS = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT, nombre TEXT, pais TEXT)"
        const val DROP_TABLE_MONEDAS = "DROP TABLE IF EXISTS $TABLE_NAME"
    }

}