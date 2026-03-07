package com.redcatdev86.backend;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseManager {

    private static final String DB_RELATIVE_PATH = "db/database.sqlite";

    private DatabaseManager() {
    }

    public static Connection getConnection() throws SQLException {

        Path dbPath = Path.of(DB_RELATIVE_PATH).toAbsolutePath().normalize();

        if (!Files.exists(dbPath)) {
            throw new IllegalStateException("Database non trovato: " + dbPath);
        }

        String url = "jdbc:sqlite:" + dbPath;

        return DriverManager.getConnection(url);
    }

    public static String getDatabaseAbsolutePath() {
        return Path.of(DB_RELATIVE_PATH).toAbsolutePath().normalize().toString();
    }
}