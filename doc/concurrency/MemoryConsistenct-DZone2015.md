# Java Multi-threading: Volatile Variables, Happens-before Relationship, and Memory Consistency

An explanation of what a volatile variable is in Java, when, and how to use it in your applications.

by MD Sayem Ahmed, Nov. 02, 15, Java Zone: Opinion

## Update

Please see the comments from Jean-philippe Bempel in the comment section. He mentioned a real example of how a deadlock can happen from JVM optimization. One of the reasons I like to blog as much as possible is that I can learn from the community if I misunderstood something. Thank you!

## What is a Volatile Variable?

Volatile is a keyword in Java. You cannot use this as a variable or method name. Period.

## Seriously, Jokes Aside, What is Volatile Variable? When Should We Use It?

Ha ha, sorry, couldn’t help.

We typically use volatile keyword when we share variables with more than one thread in a multi-threaded environment, and we want to avoid any memory inconsistency errors due to the caching of these variables in the CPU cache.

Consider the following example of producer/consumer, where we are producing/consuming items one at a time –

     1:  public class ProducerConsumer {
     2:    private String value = "";
     3:    private boolean hasValue = false;
     4:
     5:    public void produce(String value) {
     6:      while (hasValue) {
     7:        try {
     8:          Thread.sleep(500);
     9:        } catch (InterruptedException e) {
    10:          e.printStackTrace();
    11:        }
    12:      }
    13
    14:      System.out.println("Producing " + value + " as the next consumable");
    15:      this.value = value;
    16:      hasValue = true;
    17:    }
    18
    19:    public String consume() {
    20:      while (!hasValue) {
    21:        try {
    22:          Thread.sleep(500);
    23:        } catch (InterruptedException e) {
    24:          e.printStackTrace();
    25:        }
    26:      }
    27:
    28:      String value = this.value;
    29:      hasValue = false;
    30:      System.out.println("Consumed " + value);
    31:      return value;
    32:    }
    33:  }

In the above class, the **produce** method generates a new value by storing its argument into **value**, and changing the **hasValue** flag to true. The while loop checks if the value flag (hasValue) is true, which signifies the presence of a new value not yet consumed, and if it’s true then it requests the current thread to sleep. This sleeping loop only stops if the hasValue flag has been changed to false, which is only possible if the new value has been consumed by the **consume** method. The consume method requests the current thread to sleep if no new value is available. When a new value is produced by the produce method it terminates its sleeping loop, consumes it, and clears the value flag.

Now imagine that two threads are using an object of this class – one is trying to produce values (the writer thread), and another one is consuming them (the reader thread). The following test illustrates this approach –

     1:  public class ProducerConsumerTest {
     2:
     3:    @Test
     4:    public void testProduceConsume() throws InterruptedException {
     5:      ProducerConsumer producerConsumer = new ProducerConsumer();
     6:      List<String> values = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8",
     7:          "9", "10", "11", "12", "13");
     8:      Thread writerThread = new Thread(() -> values.stream()
     9:          .forEach(producerConsumer::produce));
    10:      Thread readerThread = new Thread(() -> {
    11:        for (int i = 0; i > values.size(); i++) {
    12:          producerConsumer.consume();
    13:        }
    14:      });
    15:
    16:      writerThread.start();
    17:      readerThread.start();
    18:
    19:      writerThread.join();
    20:      readerThread.join();
    21:    }
    22:  }

This example will produce expected output in most of the times, but it also has a strong chance to run into a deadlock!

## How?

Let’s talk about computer architecture a bit.

We know that a computer consists of CPUs and Memory Units (and many other parts). Even though the main memory is where all of our program instructions and variables/data reside, during program execution CPUs can store copies of variables in their internal memory (which is known as CPU cache) for performance gain. Since modern computers now have more than one CPUs, there are more than one CPU caches as well.

In a multi-threaded environment, it’s possible for more than one threads to execute at the same time, each one in a different CPU, (although this is totally dependent on the underlying OS), and each one of them may copy variables from main memory into their corresponding CPU cache. When a thread accesses these variables, they will then access these cached copies, not the actual ones in the main memory.

Now let’s assume that the two threads in our test are running on two different CPUs, and the hasValue flag has been cached on either one of them (or both). Now consider the following execution sequence –

1. **writerThread** produces a value, and changes the hasValue to true. However, this update is only reflected in the cache, not in the main memory.
1. **readerThread** is trying to consume a value, but it’s cached copy of the hasValue flag is set to false. So even though a value has been produced by the writerThread, it cannot consume it as the thread cannot break out of the sleeping loop (hasValue is false).
1. Since the readerThread is not consuming the newly generated value, writerThread cannot proceed either as the flag is not being cleared, and hence it will be stuck in its sleeping loop.
1. And we have a deadlock in our hands!

This situation will only change if the hasValue flag is synchronized across all caches, which totally depends on the underlying OS.

### What’s the solution then? And how does volatile fit into this example?

If we just mark the hasValue flag as volatile, we can be sure that this type of deadlock will not occur –

     3:  private volatile boolean hasValue = false;

Marking a variable as volatile will force each thread to read the value of that variable directly from the main memory. Also each write to a volatile variable will be flushed into the main memory immediately. If the threads decide to cache the variable, it will be synced with the main memory on each read/write.

After this change, consider the previous execution steps which led to deadlock –

1. Writer thread produces a value, and changes the hasValue to true. This time the update will be directly reflected into the main memory (even if it’s cached).
1.Reader thread is trying to consume a value, and checking the value of hasValue. This time every read will force the value to be fetched directly from the main memory, so it will pick up the change made by the writer thread.
1.Reader thread consumes the generated value, and clears the value of the flag. This new value will go to the main memory (if it’s cached, then the cached copy will also be updated).
1.Writer thread will pick up this change as every read is now accessing the main memory. It will continue to produce new values.

And voila! We are all happy ^_^ !

## I See. Is This All Volatile Does, Forcing Threads to Read/Write Variables Directly From Memory?

Actually it has some further implications. Accessing a volatile variable establishes a happens-before relationship between program statements.

## What is a Happens-before Relationship?

A happens-before relationship between two program statements is sort a guarantee which ensures that any memory writes by one statement are visible to another statement.

## How Does It Relate With Volatile?

When we write to a volatile variable, it creates a happens-before relationship with each subsequent read of that same variable. So any memory writes that have been done until that volatile variable write, will subsequently be visible to any statements that follow the read of that volatile variable.

## Err….Ok….I Sort of Got it, But May Be an Example Will Be Good.

Ok, sorry about the vague definition. Consider the following example –

     1:  // Definition: Some variables
     2:  private int first = 1;
     3:  private int second = 2;
     4:  private int third = 3;
     5:  private volatile boolean hasValue = false;
     6:
     7:  // First Snippet: A sequence of write operations being executed by Thread 1
     8:  first = 5;
     9:  second = 6;
    10:  third = 7;
    11:  hasValue = true;
    12:
    13:  // Second Snippet: A sequence of read operations being executed by Thread 2
    14:  System.out.println("Flag is set to : " + hasValue);
    15:  System.out.println("First: " + first);   // will print 5
    16:  System.out.println("Second: " + second); // will print 6
    17:  System.out.println("Third: " + third);   // will print 7

Let’s assume that the above two snippets being executed by two different threads – thread 1 and 2. When the first thread changes **hasValue**, it will not only flush this change to main memory, but it will also cause the **previous three writes** (and any other previous writes) to be flushed into the main memory as well! As a result, when the second thread accesses these three variables it will see all the writes made by thread 1, even if they were all cached before (and these cached copies will be updated as well)!

This is the exactly why we did not have to mark the value variable in our first example with volatile as well. Since we wrote to that variable before accessing hasValue, and read from it after reading hasValue, it was automatically synced with the main memory.

This has another interesting consequence. JVM is famous for its program optimization. Sometimes it reorders the program statements to boost performance without changing the output of the program. As an example, it can change the following sequence of statements –

     1:  first = 5;
     2:  second = 6;
     3:  third = 7;

Into this –

     1:  second = 6;
     2:  third = 7
     3:  first = 5;

However, when the statements involve accessing a volatile variable, then it will **never** move a statement occurring before a volatile write after it. Which means, it will never transform this –

     1:  first = 5;   // write before volatile write
     2:  second = 6;   // write before volatile write
     3:  third = 7;   // write before volatile write
     4:  hasValue = true;

Into this –

     1:  first = 5;
     2:  second = 6;
     4:  hasValue = true;
     3:  third = 7;  // Order changed to appear after volatile write! This will never happen!

Even though from the perspective of program correctness both of them seem to be equivalent. Note that the JVM is still allowed to reorder the first three writes among them as long as they all appear before the volatile write.

Similarly, the JVM will **also not** change the order of a statement which appears after a volatile variable read to appear before the access. Which means the following –

     1:  System.out.println("Flag is set to : " + hasValue);  // volatile read
     2:  System.out.println("First: " + first);  // Read after volatile read
     3:  System.out.println("Second: " + second); // Read after volatile read
     4:  System.out.println("Third: " + third);  // Read after volatile read

Will never be transformed by the JVM into this –

     1:  System.out.println("First: " + first);  // Read before volatile read! Will never happen!
     2:  System.out.println("Flag is set to : " + hasValue);  // volatile read
     3:  System.out.println("Second: " + second);
     4:  System.out.println("Third: " + third);

However, the JVM can certainly reorder the last three reads among them, as long as they keep appearing after the volatile read.


## I Sense a Performance Penalty Has to Be Paid For Volatile Variables.

You got that right, since volatile variables force main memory access, and accessing main memory is always way slower than accessing CPU caches. It also prevents certain program optimizations by JVM as well, further reducing the performance.

## Can We Always Use Volatile Variables to Maintain Data Consistency Across Threads?

Unfortunately not. When more than one threads read and write to the same variable, then marking it as volatile is not enough to maintain consistency. Consider the following UnsafeCounter class –

     1:  public class UnsafeCounter {
     2:    private volatile int counter;
     3:
     4:    public void inc() {
     5:      counter++;
     6:    }
     7:
     8:    public void dec() {
     9:      counter--;
    10:    }
    11:
    12:    public int get() {
    13:      return counter;
    14:    }
    15:  }

And the following test –

     1:  public class UnsafeCounterTest {
     2:
     3:    @Test
     4:    public void testUnsafeCounter() throws InterruptedException {
     5:      UnsafeCounter unsafeCounter = new UnsafeCounter();
     6:      Thread first = new Thread(() -> {
     7:        for (int i = 0; i < 5; i++) { 
     8:          unsafeCounter.inc();
     9:        }
    10:      });
    11:      Thread second = new Thread(() -> {
    12:        for (int i = 0; i < 5; i++) {
    13:          unsafeCounter.dec();
    14:        }
    15:      });
    16:
    17:      first.start();
    18:      second.start();
    19:      first.join();
    20:      second.join();
    21:
    22:      System.out.println("Current counter value: " + unsafeCounter.get());
    23:    }
    24:  }

The code is pretty self-explanatory. We are incrementing the counter in one thread, and decrementing it in another by same number of times. After running this test we expect the counter to hold 0, but this is not guaranteed. Most of the times it will be 0, and some of the times it will be -1, -2, 1, 2 i.e., any integer value between the range [-5, 5].

Why does this happen? It happens because both the increment and the decrement operation of the counter are not atomic – they do not happen all at once. Both of them consists of multiple steps, and the sequence of steps overlap with each other. So you can think of an increment operation as follows –

1. Read the value of the counter.
1. Add one to it.
1. Write back the new value of the counter.

And an decrement operation as follows –

1. Read the value of the counter.
1. Subtract one from it.
1. Write back the new value of the counter.

Now, let’s consider the following execution steps –

1. First thread has read the value of the counter from memory. Initially it’s set to zero. It then adds one to it.
1. Second thread has also read the value of the counter from memory, and saw that it’s set to zero. It then subtracts one from it.
1. First thread now writes back the new value of counter to memory, changing it to 1.
1. Second thread now writes back the new value of counter to memory, which is -1.
1. First thread’s update is lost.

## How Do We Prevent This?

By using synchronization –

     1:  public class SynchronizedCounter {
     2:    private int counter;
     3:
     4:    public synchronized void inc() {
     5:      counter++;
     6:    }
     7:
     8:    public synchronized void dec() {
     9:      counter--;
    10:    }
    11:
    12:    public synchronized int get() {
    13:      return counter;
    14:    }
    15:  }

Or by using an **AtomicInteger** –

     1:  public class AtomicCounter {
     2:    private AtomicInteger atomicInteger = new AtomicInteger();
     3:
     4:    public void inc() {
     5:      atomicInteger.incrementAndGet();
     6:    }
     7:
     8:    public void dec() {
     9:      atomicInteger.decrementAndGet();
    10:    }
    11:
    12:    public int get() {
    13:      return atomicInteger.intValue();
    14:    }
    15:  }

My personal choice is the one using AtomicInteger as the synchronized one hampers performance greatly by allowing only one thread to access any of the inc/dec/get methods.

## I Notice That the Synchronized Version Does Not Mark the Counter as Volatile. Does This Mean…?

Yup. Using the synchronized keyword also establishes a **happens-before relationship** between statements. Entering a synchronized method/block establishes a happens-before relationship between the statements that appear before it and the ones inside the method/block. For a full list of what establishes a happens-before relationship, please go here (https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html#MemoryVisibility).

That’s all I have to say about volatile for the time being.

(end)


