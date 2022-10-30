package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.*;
/*
The HotelResource should have little to no behavior contained
inside the class and should make use of the Service classes to implement its methods
 */
public class HotelResource {
    private HotelResource(){}
    static HotelResource hotelResource = new HotelResource();
    public static HotelResource getInstance(){return hotelResource;}
    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();
    public Customer getCustomer(String email)
    {
        return customerService.getCustomer(email);
    }
    public void createACustomer(String email, String firstName, String lastName)
    {
        customerService.addCustomer(email,firstName,lastName);
    }
    public IRoom getRoom(String roomNumber)
    {
        return reservationService.getARoom(roomNumber);
    }
    public Reservation bookARoom(String email, IRoom room1, Date checkIn, Date checkOut)
    {
        findARoom(checkIn,checkOut);
        return reservationService.reserveARoom(getCustomer(email),room1,checkIn,checkOut);
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail)
    {
        return reservationService.getCustomersReservation(getCustomer(customerEmail));
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
                         return reservationService.findRooms(checkIn,checkOut);
    }

}
