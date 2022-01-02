package com.example.signupsignin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, "signUpSignIn.db", null, 1) {

    private val database: SQLiteDatabase = writableDatabase


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table users (email string primary key, name text, phone string, password string)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun saveUser(userEmail: String, userName: String, userPhone: String, userPassword: String): Long {
        val contentValues = ContentValues()
        contentValues.put("email", userEmail)
        contentValues.put("name", userName)
        contentValues.put("phone", userPhone)
        contentValues.put("password", userPassword)
        val success = database.insert("users", null, contentValues)
        if (success < 0) {
            println("Email already used")
        }
        return success
    }

    @SuppressLint("Range")
    fun readUsers(userE: String, userP: String): ArrayList<String>{
        val credentials = arrayListOf<String>()
        val userInfo = listOf(userE, userP).toTypedArray()
        var cursor: Cursor? = null
        cursor = database.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", userInfo)


        if (cursor.count < 1) {
            return credentials  // This's going to return an empty arrayList so this toast in SignIn.kt will be displayed
        }

        cursor.moveToFirst()
        val email = cursor.getString(cursor.getColumnIndex("email"))
        val password = cursor.getString(cursor.getColumnIndex("password"))
        val name = cursor.getString(cursor.getColumnIndex("name"))
        val phone = cursor.getString(cursor.getColumnIndex("phone"))
        credentials.add(0, email)
        credentials.add(1, password)
        credentials.add(2, name)   //NOTE TO MYSELF: Do it again by creating User data class
        credentials.add(3, phone)

        return credentials

    }

}