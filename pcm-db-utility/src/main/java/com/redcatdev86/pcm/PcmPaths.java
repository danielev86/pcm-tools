package com.redcatdev86.pcm;

import java.nio.file.Path;

public final class PcmPaths {

    private PcmPaths() {}

    public static Path pcmCloudDir(String gameVersion, String pcmUser) {
        // C:\Users\<winUser>\AppData\Roaming\Pro Cycling Manager <ver>\Cloud\<pcmUser>\
        String winUser = System.getProperty("user.name");
        return Path.of("C:\\Users", winUser, "AppData", "Roaming",
                "Pro Cycling Manager " + gameVersion, "Cloud", pcmUser);
    }

    public static Path cdbFile(String gameVersion, String pcmUser, String careerFileNameNoExt) {
        return pcmCloudDir(gameVersion, pcmUser).resolve(careerFileNameNoExt + ".cdb");
    }

    public static Path sqliteExportedFile(String gameVersion, String pcmUser, String careerFileNameNoExt) {
        return pcmCloudDir(gameVersion, pcmUser).resolve(careerFileNameNoExt + ".sqlite");
    }
}