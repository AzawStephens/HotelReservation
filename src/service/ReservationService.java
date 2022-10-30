package service;
import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.*;
public class ReservationService {
    private ReservationService() {}
    private static final ReservationService reservationService = new ReservationService();
    public static ReservationService getInstance(){return reservationService;}
    public IRoom theRoom;
    public Reservation reservation;
    public Set<IRoom> roomsCollection= new HashSet<>();
    public Set<IRoom> availableRoomsCollection= new HashSet<>();
    public Set<Reservation> reservations = new HashSet<>();
    public void addRoom(IRoom room) {roomsCollection.add(room);}//add a room to the rooms collection
    public void addToAvailableRooms(IRoom room) {availableRoomsCollection.add(room);}
    public IRoom getARoom(String roomId){
        return theRoom;
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate)
    {
        reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        reservations.add(reservation);
        return reservation;
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate)
    {
        availableRoomsCollection.clear();
        for (IRoom room : roomsCollection)
        {
            addToAvailableRooms(room);
        }
        if (!reservations.isEmpty()) {
            for (Reservation res : reservations) {
                if (!isDateGood(checkInDate, checkOutDate, res))
                {
                    availableRoomsCollection.remove(res.getRoom());
                }
            }
        }
            return availableRoomsCollection;
    }
        public boolean isDateGood(Date checkInDate, Date checkoutDate, Reservation res)
        {
            boolean isGood;
            if(checkInDate.after(res.getCheckOutDate()) ||checkoutDate.before(res.getCheckInDate()))
            {
                isGood = true;
            }else{isGood = false;}
            return isGood;
        }
    public Collection<Reservation> getCustomersReservation(Customer customer){
        Set<Reservation> thisCustomersReservations = new HashSet<>();
        for(Reservation res : reservations)
        {
            if(res.getCustomer().equals(customer))
            {
                thisCustomersReservations.add(res);
            }
        }
        return thisCustomersReservations;
    }
    Collection<Reservation> AllReservations(){return reservations;}
    public void printAllReservation()
    {
        for(Reservation res : reservations)
        {
            System.out.println(res);
            System.out.println("-----------------------------------------------");
        }
    }
}
