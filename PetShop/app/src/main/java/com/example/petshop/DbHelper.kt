package com.example.petshop

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context, "AppData", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) { // создание базы данных
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, pass TEXT)"
        db!!.execSQL(query) // выполнение команды с передачей запроса

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { // удаление и обновление
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) { // добавление нового пользователя в таблицу users
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()
    }

    fun getUser(login : String, pass: String) : Boolean {
        val db = this.readableDatabase

        val result  = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND pass = '$pass'", null)
        return result.moveToFirst()

    }


}