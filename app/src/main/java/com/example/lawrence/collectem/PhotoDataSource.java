package com.example.lawrence.collectem;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Evanna on 2015-03-18.
 */
public class PhotoDataSource {
    private SQLiteDatabase db;
    private DbHandler dbHelper;
    private String[] allColumns = {DbHandler.KEY_ID, DbHandler.TITLE,
            DbHandler.IMAGE, DbHandler.DESC};
    private String[] picture = {DbHandler.KEY_ID, DbHandler.IMAGE};

    public PhotoDataSource(Context context) {
        dbHelper = new DbHandler(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public PhotoData addPic(String title, String imagePath, String desc){
        ContentValues values = new ContentValues();
        values.put(DbHandler.TITLE, title);
        values.put(DbHandler.IMAGE, imagePath);
        values.put(DbHandler.DESC, desc);

        long insertId = db.insert(DbHandler.TABLE_NAME, null, values);
        Cursor cursor = db.query(DbHandler.TABLE_NAME, allColumns, DbHandler.KEY_ID + " = " +
                insertId, null, null, null, null);

        cursor.moveToFirst();
        PhotoData newPhoto = cursorToPhoto(cursor);
        cursor.close();
        return newPhoto;
    }

    public void deletePhoto(PhotoData photo) {
        long id = photo.getId();
        System.out.println("Comment deleted with id: " + id);
        db.delete(DbHandler.TABLE_NAME, DbHandler.KEY_ID  + " = " + id, null);
    }

    public List<PhotoData> getAllPics() {
        List<PhotoData> photoList = new ArrayList<PhotoData>();

        Cursor cursor = db.query(DbHandler.TABLE_NAME,
                picture, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PhotoData photo = cursorToPhoto(cursor);
            photoList.add(photo);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();
        return photoList;
    }

    public List<String> getPaths() {
        List<String> pathList = new ArrayList<String>();
        String[] pathColumn = {DbHandler.IMAGE};
        Cursor cursor = db.query(DbHandler.TABLE_NAME, pathColumn, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String photoPath = cursorToString(cursor);
            pathList.add(photoPath);
            cursor.moveToNext();
        }

        cursor.close();
        return pathList;
    }

    public List<String> getTitle() {
        List<String> titleList = new ArrayList<String>();
        String[] pathColumn = {DbHandler.IMAGE};
        Cursor cursor = db.query(DbHandler.TABLE_NAME, pathColumn, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String photoPath = cursorToTitle(cursor);
            titleList.add(photoPath);
            cursor.moveToNext();
        }

        cursor.close();
        return titleList;
    }

    private PhotoData cursorToPhoto(Cursor cursor){
        PhotoData photo = new PhotoData();
        photo.setId(cursor.getLong(0));
        //photo.setTitle(cursor.getString(1));
        photo.setImage(cursor.getString(1));
        // photo.setDesc(cursor.getString(3));
        return photo;
    }

    private String cursorToTitle(Cursor cursor){
        String path = new String();
        path = cursor.getString(cursor.getColumnIndex(DbHandler.TITLE));
        return path;
    }

    private String cursorToString(Cursor cursor){
        String path = new String();
        path = cursor.getString(cursor.getColumnIndex(DbHandler.IMAGE));
        return path;
    }
}
