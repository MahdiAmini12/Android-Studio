package com.slowtech.taptag

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "university.db"
        const val DATABASE_VERSION = 1

        // Tables
        const val TABLE_STUDENTS = "students"
        const val TABLE_PROFESSORS = "professors"
        const val TABLE_STAFF = "staff"

        // Common columns
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_NATIONAL_ID = "national_id"

        // Student-specific columns
        const val COLUMN_STUDENT_ID = "student_id"
        const val COLUMN_MAJOR = "major"

        // Professor-specific columns
        const val COLUMN_EMPLOYEE_ID = "employee_id"
        const val COLUMN_DEPARTMENT = "department"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create Students table
        val createStudentsTable = """
            CREATE TABLE $TABLE_STUDENTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_NATIONAL_ID TEXT,
                $COLUMN_STUDENT_ID TEXT,
                $COLUMN_MAJOR TEXT
            )
        """
        db.execSQL(createStudentsTable)

        // Create Professors table
        val createProfessorsTable = """
            CREATE TABLE $TABLE_PROFESSORS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_NATIONAL_ID TEXT,
                $COLUMN_EMPLOYEE_ID TEXT,
                $COLUMN_DEPARTMENT TEXT
            )
        """
        db.execSQL(createProfessorsTable)

        // Create Staff table
        val createStaffTable = """
            CREATE TABLE $TABLE_STAFF (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_NATIONAL_ID TEXT,
                $COLUMN_EMPLOYEE_ID TEXT
            )
        """
        db.execSQL(createStaffTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PROFESSORS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STAFF")
        onCreate(db)
    }

    // Insert data into the Students table
    fun insertStudent(name: String, nationalId: String, studentId: String, major: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_NATIONAL_ID, nationalId)
        values.put(COLUMN_STUDENT_ID, studentId)
        values.put(COLUMN_MAJOR, major)
        return db.insert(TABLE_STUDENTS, null, values)
    }

    // Insert data into the Professors table
    fun insertProfessor(name: String, nationalId: String, employeeId: String, department: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_NATIONAL_ID, nationalId)
        values.put(COLUMN_EMPLOYEE_ID, employeeId)
        values.put(COLUMN_DEPARTMENT, department)
        return db.insert(TABLE_PROFESSORS, null, values)
    }

    // Insert data into the Staff table
    fun insertStaff(name: String, nationalId: String, employeeId: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_NATIONAL_ID, nationalId)
        values.put(COLUMN_EMPLOYEE_ID, employeeId)
        return db.insert(TABLE_STAFF, null, values)
    }
}
