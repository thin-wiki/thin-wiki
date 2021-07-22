//package wiki.thin.core.restart;
//
//import oshi.PlatformEnum;
//import oshi.SystemInfo;
//import oshi.software.os.OSProcess;
//
//import java.io.IOException;
//import java.util.Optional;
//
///**
// * @author beldon
// */
//public class ApplicationRestart {
//    private static final char NUL_CHAR = '\00';
//    private static final char SPACE = '\u0020';
//
//    private ApplicationRestart() {
//
//    }
//
//    /**
//     * restart app
//     */
//    public static void restartApp() {
//        Optional<OSProcess> appProcess = getAppProcess();
//        OSProcess raw = appProcess.orElseThrow(() -> new RestartException("Couldn't identify the process by PID"));
//        try {
//            Runtime.getRuntime().exec(getCommandLine(raw));
//        } catch (IOException e) {
//            throw new RestartException("process exec fail.", e);
//        }
//        System.exit(0);
//    }
//
//    private static Optional<OSProcess> getAppProcess() {
//        var operatingSystem = new SystemInfo().getOperatingSystem();
//        var currentPID = operatingSystem.getProcessId();
//        return Optional.ofNullable(operatingSystem.getProcess(currentPID));
//    }
//
//    private static String getCommandLine(OSProcess osProcess) {
//        String commandLine = osProcess.getCommandLine();
//        if (commandLine == null || commandLine.trim().isEmpty()) {
//            throw new RestartException("Couldn't retrieve command-line (it's empty or null)");
//        }
//        final PlatformEnum platform = SystemInfo.getCurrentPlatform();
//        if (PlatformEnum.LINUX.equals(platform) || PlatformEnum.MACOS.equals(platform)) {
//            commandLine = commandLine.replace(NUL_CHAR, SPACE);
//        }
//        return commandLine;
//    }
//}
