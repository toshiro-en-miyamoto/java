## Package java.nio.file in this tutorial

* 17 - Path.getNameCount(), getName(), getFileName()

* 21 - Files.copy()

* 10 - Files.readAllLines(), Files.lines(), Path.get()

* 24 - Files.readAttributes(), Files.walk(), try-with-resources

* 12 - try-catch, a single handler for multiple exceptions
* 26 - AutoCloseable, try-with-resources

* // tricky 29 - InputStreamReader, ready(), skip(), read(), try-with-resources
* // tricky 44 - Files.readAllLines()
* // tricky 65 - BufferedReader.lines(), ready(), FileReader, try-with-resources
* // tricky 67 - Path.resolve()
* // not NIO.2 70 - FileReader.read(), try-with-resources


### Path and Paths

The Path class, introduced in the Java SE 7 release is one of the primary entry points of the java.nio.file package. As its name implies, the Path class is a programmatic representation of a path in the file system. A Path object contains the file name and directory list used to construct the path. It is also used to examine, locate, and manipulate files.

The file or directory corresponding to the Path might not exist. You can create a Path instance and manipulate it in various ways: you can append to it, extract pieces of it, compare it to another path. At the appropriate time, you can use the methods in the Files class to check the existence of the file corresponding to the Path, create the file, open it, delete it, change its permissions, and so on.

This section addresses Path methods, sometimes called **syntactic** operations, because they operate on the path itself and don't access the file system. This section covers the following:

* Creating a Path
* Retrieving Information About a Path
* Removing Redundancies from a Path
* Converting a Path
* Joining Two Paths
* Creating a Path Between Two Paths
* Comparing Two Paths

#### Creating a Path

You can easily create a Path object by using one of the following **get()** methods from the **Paths** helper class:

    Path p1 = Paths.get("/tmp/foo");
    Path p2 = Paths.get("/tmp", "foo");

The Paths.get() method is shorthand for the following code:

    Path p = FileSystems.getDefault().getPath("/users/sally");

### Files

    public static InputStream newInputStream(Path path, OpenOption... options) throws IOException
    public static OutputStream newOutputStream(Path path, OpenOption... options) throws IOException
    public static BufferedReader newBufferedReader(Path path, Charset cs) throws IOException
    public static BufferedWriter newBufferedWriter(Path path, Charset cs, OpenOption... options) throws IOException
    public static BufferedReader newBufferedReader(Path path) throws IOException
    public static BufferedWriter newBufferedWriter(Path path, OpenOption... options) throws IOException
    
    public static byte[] readAllBytes(Path path) throws IOException
    public static List<String> readAllLines(Path path, Charset cs) throws IOException
    public static List<String> readAllLines(Path path) throws IOException
    public static Stream<String> lines(Path path, Charset cs) throws IOException
    public static Stream<String> lines(Path path) throws IOException
    
    public static Path write(Path path, byte[] bytes, OpenOption... options) throws IOException
    public static Path write(Path path, Iterable<? extends CharSequence> lines, Charset cs, OpenOption... options) throws IOException
    public static Path write(Path path, Iterable<? extends CharSequence> lines, OpenOption... options) throws IOException
    
    public static DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException
    public static DirectoryStream<Path> newDirectoryStream(Path dir, String glob) throws IOException
    public static Stream<Path> list(Path dir) throws IOException

### Metadata
### Access Methods
### Links
### Walking the File Tree
### Finding Files
### Watching a Directory for Changes
### Other Useful Methods
### Legacy File I/O Code

(End of BasicIOb)
