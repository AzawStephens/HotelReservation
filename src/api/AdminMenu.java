package api;
import model.*;
import service.ReservationService;

import java.util.*;
public class AdminMenu {
    public static AdminResource adminResource = AdminResource.getInstance();
    public static HotelResource hotelResource = HotelResource.getInstance();

    public AdminMenu() {
        printMenu();
    }

    public static void printMenu() {
        String adminOptions = "Admin Menu" +
                "\n----------------------------------------------" +
                "\n1. See all Customers" +
                "\n2. See all Rooms" +
                "\n3. See all Reservations" +
                "\n4. Add a Room" +
                "\n5. Back to Main Menu" +
                "\n----------------------------------------------" +
                "\nPlease select a number for the menu option";
        System.out.println(adminOptions);
        Scanner keyboard = new Scanner(System.in);
        int selection = keyboard.nextInt();
        directory(selection);
    }

    public static void directory(int selection) {
        if (selection == 1 || selection == 2 || selection == 3 || selection == 4 || selection == 5) {
            switch (selection) {
                case (1): {
                    System.out.println(adminResource.getAllCustomers());
                    printMenu();
                }
                break;
                case (2): {
                    if (adminResource.getAllRooms().isEmpty()) {
                        System.out.println("No rooms in database");
                        printMenu();
                        }if (!adminResource.getAllRooms().isEmpty()) {
                        for (IRoom room : adminResource.getAllRooms()) {
                            System.out.println(room);
                        }
                    }
                    printMenu();
                }
                break;
                case (3): {
                    {
                        if (hotelResource.reservationService.reservations.isEmpty()) {
                            System.out.println("No reservations");
                        } else {
                            adminResource.displayAllReservations();
                        }
                        printMenu();
                    }
                }
                break;
                case (4): {
                    addARoom();
                }
                break;
                case (5): {
                    MainMenu.printMenuOptions();
                }
                break;

            }
        } else {
            printMenu();
        }
    }

    public static void addARoom() {
        Scanner keyboard = new Scanner(System.in);
        String addanotherRoom = "";
        String roomNumber;
        double price;
        String whichRoomType;
        RoomType roomType;
        List<IRoom> rooms = new ArrayList<IRoom>();
        IRoom room;
        System.out.println("Enter room number :");
        roomNumber = keyboard.nextLine();
            try {
                System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                whichRoomType = keyboard.nextLine();
                if (whichRoomType.equals("1")) {
                    roomType = RoomType.SINGLE;
                } else if (whichRoomType.equals("2")) {
                    roomType = RoomType.DOUBLE;
                } else {
                    throw new Exception("Wrong Input");
                }
                    System.out.println("Enter Price: ");
                    price = keyboard.nextDouble();
                    room = new Room(roomNumber, price, roomType);
                    if(checkDatabase(room))
                    {
                        rooms.add(room);
                        adminResource.addRoom(rooms);
                        System.out.println("Would you like to add another room? y/n");
                        addanotherRoom = keyboard.next();
                        if (addanotherRoom.equalsIgnoreCase("y")) {
                            addARoom();
                        } else if (addanotherRoom.equalsIgnoreCase("n")) {
                            printMenu();
                        }
                    }else if (!addanotherRoom.equalsIgnoreCase("y") || addanotherRoom.equalsIgnoreCase("n")){addARoom();}

            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                printMenu();
            }
        }

    public static boolean checkDatabase(IRoom aRoom)
    {
        boolean goodToGo;
        if(!adminResource.getAllRooms().isEmpty())
        {

            for (IRoom thisRoom : adminResource.getAllRooms()) {
                    if (aRoom.equals(thisRoom))
                    {
                        System.out.println("Room already in database");
                        goodToGo = false;
                        return goodToGo;
                    }
            }
        }
        goodToGo = true;
        return goodToGo;
    }

}




