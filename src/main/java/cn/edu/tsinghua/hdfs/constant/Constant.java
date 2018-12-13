package cn.edu.tsinghua.hdfs.constant;

/**
 * @author: An
 * @description: some constants
 */

public class Constant {
    public static final String HOST = "127.0.0.1";
    public static int PORT = 1234;

    public static final String CMD_SUFFIX = ">";
    public static final String CMD_IDENTIFIER = "minihdfs";

    public static final String DIRECTORY_PREFIX = "/";

    public static final String CD = "cd";
    public static final String LS = "ls";
    public static final String MKDIR = "mkdir";
    public static final String GET = "get";
    public static final String PUT = "put";
    public static final String CAT = "cat";
    public static final String RM = "rm";
    public static final String RMR = "rmr";

    public static final String NAMENODE_GREETING = "*** Starting namenode...\r\n*** Wait patiently. :)";
    public static final String NAMENODE_START = "*** Namenode starts successfully!";
    public static final String NAMENODE_ERROR = "*** Shutting down namenode.. (JVM is shutting down.) :(";
    public static final String NAMENODE_SHUTDOWN = "*** Done.";
    public static final String CLIENT_GREETING = "Starting client...\r\nWait patiently. :)";
    public static final String INVALID_COMMAND = "Please input correct commands!";

    public static final int BLOCK_SIZE = 64 * 1024 * 1024; // 64MB
}
