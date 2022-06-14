# Threads

### What is a "thread"?

A thread is a "pathway" for a program to run.

- 1 thread runs 1 program pathway.
- The more threads there is, the more simultaneous instances can be ran.

### Why the need for "multi-thread"?

Lets say there is such a code:

```java
// App.java
Console cons = System.console();
String userInput = cons.readLine("User to input message");
Socket sock = new Socket();
String msg = ois.readUTF();
```

The program will run line by line and its inefficient to let `userInput` wait for the program to run finish before the user can input another message.

Therefore, it is more efficient to split it like this:

```java
// App.java
Console cons = System.console();
String userInput = cons.readLine("User to input message");
```

```java
// Backend.java
Socket sock = new Socket();
String msg = ois.readUTF();
```

At the same time, we let App.java run on 1 thread, and Backend.java run on another thread.

This is an example of an implementation:

```java
// ThrMain.java
public class ThrMain implements Runnable {
    private final String msg;
    private final int sec;

    public ThrMain(String msg, int sec) {
        this.msg = msg;
        this.sec = sec;
    }

    // The thread's body
    @Override
    public void run() {
        System.out.printf(">>> Start %s, %d\n", msg, sec);
    }
}
```

```java
// App.java
public class App {
    public static void main( String[] args) {
        //Create a thread pool
        ExecutorService thrPool = Executors.newFixedThreadPool(/*number of cores ur computer has*/ 4);
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            String msg = "Thread-%d".formatted(i);
            int sec = rand.nextInt(1, 5);
            System.out.printf("/tDispatching %s to pool\n", msg);
            ThrMain thr = new ThrMain(msg, sec);
            thrPool.submit(thr)
        }
    }
}
```
