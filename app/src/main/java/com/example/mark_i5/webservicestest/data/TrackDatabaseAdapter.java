package com.example.mark_i5.webservicestest.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mark-i5 on 23/08/2014.
 */
public class TrackDatabaseAdapter {
    public TrackDatabaseHelper dbHelper;
    public static final String LOGTAG = "TrackDatabaseHelper";



    public TrackDatabaseAdapter(Context context){
        this.dbHelper = new TrackDatabaseHelper(context);

    }
    public void insertTrack (Track track, String country){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TrackDatabaseHelper.ARTIST_NAME, track.getArtist());
        contentValues.put(TrackDatabaseHelper.TRACK_NAME, track.getName());
        contentValues.put(TrackDatabaseHelper.ARTIST_URL, track.getArtistUrl());
        contentValues.put(TrackDatabaseHelper.TRACK_URL, track.getTrackUrl());
        contentValues.put(TrackDatabaseHelper.COUNTRY, country);
        long id = db.insert(TrackDatabaseHelper.TABLE_NAME, null,contentValues);
       // long id  = db.insertWithOnConflict(TrackDatabaseHelper.TABLE_NAME,null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d(LOGTAG, "track_id: " +id);

    }



    class TrackDatabaseHelper extends SQLiteOpenHelper {
        private Context context;

        private static final String DATABASE_NAME = "tracks.db";
        private static final String TABLE_NAME = "track_table";
        private static final int DATABASE_VERSION = 1;


        private static final String METRO = "metro";
        private static final String COUNTRY = "country";
        private static final String ARTIST_NAME = "artist_name";
        private static final String TRACK_NAME = "track_name";
        private static final String TRACK_URL = "track_url";
        private static final String ARTIST_URL = "artist_url";

        private static final String TID = "_id";
        private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +" (" +TID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TRACK_NAME +" VARCHAR(255), "+ ARTIST_NAME + " VARCHAR(255), "+ COUNTRY + " VARCHAR(255), "+ METRO+ " VARCHAR(255), "+
                TRACK_URL + " VARCHAR(255), "+ ARTIST_URL +" VARCHAR(255))";

      /*  , UNIQUE ("+ ARTIST_NAME+", " + TRACK_NAME+
                "))";
*/
       /* , UNIQUE ("+ ARTIST_NAME+", " + TRACK_NAME+
                ") ON CONFLICT FAIL);";
*/
        private static final String DROP_TABLE = "DROP TABLE "+TABLE_NAME;


        public TrackDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOGTAG, CREATE_TABLE);

            try {
                sqLiteDatabase.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            Log.d(LOGTAG, "upgrading database");
            sqLiteDatabase.execSQL(DROP_TABLE);
            dbHelper.onCreate(sqLiteDatabase);
        }

    }



}
