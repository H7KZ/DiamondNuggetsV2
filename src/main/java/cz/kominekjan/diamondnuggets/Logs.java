package cz.kominekjan.diamondnuggets;

import java.io.PrintWriter;
import java.io.StringWriter;

import static cz.kominekjan.diamondnuggets.DiamondNuggets.Logger;

public class Logs {
    public static void log(String msg) {
        Logger().info(msg);
    }

    public static void err(String msg) {
        Logger().warning(msg);
    }

    public static void err(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        for (String line : sw.toString().split("\n")) {
            Logger().severe(line);
        }
    }
}
