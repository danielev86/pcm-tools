package com.redcatdev86.pcm;

import java.util.prefs.Preferences;

public final class PcmPreferences {

    // nodo fisso, indipendente dal package della classe che lo usa
    private static final Preferences PREFS = Preferences.userRoot().node("com/redcatdev86/pcm");

    private static final String KEY_GAME = "pcm.gameVersion";
    private static final String KEY_USER = "pcm.user";
    private static final String KEY_CAREER = "pcm.career";

    private PcmPreferences() {}

    public static void setAll(String gameVersion, String pcmUser, String careerNoExt) {
        PREFS.put(KEY_GAME, gameVersion == null ? "" : gameVersion.trim());
        PREFS.put(KEY_USER, pcmUser == null ? "" : pcmUser.trim());
        PREFS.put(KEY_CAREER, careerNoExt == null ? "" : careerNoExt.trim());
    }

    public static String getGameVersion() { return PREFS.get(KEY_GAME, ""); }
    public static String getPcmUser()     { return PREFS.get(KEY_USER, ""); }
    public static String getCareer()      { return PREFS.get(KEY_CAREER, ""); }

    public static boolean isComplete() {
        return !getGameVersion().isBlank() && !getPcmUser().isBlank() && !getCareer().isBlank();
    }
}