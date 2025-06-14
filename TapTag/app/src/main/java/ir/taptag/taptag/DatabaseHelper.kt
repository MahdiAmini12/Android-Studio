package ir.taptag.taptag

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "university.db"
        const val DATABASE_VERSION = 1

        const val TABLE_STUDENTS = "students"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_NATIONAL_ID = "national_id"
        const val COLUMN_STUDENT_ID = "student_id"
        const val COLUMN_MAJOR = "major"
    }

    override fun onCreate(db: SQLiteDatabase) {
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
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENTS")
        onCreate(db)
    }

    fun insertStudent(name: String, nationalId: String, studentId: String, major: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_NATIONAL_ID, nationalId)
        values.put(COLUMN_STUDENT_ID, studentId)
        values.put(COLUMN_MAJOR, major)
        return db.insert(TABLE_STUDENTS, null, values)
    }
}
