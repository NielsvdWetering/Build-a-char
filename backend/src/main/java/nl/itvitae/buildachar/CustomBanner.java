package nl.itvitae.buildachar;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

public class CustomBanner implements Banner {
    private static final String mantis = "\u001B[38;2;125;206;130m";
    private static final String blush = "\u001B[38;2;227;101;136m";
    private static final String jordyBlue = "\u001B[38;2;154;196;248m";

    private static final String BANNER =
            jordyBlue +
                    "\nSuper duper amazing development by Michiel, Niels & Laila\n\n" +
                    blush +





                    "    ____        _ __    __            ________              \n"+
                    "   / __ )__  __(_) /___/ /  ____ _   / ____/ /_  ____ ______\n"+
                    "  / __  / / / / / / __  /  / __ `/  / /   / __ \\/ __ `/ ___/\n"+
                    " / /_/ / /_/ / / / /_/ /  / /_/ /  / /___/ / / / /_/ / /    \n"+
                    "/_____/\\__,_/_/_/\\__,_/   \\__,_/   \\____/_/ /_/\\__,_/_/     \n"+



    jordyBlue +
                    "Don't forget to have fun!       Spring version 3.2.5\n\n" +
                    mantis;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, java.io.PrintStream out) {
        out.println(BANNER);
    }
}