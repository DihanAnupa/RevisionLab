package com.example.revisionlab.Database;

import android.provider.BaseColumns;

public class User {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private User() {}

    /* Inner class that defines the table contents */
    public static class UserT implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_1 = "Name";
        public static final String COLUMN_2 = "Password";
        public static final String COLUMN_3 = "Type";

    }
}
