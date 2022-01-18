package hr.algebra.nasa.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import hr.algebra.nasa.model.Item

private const val DB_NAME = "items.db"
private const val DB_VERSION = 1
private const val TABLE_NAME = "Items"

private val CREATE = "create table $TABLE_NAME(" +
        "${Item::_id.name} integer primary key autoincrement, " +
        "${Item::title.name} text not null, " +
        "${Item::explanation.name} text not null, " +
        "${Item::picturePath.name} text not null, " +
        "${Item::date.name} text not null, " +
        "${Item::read.name} integer not null" +
        ")"

private const val DROP = "drop table $TABLE_NAME"

class NasaSqlHelper(context: Context?)
        : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), NasaRepository {
        override fun onCreate(db: SQLiteDatabase) {
                db.execSQL(CREATE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
                db.execSQL(DROP)
                onCreate(db)
        }

        override fun delete(selection: String?, selectionArgs: Array<String>?)
        = writableDatabase.delete(TABLE_NAME, selection, selectionArgs)

        override fun update(
                values: ContentValues?, selection: String?,
                selectionArgs: Array<String>?
        ) = writableDatabase.update(TABLE_NAME, values, selection, selectionArgs)

        override fun insert(values: ContentValues?)
        = writableDatabase.insert(TABLE_NAME, null, values)

        override fun query(
                projection: Array<String>?, selection: String?,
                selectionArgs: Array<String>?, sortOrder: String?
        ): Cursor = readableDatabase.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder)


}