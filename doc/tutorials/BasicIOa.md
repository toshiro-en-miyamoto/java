## Package java.io in this tutorial

An **I/O Steam** represents an input source or an output destination. A stream can represent many different kinds of sources and destinations, including disk files, devices, other programs, and memory arrays.

Streams support many different kinds of data, including bytes, primitive data types, localized characters, and objects. Some streams simply pass on data; others manipulate and transform the data in useful ways.

No matter how they work internally, all streams present the same simple model to programs that use them: A stream is a sequence of data.

A program uses an **input stream** to read data from a source, one item at a time. A program uses an **output stream** to write data to a destination, one item at a time.

### Byte Streams

Programs use **byte streams** to perform input and output of 8-bit bytes. All byte stream classes are descended from **InputStream** and **OutputStream**.

InputStream is the superclass of all classes representing an input stream of bytes. Applications that need to define a subclass of InputStream must always provide a method that returns the next byte of input.

    java.io.InputStream
     1:  public abstract class InputStream implements Closeable {
     2:     public abstract int read() throws IOException;
     3:     public int read(byte[] b) throws IOException { ... }
     4:     public int read(byte[] b, int off, int len) throws IOException { ... }
     5:     public long skip(long n) throws IOException { ... }
     6:     public int available() throws IOException { ... }
     7:     public void close() throws IOException { ... }
    ??  }
     
    java.io.Closeable
     1:  public interface Closeable extends AutoCloseable {
     2:     void close() throws IOException;
     3:  }
     
    java.lang.AutoCloseable
     1:  public interface AutoCloseable {
     2:     void close() throws Exception;
     3:  }

OutputStream is the superclass of all classes representing an output stream of bytes. An output stream accepts output bytes and sends them to some sink. Applications that need to define a subclass of OutputStream must always provide at least a method that writes one byte of output.

    java.io.OutputStream
     1:  public abstract class OutputStream implements Closeable, Flushable {
     2:     public abstract void write(int b) throws IOException;
     3:     public void write(byte[] b) throws IOException { ... }
     4:     public void write(byte[] b, int off, int len) throws IOException { ... }
     5:     public void flush() throws IOException { ... }
     6:     public void close() throws IOException { ... }
    ??:  }
     
    java.io.Flushable
     1:  public interface Flushable {
     2:     void flush() throws IOException;
     3:  }

A subclass of InputStream that handles file I/O is **FileInputStream**. A FileInputStream obtains input bytes from a file in a file system. FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.

    java.io.FileInputStream
     1:  public class FileInputStream extends InputStream {
     2:     public FileInputStream(String name) throws FileNotFoundException { ... }
     3:     public FileInputStream(File file) throws FileNotFoundException { ... }
     4:     public FileInputStream(FileDescriptor fd) { ... }
     5:
     6:     public int read() throws IOException { ... }
     7:     public int read(byte[] b) throws IOException { ... }
     8:     public int read(byte[] b, int off, int len) throws IOException { ... }
     9:     public long skip(long n) throws IOException { ... }
    10:     public int available() throws IOException { ... }
    11:     public void close() throws IOException { ... }
    ??:  }

A subclass of OutputStream that handles file I/O is **FileOutputStream**. A FileOutputStream is an output stream for writing data to a File or to a File Descriptor. FileOutputStream is meant for writing streams of raw bytes such as image data. For writing streams of characters, consider using FileWriter.

    java.io.FileOutputStream
    // methods with ** as the line number are inherited from super classes
     1:  public class FileOutputStream extends OutputStream {
     2:     public FileOutputStream(String name) throws FileNotFoundException { ... }
     3:     public FileOutputstream(String name, boolean append) throws FileNotFoundException { ... }
     4:     public FileOutputStream(File file) throws FileNotFoundException { ... }
     5:     public FileOutputStream(File file, boolean append) throws FileNotFoundException { ... }
     6:     public FileOutputStream(FileDescriptor fd) { ... }
     7:
     8:     public void write(int b) throws IOException { ... }
     9:     public void write(byte[] b) throws IOException { ... }
    10:     public void write(byte[] b, int off, int len) throws IOException { ... }
    **:     public void flush() throws IOException;
    11:     public void close() throws IOException { ... }
    ??:  }

**Sample Code:**

The sample code produce a file by copying another file. It demonstrates how you can read from and write to byte streams.

    se.tutorials.basicio.CopyBytes
     9:     private static final String INPUT_FILENAME  = ... ;
    10:     private static final String OUTPUT_FILENAME = ... ;
    ??:
    13:        FileInputStream  in  = null;
    14:        FileOutputStream out = null;
    15:  
    16:        try {
    17:           in  = new FileInputStream(INPUT_FILENAME);
    18:           out = new FileOutputStream(OUTPUT_FILENAME, false);
    19:           int b;
    20:           while ((b = in.read()) != -1) {
    21:              out.write(b);
    22:           }
    23:        } catch (FileNotFoundException e) {
    24:           System.err.println("A file for CopyBytes not found.");
    25:        } catch (IOException e) {
    26:           System.err.println("Failed to copy bytes.");
    27:        } finally {
    28:           try {
    29:              if (in  != null) { in.close(); }
    30:              if (out != null) { out.close(); }
    31:           } catch (IOException e) {
    32:              System.err.println("Failed to close an output stream.");
    33:           }
    34:        }

**Sample Code Explained:**

The sample code spends most of its time in a simple loop that reads the input stream and writes the output stream, one byte at a time. The code seems like a normal program, but it actually represents a kind if low-level I/O that you should avoid. When reading files that contain character data, the best approach is to use **character stream**, as discussed in the next section.

Definitions of methods used in the sample code follows:

    java.io.FileInputStream
     2:     public FileInputStream(String name) throws FileNotFoundException { ... }
     6:     public int read() throws IOException { ... }
    11:     public void close() throws IOException { ... }

* FileInputStream(String name) - Creates a FileInputstream by opening a connection to an actual file, the file names by the path name 'name' in the file system. If the named file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading then a FileNotFoundException is thrown.
* read() - Reads a byte of data from this input stream. This method blocks if no input is yet available. Returns the next byte of data, or -1 if the end of the file is reached.
* close() - Closes this file input stream and releases any system resources associated with the stream.

    java.io.FileOutputStream
     3:     public FileOutputstream(String name, boolean append) throws FileNotFoundException { ... }
     8:     public void write(int b) throws IOException { ... }
    11:     public void close() throws IOException { ... }

* FileOutputStream(String name, boolean append) - Creates a file output stream to write to the file with the specified name. If the second argument is true, then bytes will be written to the end of the file rather than the beginning. If the file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason then a FileNotFoundException is thrown.
* write(int b) - Writes the specified byte to this file output stream.
* close() - Closes this file output stream and release any system resources associated with this stream.


### Character Streams

The Java platform stores character values using Unicode conventions. In Japan, popular character sets include Windows-31J (also known as Shift-JIS) and UTF-8.

Character stream I/O automatically translates this internal format to and from the local character set. All character stream classes are descended from **Reader** and **Writer**.

    java.io.Reader
     1:  public abstract class Reader implements Readable, Closeable {
     2:     public int read() throws IOException { ... }
     3:     public int read(char[] cbuf throws IOException { ... }
     4:     public abstract int read(char[] cbuf, int off, int len) throws IOException;
     5:     public int read(CharBuffer target) throws IOException { ... }
     6:     public long skip(long n) throws IOException { ... }
     7:     public abstract void close() throws IOException;
    ??:  }
     
    java.lang.Readable
     1:  public interface Readable {
     2:     int read(CharBuffer cb) throws IOException;
     3:  }
     
    java.io.Writer
     1:  public abstract class Writer implements Appendable, Closeable, Flushable {
     2:     public void write(int c) throws IOException { ... }
     3:     public void write(char[] cbuf throws IOException { ... }
     4:     public abstract void write(char[] cbuf, int off, int len) throws IOException;
     5:     public void write(String str) throws IOException { ... }
     6:     public void write(String str, int off, int len) throws IOException { ... }
     7:     public abstract void flush() throws IOException;
     8:     public abstract void close() throws IOException;
    ??:  }
    
    java.lang.Appendable
     1:  public interface Appendable {
     2:     Appendable append(char c) throws IOException;
     3:     Appendable append(CharSequence csq) throws IOException;
     4:     Appendable append(CharSequence csq, int start, int end) throws IOException;
     5:  }

An **InputStreamReader** is a general-purpose "bridge" from byte streams to character streams: It reads bytes and decodes them into characters using a specified charset. The charset that it uses may be specified by name of my be given explicitly, or the platform's default charset may be accepted. When you need a Reader that handles file I/O, construct an InputStreamReader on a FileInputStream.

    java.io.InputStreamReader
     1:  public class InputStreamReader extends Reader {
     2:     public InputStreamReader(InputStream in) { ... }
     3:     public InputStreamReader(InputStream in, String charsetName) throws UnsupportedEncodingException { ... }
     4:     public InputStreamReader(InputStream in, Charset cs) { ... }
    ??:  }

An **OutputStreamWriter** is a general-purpose "bridge" from character streams to byte streams: Characters written to it are encoded into bytes using a specified charset. The charset that it uses may be specified by name or may be given explicitly, or the platform's default charset may be accepted. When you need a Writer that handles file I/O, construct an OutputStreamWriter on a FileOutputStream.

    java.io.OutputStreamWriter
    // methods with ** as the line number are inherited from super classes
     1:  public class OutputStreamWriter extends Writer {
     2:     public OutputStreamWriter(OutputStream out) { ... }
     3:     public OutputStreamWriter(OutputStream out, String charsetName) throws UnsupportedEncodingException { ... }
     4:     public OutputStreamWriter(OutputStream out, Charset cs) { ... }
    ??:  }

**Note:**
FileReader and FileWriter are convenience classes for reading and writing character files. The constructors of the classes assume that the default character encoding and the default byte-buffer size are appropriate.

**Sample Code:**

When you writing a program that accesses text files, consider specifying the charset of the files by name or explicitly rather than depending upon the platform's default charset. The following sample code, therefore, focuses on InputStreamReader and OutputStreamWriter rather than FileReader and FileWriter.

    se.tutorials.basicio.CopyCharacters
    12:     private static final String INPUT_FILENAME  = ... ;
    13:     private static final String OUTPUT_FILENAME = ... ;
    ??:
    16:        InputStreamReader  reader = null;
    17:        OutputStreamWriter writer = null;
    18:  
    19:        try {
    20:           reader = new InputStreamReader(
    21:                    new FileInputStream(INPUT_FILENAME),
    22:                    "UTF-8");
    23:           writer = new OutputStreamWriter(
    24:                    new FileOutputStream(OUTPUT_FILENAME, false),
    25:                    StandardCharsets.UTF_8);
    26:           int c;
    27:           while ((c = reader.read()) != -1) {
    28:              writer.write(c);
    29:           }
    30:        } catch (FileNotFoundException e) {
    31:           System.err.println("A file for CopyCharacters not found.");
    32:        } catch (IOException e) {
    33:           System.err.println("Failed to copy characters.");
    34:        } finally {
    35:           try {
    36:              if (reader != null ) { reader.close(); }
    37:              if (writer != null ) { writer.close(); }
    38:           } catch (IOException e) {
    39:              System.err.println("Failed to close an output stream.");
    40:           }
    41:        }

**Sample Code Explained:**

CopyCharacters is very similar to CopyBytes. The most important difference is that CopyCharacters uses character streams (InputStreamReader and OutputStreamWriter) that are constructed on byte streams (FileInputStream and FileOutputStream, respectively).

The character streams are "wrappers" for byte streams. Character streams handle translation between characters and bytes, while byte streams perform the physical I/O under the cover.

Definitions of methods used in the sample code follows:

    java.io.InputStreamReader
     3:     public InputStreamReader(InputStream in, String charsetName) throws UnsupportedEncodingException { ... }
     8:     public int read() throws IOException { ... }
    11:     public void close() throws IOException { ... }

* InputStreamReader(InputStream in, String charsetName) - Creates an InputStreamReader that use the named charset.
* read() - Reads a single character. Returns the character read, or -1 if the end of the stream has been reached.
* close() - Closes the stream and releases any system resources associated with it.

    java.io.OutputStreamWriter
     2:     public OutputStreamWriter(OutputStream out, String charsetName) throws UnsupportedEncodingException { ... }
     8:     public void write(int c) throws IOException { ... }
    12:     public void close() throws IOException { ... }

* OutputStreamWriter(OutputStream out, String charsetName) - Create an OutputStreamWriter that uses the named charset.
* write(int c) - Write a single character.
* close() - Closes the stream and releases any system resources associated with it.

## Bytes, Charactecters, and Charset

Notice that both CopyBytes and CopyCharacters use an int variable to read to write from. However, in CopyCharacters, the int variable holds a character value in its last 16 bits; in CopyBytes, the int variable holds a byte value in its last 8 bits.

According to Java Language Specification (JLS) Section 3.1, the Java programming language represents text in sequences of 16-bit code units, using the UTF-16 encoding. A char value, therefore, represents Unicode code units of the UTF-16 encoding. In other words, a char value represents Basic Multilingual Plane (BMP) code points, including the surrogate code points.

**Notes:**
The Unicode Standard allows for characters whose representation requires more than 16 bits, and the range of legal **code points** is U+0000 to U+10FFFF. The set of characters from U+0000 to U+FFFF is referred to as the **Basic Multilingual Plane (BMP)**. Characters whose code points are greater than U+FFFF are called **supplementary characters**. In the UTF-16 representation, supplementary characters are represented as a pair of char values, the first from the **high-surrogates** range, (\uD800-\uDBFF), the second from the **low-surrogates** range (\uDC00-\uDFFF).

An instance of the class **Charset** is a named mapping between sequences of 16-bit Unicode code units and sequences of bytes.
To see which named charsets the Java 8 supports, visit the Supported Encoding site (https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html).

    java.nio.charset.Charset
     1:  public abstract class Charset implements Comparable<Charset> {
     2:     static SortedMap<String, Charset> availableCharsets() { ... }
     3:     String name() { ... }
     4:     boolean isRegistered() { ... }
     5:     Set<String> aliases() { ... }
    ??:  }

You can also find the charsets supported by your system by running the following code:

    AvailableCharset
     8:        Charset.availableCharsets().values()
     9:           .forEach((cs) ->
    10:              System.out.printf("%s,%s,%s%n",
    11:                    cs.name(),
    12:                    cs.isRegistered(),
    13:                    cs.aliases()));

### Buffered Streams

Most of the example we've seen so far use **unbuffered** I/O. This means each read or write request is handled directly by the underlying OS. This can make a program much less efficient, since each such request often triggers disk access that is relatively expensive.

To reduce this kind of overhead, the Java platform implements **buffered** I/O streams. Buffered input streams read data from a memory area known as **buffer**, the native input API is called only when the buffer is empty. Similarly, buffered output streams write data to a buffer, and the native output API is called only when the buffer is full.

A program can convert an unbuffered stream into a buffered stream using the wrapping idiom, where the unbuffered stream object is passed to the constructor for a buffered stream class. There are four buffered stream classes use to wrap unbuffered streams:

    java.io.BufferedInputStream
    // methods with ** as the line number are inherited from super classes
     1:  public class BufferedInputStream extends FilterInputStream {
     2:     public BufferedInputStream(InputStream in) { ... }
     3:     public BufferedInputStream(InputStream in, int size) { ... }
     4:
     5:     public int read() throws IOException { ... }
    **:     public int read(byte[] b) throws IOException;
     6:     public int read(byte[] b, int off, int len) throws IOException { ... }
     7:     public long skip(long n) throws IOException { ... }
     8:     public void close() throws IOException { ... }
    ??:  }
     
    java.io.BufferedOutputStream
    // methods with ** as the line number are inherited from super classes
     1:  public class BufferedOutputStream extends FilterOutputStream {
     2:     public BufferedOutputStream(OutputStream out) { ... }
     3:     public BufferedOutputStream(OutputStream out, int size) { ... }
     4:
     5:     public void write(int b) throws IOException { ... }
    **:     public void write(byte[] b) throws IOException;
     6:     public void write(byte[] b, int off, int len) throws IOException { ... }
     7:     public void flush() throws IOException { ... }
    **:     public void close() throws IOException;
    ??:  }
    
    java.io.BufferedReader
    // methods with ** as the line number are inherited from super classes
     1:  public class BufferedReader extends Reader {
     2:     public BufferedReader(Reader in) { ... }
     3:     public BufferedReader(Reader in, int sz) { ... }
     4:
     5:     public int read() throws IOException { ... }
    **:     public int read(char[] cbuf throws IOException;
     6:     public int read(char[] cbuf, int off, int len) throws IOException { ... }
    **:     public int read(CharBuffer target) throws IOException;
     7:     public String readLine() throws IOException { ... }
     8:     public Stream<String> lines() { ... }
     9:     public long skip(long n) throws IOException { ... }
    10:     public void close() throws IOException { ... }
    ??:  }
    
    java.io.BufferedWriter
    // methods with ** as the line number are inherited from super classes
     1:  public class BufferedWriter extends Writer {
     2:     public BufferedWriter(Writer out) { ... }
     3:     public BufferedWriter(Writer out, int sz) { ... }
     4:
     5:     public void write(int c) throws IOException { ... }
    **:     public void write(char[] cbuf throws IOException;
     6:     public void write(char[] cbuf, int off, int len) throws IOException { ... }
    **:     public void write(String str) throws IOException;
     7:     public void write(String s, int off, len) throws IOException { ... }
     8:     public void newLine() throws IOException { ... }
    **:     public Writer append(char c) throws IOException;
    **:     public Writer append(CharSequence csq) throws IOException;
    **:     public Writer append(CharSequence csq, int start, int end) throws IOException;
     9:     public void flush() throws IOException { ... }
    10:     public void close() throws IOException { ... }
    ??:  }

### Formatting

Stream objects that implement formatting are instances of either **PrintWriter**, a character stream class, or **PrintStream**. a byte stream class. Like all byte and character stream objects, instances of PrintStream and PrintWriter implement a standard set of write methods for simple byte and character output. In addition, both PrintStream and PrintWriter implement the same set of methods for converting internal data into formatted output. Two levels of formatting are provided:

* print() and println() format individual values in a standard way.
* format() formats almost any number of values based on a format string, with many options for precise formatting.

As PrintStream and PrintWriter implements the same set of methods for formatting, this section focuses on PrintWriter.

PrintWriter prints formatted representations of objects to a text-output stream. This call implements all of the print() methods found in PrintStream, but does not contain methods for writing raw bytes. Methods in this class never throw I/O exception, although some of its constructors may. The client may require as to whether any errors have occurred by invoking checkError().

    java.io.PrintWriter
     1:  public class PrintWriter extends Writer {
     2:     public PrintWriter(Writer out) { ... }
     3:     public PrintWriter(Writer out, boolean autoFlush) { ... }
     4:     public PrintWriter(OutputStream out) { ... }
     5:     public PrintWriter(OutputStream out, boolean autoFlush) { ... }
     6:     public PrintWriter(String fileName) throws FileNotFoundException { ... }
     7:     public PrintWriter(String fileName, String csn) throws FileNotFound, UnsupportedEncodingException { ...}
     8:     public PrintWriter(File file) throws FileNotFoundException { ... }
     9:     public PrintWriter(File file, String csn) throws FileNotFound, UnsupportedEncodingException { ...}
    ??:
    ??:     // for methods, refer the Java API doc
    ?? }

Some convenience constructors (line 4,5,6,8) creates the necessary intermediate OutputStreamWriter, which will encode characters using the default charset for this instance of the Java virtual machine. Two constructors taking the charset name (line 7,9) also creates the necessary intermediate OutputStreamWriter, which will encode characters using the provided charset.

In general, a Writer send its output immediately to the underlying character or byte stream. Unless prompt output is required, it is advisable to wrap a BufferedWriter around any Writer whose write()operations may be costly, such as FileWriters and OutputStreamWriters.

For example, the following code will buffer the PrintWriter's output to the file. Without buffering, each invocation of a print() method would cause characters to be converted into bytes that would then be written immediately to the file, which can be very inefficient.

    se.tutorials.basicio.CopyLines
    20:        PrintWriter writer = null;
    ??:
    27:           writer = new PrintWriter(
    28:                    new BufferedWriter(
    29:                    new OutputStreamWriter(
    30:                    new FileOutputStream(OUTPUT_FILENAME, false),
    31:                    StandardCharsets.UTF_8)));

**Sample Code:**

Character I/O usually occurs in bigger units than single characters. One common unit is the line: a string of characters with a line terminator at the end. A line terminator can be a carriage-return/line-feed sequence("\r\n"), a single carriage-return ("\r"), or single line-feed("\n"). Supporting all possible line terminators allow programs to read text files created on any of the widely used operating systems.

The following sample code invokes BufferedReader.readLine() and PrintWriter.println() to do input and output one line at a time.

    se.tutorials.basicio.CopyLines
    15:     private static final String INPUT_FILENAME  = ... ;
    16:     private static final String OUTPUT_FILENAME = ... ;
    ??:
    19:        BufferedReader reader = null;
    20:        PrintWriter writer = null;
    21:  
    22:        try {
    23:           reader = new BufferedReader(
    24:                    new InputStreamReader(
    25:                    new FileInputStream(INPUT_FILENAME),
    26:                    "UTF-8"));
    27:           writer = new PrintWriter(
    28:                    new BufferedWriter(
    29:                    new OutputStreamWriter(
    30:                    new FileOutputStream(OUTPUT_FILENAME, false),
    31:                    StandardCharsets.UTF_8)));
    32:           String line;
    33:           while ((line = reader.readLine()) != null) {
    34:              writer.println(line);
    35:           }
    36:        } catch (FileNotFoundException e) {
    37:           System.err.println("A file for CopyCharacters not found.");
    38:        } catch (IOException e) {
    39:           System.err.println("Failed to copy characters.");
    40:        } finally {
    41:           try {
    42:              if (reader != null ) { reader.close(); }
    43:              if (writer != null ) { writer.close(); }
    44:           } catch (IOException e) {
    45:              System.err.println("Failed to close an output stream.");
    46:           }
    47:        }

**Sample Code Explained:**

Definitions of methods used in the sample code follows:

    java.io.BufferedReader
     2:     public BufferedReader(Reader in) { ... }
     7:     public String readLine() throws IOException { ... }

* BufferedReader(Reader in) - Creates a buffering character-input stream that uses a default-sized input buffer.
* readLine() - Reads a line of text. A line is considered to be terminated by any one of a line feed ('\n'), a carriage return ('\r'), or a carriage return followed immediately by a line feed. Returns a String containing the contents of the line, not including any line-termination characters, or null if the end of the stream has been reached. See also Files.readAllLines(Path, Charset).

    java.io.BufferedWriter
     2:     public BufferedWriter(Writer out) { ... }

* BufferedWriter(Writer out) - Creates a buffered character-output stream that uses a default-sized output buffer.

    java.io.PrintWriter
     2:     public PrintWriter(Writer out) { ... }
    ??:     public void println(String s);

* PrintWriter(Writer out) - Creates a new PrintWriter, without automatic line flushing.
* println(String s) - Prints a String then terminates the line. This methods behaves as though it invokes print(String) and then println().


(End of BasicIOa)
