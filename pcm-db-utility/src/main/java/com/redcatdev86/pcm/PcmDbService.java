package com.redcatdev86.pcm;

import com.redcatdev86.backend.DatabaseManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class PcmDbService {

    private final Path exporterDir;   // ./exporter
    private final Path dbDataDir;     // ./db_data

    public PcmDbService(Path exporterDir, Path dbDataDir) {
        this.exporterDir = exporterDir;
        this.dbDataDir = dbDataDir;
    }

    public Path exportAndAttach(String gameVersion, String pcmUser, String careerFileNameNoExt)
            throws IOException, InterruptedException {

        Path cdb = PcmPaths.cdbFile(gameVersion, pcmUser, careerFileNameNoExt);
        if (!Files.exists(cdb)) throw new IOException("CDB not found: " + cdb);

        SQLiteExporterRunner.exportCdbToSqlite(exporterDir, gameVersion, pcmUser, careerFileNameNoExt);

        Path exportedSqlite = PcmPaths.sqliteExportedFile(gameVersion, pcmUser, careerFileNameNoExt);
        if (!Files.exists(exportedSqlite)) throw new IOException("Exported sqlite not found: " + exportedSqlite);

        Files.createDirectories(dbDataDir);

        Path destDb = dbDataDir.resolve(careerFileNameNoExt + ".db");
        Files.copy(exportedSqlite, destDb, StandardCopyOption.REPLACE_EXISTING);

        DatabaseManager.setDatabasePath(destDb);
        return destDb;
    }

    // SAVE ALL: db locale -> copia come .sqlite nella cartella PCM -> -import (senza estensione)
    public void saveAllToCdb(String gameVersion, String pcmUser, String careerFileNameNoExt)
            throws IOException, InterruptedException {

        if (!DatabaseManager.isConfigured()) {
            throw new IllegalStateException("No database configured. Export/load a DB first.");
        }

        Path currentDb = DatabaseManager.getDatabasePath();
        if (currentDb == null || !Files.exists(currentDb)) {
            throw new IllegalStateException("Current DB not found: " + currentDb);
        }

        // 1) Copia DB locale -> cartella PCM come .sqlite
        Path pcmSqlite = PcmPaths.sqliteExportedFile(gameVersion, pcmUser, careerFileNameNoExt);
        Files.copy(currentDb, pcmSqlite, StandardCopyOption.REPLACE_EXISTING);

        // 2) Import sqlite -> cdb (runner ora usa path senza estensione)
        SQLiteExporterRunner.importSqliteToCdb(exporterDir, gameVersion, pcmUser, careerFileNameNoExt);
    }
}