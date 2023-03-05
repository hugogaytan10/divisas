package net.ivanvega.proyectodivisacontentprividera

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import net.ivanvega.proyectodivisacontentprividera.db.DbHelper
import net.ivanvega.proyectodivisacontentprividera.db.MiDbMonedas

const val MONEDAS = 0
class MiContentProvider : ContentProvider() {

    private lateinit var miDbMonedas: MiDbMonedas
    private lateinit var dbHelper: DbHelper

    companion object {
        const val AUTHORITY = "net.ivanvega.proyectodivisacontentprovider.provider"
        const val TABLE_NAME = "Moneda"
    }
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, "monedas", 1)
        addURI(AUTHORITY, "monedas/#", 2)
    }
    override fun onCreate(): Boolean {
        dbHelper = DbHelper(context!!)
        miDbMonedas = Room.databaseBuilder(
            context!!.applicationContext,
            MiDbMonedas::class.java, "midbmonedas"
        ).build()

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor?
        val match = uriMatcher.match(uri)

        cursor = when(match) {
            MONEDAS -> {
                db.query(
                    "Moneda",
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            else -> throw IllegalArgumentException("URI desconocido: $uri")
        }

        cursor.setNotificationUri(context!!.contentResolver, uri)

        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

}
