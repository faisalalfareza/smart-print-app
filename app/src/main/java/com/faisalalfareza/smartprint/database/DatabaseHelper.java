package com.faisalalfareza.smartprint.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import com.faisalalfareza.smartprint.database.models.UserModels;
import com.faisalalfareza.smartprint.database.models.DocumentModels;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "smartprint_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(UserModels.CREATE_TABLE);
        db.execSQL(DocumentModels.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + UserModels.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DocumentModels.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /* Start : User Model */
        public void reInitializeTable() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE " + UserModels.TABLE_NAME);
            db.execSQL("DROP TABLE " + DocumentModels.TABLE_NAME);

            onCreate(db);
        }

        public boolean isRoleExists(String role) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(UserModels.TABLE_NAME,// Selecting Table
                    new String[]{UserModels.COLUMN_ROLE},//Selecting columns want to query
                    UserModels.COLUMN_ROLE + "=?",
                    new String[]{ role },//Where clause
                    null, null, null);

            if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email so return true
                return true;
            }

            //if email does not exist return false
            return false;
        }

        public String isRoleUserExists() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(UserModels.TABLE_NAME,// Selecting Table
                    new String[]{UserModels.COLUMN_ROLE},//Selecting columns want to query
                    UserModels.COLUMN_ROLE + "=?",
                    new String[]{ "Usual User" },//Where clause
                    null, null, null);

            if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email so return true
                return cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_ROLE));
            }

            //if email does not exist return false
            return null;
        }

        public String isRoleMerchantExists() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(UserModels.TABLE_NAME,// Selecting Table
                    new String[]{UserModels.COLUMN_ROLE},//Selecting columns want to query
                    UserModels.COLUMN_ROLE + "=?",
                    new String[]{ "Merchant" },//Where clause
                    null, null, null);

            if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email so return true
                return cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_ROLE));
            }

            //if email does not exist return false
            return null;
        }

        public boolean isLoggedInExists() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(UserModels.TABLE_NAME,// Selecting Table
                    new String[]{UserModels.COLUMN_ROLE},//Selecting columns want to query
                    UserModels.COLUMN_ISLOGGEDIN + "=?",
                    new String[]{ "1" },//Where clause
                    null, null, null);

            if (cursor != null && cursor.moveToFirst() && cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email so return true
                return true;
            }

            //if email does not exist return false
            return false;
        }

        public long insertUser(String role) {
            // get writable database as we want to write data
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            // `id` and `timestamp` will be inserted automatically.
            // no need to add them

            String name = "", roleDesc = "", email = "";
            switch (role) {
                case "Usual User":
                    name = UserModels.U_NAME_VALUE;
                    roleDesc = UserModels.U_ROLEDESC_VALUE;
                    email = UserModels.U_EMAIL_VALUE;
                    break;

                case "Merchant":
                    name = UserModels.M_NAME_VALUE;
                    roleDesc = UserModels.M_ROLEDESC_VALUE;
                    email = UserModels.M_EMAIL_VALUE;
                    break;
            }

            values.put(UserModels.COLUMN_ROLE, role);
            values.put(UserModels.COLUMN_ISLOGGEDIN, 1);

            values.put(UserModels.COLUMN_NAME, name);
            values.put(UserModels.COLUMN_ROLEDESC, roleDesc);
            values.put(UserModels.COLUMN_EMAIL, email);


            // insert row
            long id = db.insert(UserModels.TABLE_NAME, null, values);

            // close db connection
            db.close();

            // return newly inserted row id
            return id;
        }

        public int activateUserByRole(String role) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(UserModels.COLUMN_ISLOGGEDIN, 1);

            // updating row
            return db.update(UserModels.TABLE_NAME, values, UserModels.COLUMN_ROLE + " = ?",
                    new String[]{ role });
        }

        public int logoutAllUser() {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(UserModels.COLUMN_ISLOGGEDIN, 0);

            // updating row
            return db.update(UserModels.TABLE_NAME, values, UserModels.COLUMN_ISLOGGEDIN + " = ?",
                    new String[]{ String.valueOf(1) });
        }

        public UserModels loggedInByActiveUser() {
            // get readable database as we are not inserting anything
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(UserModels.TABLE_NAME,
                    new String[]{
                        UserModels.COLUMN_ID,
                        UserModels.COLUMN_NAME,
                        UserModels.COLUMN_ROLE,
                        UserModels.COLUMN_ROLEDESC,
                        UserModels.COLUMN_EMAIL,
                        UserModels.COLUMN_CREATEDDATE,
                        UserModels.COLUMN_ISLOGGEDIN,
                        UserModels.COLUMN_RATING,
                        UserModels.COLUMN_TRANSACTION
                    },
                    UserModels.COLUMN_ISLOGGEDIN + "=?",
                    new String[]{ String.valueOf(1) }, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

                // prepare note object
                UserModels note = new UserModels(
                        cursor.getInt(cursor.getColumnIndex(UserModels.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_ROLE)),
                        cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_ROLEDESC)),
                        cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_CREATEDDATE)),
                        cursor.getInt(cursor.getColumnIndex(UserModels.COLUMN_ISLOGGEDIN)),
                        cursor.getDouble(cursor.getColumnIndex(UserModels.COLUMN_RATING)),
                        cursor.getInt(cursor.getColumnIndex(UserModels.COLUMN_TRANSACTION))
                );

                // close the db connection
                cursor.close();

                return note;
        }

        public UserModels loggedInByRole(String role) {
            // this.deactivateIsLoggedInUser();
            this.activateUserByRole(role);

            // get readable database as we are not inserting anything
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(UserModels.TABLE_NAME,
                    new String[]{
                            UserModels.COLUMN_ID,
                            UserModels.COLUMN_NAME,
                            UserModels.COLUMN_ROLE,
                            UserModels.COLUMN_ROLEDESC,
                            UserModels.COLUMN_EMAIL,
                            UserModels.COLUMN_CREATEDDATE,
                            UserModels.COLUMN_ISLOGGEDIN,
                            UserModels.COLUMN_RATING,
                            UserModels.COLUMN_TRANSACTION
                    },
                    UserModels.COLUMN_ROLE + "=?",
                    new String[]{ role }, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            // prepare note object
            UserModels note = new UserModels(
                    cursor.getInt(cursor.getColumnIndex(UserModels.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_ROLE)),
                    cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_ROLEDESC)),
                    cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_CREATEDDATE)),
                    cursor.getInt(cursor.getColumnIndex(UserModels.COLUMN_ISLOGGEDIN)),
                    cursor.getDouble(cursor.getColumnIndex(UserModels.COLUMN_RATING)),
                    cursor.getInt(cursor.getColumnIndex(UserModels.COLUMN_TRANSACTION))
                    );

            // close the db connection
            cursor.close();

            return note;
        }
    /* End : User Model */

    /* Start : Upload Document */
        public boolean insertDocument(String merchant_role,
                                   String ud1_curLocTitle, String ud1_curLocCode,
                                   String ud2_curMerTitle,
                                   String ud3_curService,
                                   String ud4_curSubService, String ud4_setBuildQuality, String ud4_setPrintedPage, String ud4_setSidesOfPrint,
                                   String ud4_setPaperSize, String ud4_setPaperMargin, String ud4_setOrientation, String ud4_setPagePerSheet, String ud4_setBaseColor,
                                   String ud5_setDocName, String ud5_setAttachFileDir, String ud5_setNotes, String ud5_setFinishLimitDate, String ud5_setBookDate, String ud5_isToday,
                                   String status) {
            // get writable database as we want to write data
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            // `id` and `timestamp` will be inserted automatically.
            // no need to add them

            values.put(DocumentModels.COLUMN_role, merchant_role);
            values.put(DocumentModels.COLUMN_ud1_curLocTitle, ud1_curLocTitle);
            values.put(DocumentModels.COLUMN_ud1_curLocCode, ud1_curLocCode);
            values.put(DocumentModels.COLUMN_ud2_curMerTitle, ud2_curMerTitle);
            values.put(DocumentModels.COLUMN_ud3_curService, ud3_curService);
            values.put(DocumentModels.COLUMN_ud4_curSubService, ud4_curSubService);
            values.put(DocumentModels.COLUMN_ud4_setBuildQuality, ud4_setBuildQuality);
            values.put(DocumentModels.COLUMN_ud4_setPrintedPage, ud4_setPrintedPage);
            values.put(DocumentModels.COLUMN_ud4_setSidesOfPrint, ud4_setSidesOfPrint);
            values.put(DocumentModels.COLUMN_ud4_setPaperSize, ud4_setPaperSize);
            values.put(DocumentModels.COLUMN_ud4_setPaperMargin, ud4_setPaperMargin);
            values.put(DocumentModels.COLUMN_ud4_setOrientation, ud4_setOrientation);
            values.put(DocumentModels.COLUMN_ud4_setPagePerSheet, ud4_setPagePerSheet);
            values.put(DocumentModels.COLUMN_ud4_setBaseColor, ud4_setBaseColor);
            values.put(DocumentModels.COLUMN_ud5_setDocName, ud5_setDocName);
            values.put(DocumentModels.COLUMN_ud5_setAttachFileDir, ud5_setAttachFileDir);
            values.put(DocumentModels.COLUMN_ud5_setNotes, ud5_setNotes);
            values.put(DocumentModels.COLUMN_ud5_setFinishLimitDate, ud5_setFinishLimitDate);
            values.put(DocumentModels.COLUMN_ud5_setBookDate, ud5_setBookDate);
            values.put(DocumentModels.COLUMN_ud5_isToday, ud5_isToday);
            values.put(DocumentModels.COLUMN_status, status);

            // insert row
            long id = db.insert(DocumentModels.TABLE_NAME, null, values);

            // close db connection
            db.close();

            // return newly inserted row id
            return true;
        }

        public int updateRatingAndTransaction(String role) {
            /* Get Latest Rating & Transaction */
                SQLiteDatabase dbGet = this.getReadableDatabase();
                Cursor cursor = dbGet.query(UserModels.TABLE_NAME,
                        new String[]{
                                UserModels.COLUMN_RATING,
                                UserModels.COLUMN_TRANSACTION
                        },
                        UserModels.COLUMN_ROLE + "=?",
                        new String[]{ role }, null, null, null, null);

                if (cursor != null)
                    cursor.moveToFirst();

                double currentRating = cursor.getDouble(cursor.getColumnIndex(UserModels.COLUMN_RATING));
                double currentTransction = cursor.getInt(cursor.getColumnIndex(UserModels.COLUMN_TRANSACTION));

            /* Update Latest Rating & Transaction */
                SQLiteDatabase dbSet = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(UserModels.COLUMN_RATING, currentRating + 1);
                values.put(UserModels.COLUMN_TRANSACTION, currentTransction + 1);

                // updating row
                return dbSet.update(UserModels.TABLE_NAME, values, UserModels.COLUMN_ROLE + " = ?",
                        new String[]{ role });
        }

        public List<DocumentModels> getDocumentHistoryList() {
            List<DocumentModels> documents = new ArrayList<>();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + DocumentModels.TABLE_NAME + " ORDER BY " +
                    DocumentModels.COLUMN_ud5_setBookDate + " DESC";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    DocumentModels doc = new DocumentModels();

                    doc.setDocument_id(cursor.getInt(cursor.getColumnIndex(DocumentModels.COLUMN_id)));
                    doc.setMerchant_role(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_role)));
                    doc.setUd1_curLocTitle(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud1_curLocTitle)));
                    doc.setUd1_curLocCode(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud1_curLocCode)));
                    doc.setUd2_curMerTitle(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud2_curMerTitle)));
                    doc.setUd3_curService(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud3_curService)));
                    doc.setUd4_curSubService(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud4_curSubService)));
                    doc.setUd4_setBuildQuality(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud4_setBuildQuality)));
                    doc.setUd4_setPrintedPage(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud4_setPrintedPage)));
                    doc.setUd4_setSidesOfPrint(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud4_setSidesOfPrint)));
                    doc.setUd4_setPaperSize(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud4_setPaperSize)));
                    doc.setUd4_setPaperMargin(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud4_setPaperMargin)));
                    doc.setUd4_setOrientation(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud4_setOrientation)));
                    doc.setUd4_setPagePerSheet(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud4_setPagePerSheet)));
                    doc.setUd4_setBaseColor(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud4_setBaseColor)));
                    doc.setUd5_isToday(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud5_setDocName)));
                    doc.setUd5_setDocName(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud5_setAttachFileDir)));
                    doc.setUd5_setAttachFileDir(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud5_setNotes)));
                    doc.setUd5_setNotes(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud5_setFinishLimitDate)));
                    doc.setUd5_setFinishLimitDate(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud5_setBookDate)));
                    doc.setUd5_setBookDate(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_ud5_isToday)));
                    doc.setStatus(cursor.getString(cursor.getColumnIndex(DocumentModels.COLUMN_status)));

                    documents.add(doc);
                } while (cursor.moveToNext());
            }

            // close db connection
            db.close();

            // return notes list
            return documents;
        }
    /* End : Upload Document */

//    public List<UserModels> getAllUsers() {
//        List<UserModels> notes = new ArrayList<>();
//
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + UserModels.TABLE_NAME + " ORDER BY " +
//                UserModels.COLUMN_TIMESTAMP + " DESC";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                UserModels note = new UserModels();
//                note.setId(cursor.getInt(cursor.getColumnIndex(UserModels.COLUMN_ID)));
//                note.setNote(cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_NOTE)));
//                note.setTimestamp(cursor.getString(cursor.getColumnIndex(UserModels.COLUMN_TIMESTAMP)));
//
//                notes.add(note);
//            } while (cursor.moveToNext());
//        }
//
//        // close db connection
//        db.close();
//
//        // return notes list
//        return notes;
//    }
//
//    public int getUsersCount() {
//        String countQuery = "SELECT  * FROM " + UserModels.TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//        cursor.close();
//
//
//        // return count
//        return count;
//    }
//

//
//    public void deleteUser(UserModels note) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(UserModels.TABLE_NAME, UserModels.COLUMN_ID + " = ?",
//                new String[]{String.valueOf(note.getId())});
//        db.close();
//    }

}
