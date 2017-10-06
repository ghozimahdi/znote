package com.blankdev.znote.model;

/**
 * Created by knalb on 14/07/17.
 */

public class TableItems {
    public static final String NAME = TableItems.class.getSimpleName().toLowerCase();
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String NOTE = "note";
    public static final String DATE = "mdate";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NAME +
                    " ( " +
                    _ID + " integer primary key autoincrement, " +
                    TITLE + " text ," + NOTE + " text," + DATE + " date" +
                    " ); ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NAME;
    public static String[] Columns = new String[]{_ID,TITLE,NOTE,DATE};
}
