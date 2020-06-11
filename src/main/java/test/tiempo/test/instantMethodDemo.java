package main.java.test.tiempo.test;

import java.time.Clock;
import java.time.Instant;

public class instantMethodDemo {
    static Clock clock = Clock.systemDefaultZone();

    // Main method
    public static void main(String[] args) throws InterruptedException {

        // create Clock Object
        Clock clock1 = Clock.systemDefaultZone();

        // get Instant Object of Clock
        // object using instant() method
        Instant instantObj = clock.instant();

        // print details of Instant Object
        while (true) {
            Thread.sleep(100);
            System.out.println("Instant for class " + clock + " is " + instantObj);
        }
    }
}
