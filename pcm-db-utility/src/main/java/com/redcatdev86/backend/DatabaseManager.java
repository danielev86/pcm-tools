package com.redcatdev86.backend;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.prefs.Preferences;

public final class DatabaseManager {

    private static final String PREF_KEY_DB_PATH = "db.path";
    private static final Preferences PREFS = Preferences.userNodeForPackage(DatabaseManager.class);

    private static volatile Path dbPath;

    private DatabaseManager() {}

    /** Imposta il DB corrente e lo salva nelle preferences */
    public static void setDatabasePath(Path path) {
        Objects.requireNonNull(path, "path");
        dbPath = path.toAbsolutePath().normalize();
        PREFS.put(PREF_KEY_DB_PATH, dbPath.toString());
    }

    /** Prova a caricare il path da preferences (se esiste e il file esiste) */
    public static boolean loadDatabasePathFromPreferences() {
        String saved = PREFS.get(PREF_KEY_DB_PATH, null);
        if (saved == null || saved.isBlank()) return false;

        Path p = Path.of(saved);
        if (!Files.exists(p)) return false;

        dbPath = p.toAbsolutePath().normalize();
        return true;
    }

    public static Path getDatabasePath() {
        return dbPath;
    }

    public static boolean isConfigured() {
        return dbPath != null && Files.exists(dbPath);
    }

    public static Connection getConnection() {
        if (!isConfigured()) {
            throw new IllegalStateException("Database path not configured. Please select/export a database.");
        }
        try {
            String url = "jdbc:sqlite:" + dbPath.toString();
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException("Cannot open SQLite connection for: " + dbPath, e);
        }
    }
}