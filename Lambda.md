# Lambda Expression

## Anonymous functions in JS (sidetrack)

Consider the following:

```javascript
const mkGreetings = (name) => {
  return function () {
    console.log(`Hello ${name}`);
  };
};
const greetBetty = mkGreetings("Betty");
console.log("Greet Betty:", greetBetty);
```

Notice how the inner function "inherits" the value `name` even though `name` does not get passed to the inner function?

> If so, how does the inner function know what `name` is?

This is a characteristic of anonymous functions! It _"remembers"_ the values in the outer function. These values are also known as "free variables".

#### Another implementation of annonymous functions

Consider the following:

```javascript
const mkPower = (n) => {
  return function (x) {
    const result = 1;
    for (let i = 0; i < n; i++) {
      result *= x;
    }
    return result;
  };
};

const square = mkPower(2);
const cube = mkPower(3);

console.log("2^2 =", square(2));
console.log("4^2 =", square(4));
console.log("2^3 =", cube(2));
```

## Java implementation of a annonymous function (a.k.a. Lamda)

```java
public class App {
    public static void main(String[] args) {
        ExecutorService thrPool = Executors.newFixedThreadPool(4);
        Random rand = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            final int x = rand.nextInt(100);
            thrPool.submit(
                () -> {
                System.out.printf(">>> i - %d\n", x);
                }
            );
        }
        System.out.println("All done!");
    }
}
```

The "free variable" here is `x`. Therefore, we should use a `final` keyword as a free variable cannot be changed once it is used.
A resource:

### Another implementation:

```java
public static void main(String[] args) {
    Function <String, String> toUpper = (str) -> {
        return str.toUpperCase();
    }
    Runnable runMe = () -> {
        System.out.println("I am a lamda expression");
    }

    // String convert to int
    Function<String, Integer> strToInt = (str) -> {
        return Interger.parseInt(str);
    }

    int value = strToInt("1234");
    System.out.printf("strToInt("1234") = %d\n", value);
}
```

### Another implementation:

```java
public static void main(String[] args) {
    Random rand = new SecureRandom();
    List<Integer> myList = new LinkedList<>();
    for (int i = 0; i < 100; i++) {
        myList.add(rand.nextInt(100));
    }

    // Using a traditional way
    for (int i = 0; i < myList.size(); i++) {
        int v = myList.get(i);
        // Only let even numbers through
        if (0 != (v % 2)) {
            continue;
        }
        System.out.printf(">>> %d\n", myList.get(i) + 1);
    }

    // Using lamda expression
    List<String> intList = myList.stream()
    .filter(v -> 0 == (v%2))
    .map(v -> Integer.toString(v))
    .toList();
}
```

### Yet another implementation to sum up all the random numbers:

```java
public static void main(String[] args) {
    Random rand = new SecureRandom();
    List<Integer> myList = new LinkedList<>();
    for (int i = 0; i < 100; i++) {
        myList.add(rand.nextInt(100));
    }

    // Using the traditional way to sum all the values
    int result = 0;
    for (int i = 0; i < myList.size(); i++) {
        int v = myList.get(i);
        if (0 != (v % 2)) { // Filter for even values
            continue;
        }
        result += myList.get(i)
    }
    System.out.printf("result = %d", result);

    // Using lamda expression (also commonly known as "map-reduce")
    int sumOfMyList = myList.stream()
    .filter(v -> 0 == (v%2))
    .map(v -> (v + 1))
    .reduce(0, (x, y) -> x + y)
    System.out.printf("Sum of myList = %d", sumOfMyList);
}
```
