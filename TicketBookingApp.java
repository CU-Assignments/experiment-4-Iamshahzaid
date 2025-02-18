import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private final boolean[] seats = new boolean[TOTAL_SEATS];
    private final ReentrantLock lock = new ReentrantLock();

    public void bookSeat(int seatNumber, String customer) {
        lock.lock();
        try {
            if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
                System.out.println(customer + " - Invalid seat number: " + seatNumber);
                return;
            }
            if (!seats[seatNumber]) {
                seats[seatNumber] = true;
                System.out.println(customer + " successfully booked seat " + seatNumber);
            } else {
                System.out.println(customer + " - Seat " + seatNumber + " is already booked.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class Customer extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;

    public Customer(TicketBookingSystem system, int seatNumber, String name, int priority) {
        super(name);
        this.system = system;
        this.seatNumber = seatNumber;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, getName());
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();

        Customer vip1 = new Customer(bookingSystem, 3, "VIP_Customer_1", Thread.MAX_PRIORITY);
        Customer vip2 = new Customer(bookingSystem, 5, "VIP_Customer_2", Thread.MAX_PRIORITY);
        Customer normal1 = new Customer(bookingSystem, 3, "Normal_Customer_1", Thread.NORM_PRIORITY);
        Customer normal2 = new Customer(bookingSystem, 5, "Normal_Customer_2", Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        normal1.start();
        normal2.start();
    }
}