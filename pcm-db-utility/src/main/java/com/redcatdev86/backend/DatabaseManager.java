package com.redcatdev86.backend;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DB_FOLDER = "data";
    private static final String DB_NAME = "carriera262.sqlite"; // metti qui il tuo file
    private static final String URL = "jdbc:sqlite:" + DB_FOLDER + "/" + DB_NAME;

    static {
        ensureDatabaseFolderExists();
    }

    private static void ensureDatabaseFolderExists() {
        File folder = new File(DB_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}