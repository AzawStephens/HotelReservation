package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.List;

/*
The AdminResource should have little to no behavior contained
inside the class and should make use of the Service classes to implement its methods.
 */
public class AdminResource {
    private AdminResource(){}
    private static final AdminResource adminResource = new AdminResource();
    public static AdminResource getInstance(){return adminResource;}
    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();
    public Customer getCustomer(String email)
    {
        return customerService.getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms)
    {
        for(IRoom room: rooms)
        {
            reservationService.addRoom(room);//add to rooms collection inside of reservationService
        }
    }
    public Collection<IRoom> getAllAvailableRooms(){return reservationService.availableRoomsCollection;}
    public Collection<IRoom> getAllRooms()
    {
        return reservationService.roomsCollection;
    }
    public Collection<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }
    public void displayAllReservations()
    {
        reservationService.printAllReservation();
    }
}
