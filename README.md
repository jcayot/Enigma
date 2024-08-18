# Enigma

This is a simple implementation of the Enigma machine. The Enigma machine was a cipher device used by the German military during World War II to keep their communications secure.

## Disclaimer

For those who don't know history Enigma encryption was broken during the war and germans lost it.

So even though they would've lost anyway I highly discourage anyone to use this program or any implementation of Enigma in attempt to protect any communication.

#### So I cannot be held responsible if you loose any war by using this program!

## Prerequisites

- Java Development Kit (JDK) 22 or higher

## How to Build and Run

1. **Clone the repository:**

    ```sh
    git clone https://github.com/yourusername/enigma.git
    cd enigma
    ```

2. **Build the project:**

    You can build the project using the Gradle wrapper provided. This will compile the source code and run any tests.

    ```sh
    ./gradlew build
    ```

3. **Run the Enigma application:**

    You can run the application using the following command:

    ```sh
    ./gradlew run
    ```

    The `--console=plain` option is used to disable Gradle's progress indicators for a cleaner output.

4. **Input:**

    You will be prompted to provide input for configuring the Enigma machine (e.g., rotor types and reflector). Provide the required input as prompted by the application.

## Running Tests

To run the tests:

(Which should run fast since there are no tests :P) 

```sh
./gradlew test
```

This will compile and run the unit tests in the `src/test` directory.

## License

This project is licensed under the Supreme Licence.