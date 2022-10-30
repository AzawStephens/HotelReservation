package model;
import java.util.Date;
import java.util.Objects;

public class Reservation {
   private final Customer customer;
   private final IRoom room;
   private final Date checkInDate;
   private final Date checkOutDate;
    public IRoom getRoom() {
        return room;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return room.equals(that.room) && checkInDate.equals(that.checkInDate) && checkOutDate.equals(that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, checkInDate, checkOutDate);
    }

    public Reservation(Customer customer1, IRoom room1, Date checkInDate1, Date checkOutDate2)
{
    this.customer = customer1;
    this.room = room1;
    this.checkInDate = checkInDate1;
    this.checkOutDate = checkOutDate2;
}
    @Override
    public String toString()
    {
        return "Customer: "+getCustomer()+"\nroom: "+getRoom()+"\nCheck-in date: "+getCheckInDate()+"\nCheck-out date: "+getCheckOutDate();
    }
}
