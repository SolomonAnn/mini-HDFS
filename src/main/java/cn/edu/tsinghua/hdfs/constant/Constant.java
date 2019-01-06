package cn.edu.tsinghua.hdfs.constant;

/**
 * @author: An
 * @description: some public constants
 */

public class Constant {
    public static String NAMENODE_IP;
    public static String DATANODE_IP;
    public static String CLIENT_IP;
    public static int RPC_PORT;
    public static int SOCKET_PORT;

    public static String NAMENODE_PATH = System.getProperty("user.dir");
    public static String DATANODE_PATH = System.getProperty("user.dir");

    public static final String CMD_SUFFIX = ">";
    public static final String CMD_IDENTIFIER = "minihdfs";
    public static final String DIRECTORY_PREFIX = "/";
    public static final String BLOCK_SPACE = "#";
    public static final String ILLEGAL_DIRECTORY = "[\\\\\\\\/:*?\\\"<>|]";
    public static final String NAMENODE_GREETING = "*** Starting namenode...\r\n*** Wait patiently. :)";
    public static final String NAMENODE_START = "*** Namenode starts successfully!";
    public static final String NAMENODE_ERROR = "*** Shutting down namenode.. (JVM is shutting down.) :(";
    public static final String NAMENODE_SHUTDOWN = "*** Done.";
    public static final String CLIENT_GREETING = "Starting client...\r\nWait patiently. :)";
    public static final String INVALID_COMMAND = "Please input correct commands!";
    public static final String INVALID_FILENAME_OR_DIRECTORY = "Invalid filename or directory!";

    public static final String CD = "cd";
    public static final String LS = "ls";
    public static final String MKDIR = "mkdir";
    public static final String GET = "get";
    public static final String PUT = "put";
    public static final String RM = "rm";

    public static final long BLOCK_SIZE = 128 * 1024 * 1024; // 128MB
    public static int REPLICATION_FACTOR;
    public static final int HEARTBEAT = 3000;
    public static final int BYTES_SIZE = 4096;

    // block: datanode + src + offset + length
    public static final String FSIMAGE = "FsImage";
    // TODO: record some operations
    public static final String EDITSLOG = "EditsLog";
    // datanode: ip + rpcPort + socketPort
    public static final String SLAVESLIST = "SlavesList";
    public static final String FILESLIST = "FilesList";
}
