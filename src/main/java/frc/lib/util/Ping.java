package frc.lib.util;

/**
 * The Ping class, storing a value and returning whether
 * said value has been accessed yet or not.
 */
public class Ping {

    private boolean ifPinged;

    public Ping() {
        ifPinged = false;
    }

    public void ping() {
        ifPinged = true;

        System.out.println("PINGED");
    }

    public boolean get() {
        if (ifPinged) {
            System.out.println("PING FETCHED");
            ifPinged = false;

            return true;
        } else {
            return false;
        }
    }
}
