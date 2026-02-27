package com.redcatdev86.pcm;

import java.io.IOException;
import java.nio.file.Path;

public final class SQLiteExporterRunner {

    private SQLiteExporterRunner() {}

    private static Path resolveExe(Path exporterDir) {
        Path exe = exporterDir.resolve("SQLiteExporter.exe");
        if (!exe.toFile().exists()) exe = exporterDir.resolve("SQLiteExporter");
        if (!exe.toFile().exists()) {
            throw new RuntimeException("SQLiteExporter not found in: " + exporterDir);
        }
        return exe;
    }

    // (Lasciamo export com'era: usa .cdb perché spesso l’export lo richiede)
    public static void exportCdbToSqlite(Path exporterDir,
                                         String gameVersion,
                                         String pcmUser,
                                         String careerFileNameNoExt)
            throws IOException, InterruptedException {

        Path exe = resolveExe(exporterDir);

        String relativeArg =
                "Pro Cycling Manager " + gameVersion +
                        "\\Cloud\\" + pcmUser +
                        "\\" + careerFileNameNoExt + ".cdb";

        ProcessBuilder pb = new ProcessBuilder(exe.toString(), "-export", relativeArg);
        pb.directory(exporterDir.toFile());
        pb.inheritIO();

        int code = pb.start().waitFor();
        if (code != 0) throw new RuntimeException("Export failed. Exit code=" + code);
    }

    // ✅ FIX: IMPORT usa path SENZA estensione
    public static void importSqliteToCdb(Path exporterDir,
                                         String gameVersion,
                                         String pcmUser,
                                         String careerFileNameNoExt)
            throws IOException, InterruptedException {

        Path exe = resolveExe(exporterDir);

        // ✅ esattamente come chiedi tu:
        // "Pro Cycling Manager 2025\Cloud\[login]\Career_1"
        String relativeArg =
                "Pro Cycling Manager " + gameVersion +
                        "\\Cloud\\" + pcmUser +
                        "\\" + careerFileNameNoExt;

        ProcessBuilder pb = new ProcessBuilder(exe.toString(), "-import", relativeArg);
        pb.directory(exporterDir.toFile());
        pb.inheritIO();

        int code = pb.start().waitFor();
        if (code != 0) throw new RuntimeException("Import failed. Exit code=" + code);
    }
}