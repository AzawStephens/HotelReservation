package api;
import model.Customer;
import model.IRoom;
import java.time.Duration;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
public class MainMenu {
    public static AdminResource adminResource = AdminResource.getInstance();
    public static HotelResource hotelResource = HotelResource.getInstance();
    public static void printMenuOptions()
    {
        try {
            String mainOptions = "Welcome to the Hotel Reservation Application" +
                    "\n---------------------------------------------- " +
                    "\n1. Find and Reserve a room" +
                    "\n2. See my Reservation" +
                    "\n3. Create an account" +
                    "\n4. Admin" +
                    "\n5. Exit" +
                    "\n------------------------------------------------" +
                    "\nPlease select a number from the menu option";
            System.out.println(mainOptions);
            Scanner keyboard = new Scanner(System.in);
            int selection = keyboard.nextInt();
            directory(selection);
        } catch (Exception e) {
            System.out.println("Please enter a valid option");
            printMenuOptions();
        }
    }
    public static void directory(int selection)
    {
        if (selection == 1 || selection == 2 || selection == 3 || selection == 4 || selection == 5)
        {
            switch (selection)
            {
                case (1):
                {
                    findAndReserve();
                }
                break;
                case (2):
                {
                    lookUpReservation();
                    printMenuOptions();
                }
                break;
                case (3):
                {
                    createNewCustomer();
                    printMenuOptions();
                }
                break;
                case (4):
                {
                    AdminMenu adminMenu = new AdminMenu();
                }
                break;
                case (5):
                {
                    System.exit(0);
                }
                break;
            }
        } else
        {
            System.out.println("Please enter a valid option from the menu");
            printMenuOptions();
        }
    }
    public static void findAndReserve()
    {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter a check in date mm/dd/yyyy");
            String checkIn = keyboard.nextLine();
            Date checkInDate;
            Date checkOutDate;
            String accountYesOrNo;
            String email;
            if (checkDate(checkIn))
            {
                checkInDate = new Date(checkIn);
                System.out.println("Enter a check out date mm/dd/yyyy");
                String checkOut = keyboard.nextLine();
                if (checkDate(checkOut))
                {
                    checkOutDate = new Date(checkOut);
                    System.out.println("Do you have an account with us? y/n");
                    accountYesOrNo = keyboard.nextLine();
                    if(accountYesOrNo.equalsIgnoreCase("y"))
                    {
                        System.out.println("Enter your registered email address");
                        email = keyboard.next();
                        if(memberValidation(email))
                        {
                            reserving(checkInDate,checkOutDate,email);
                        }
                    }else if(accountYesOrNo.equalsIgnoreCase("n")){createNewCustomer();}
                    else{throw new Exception("Wrong Input");}
                }
                }else{throw new Exception("Wrong date format");}
            }//end of try statement
            catch (Exception e) {
            System.out.println(e.getMessage());
            findAndReserve();
        }
        }
    public static boolean checkDate(String date)
    {
        Pattern pattern = Pattern.compile("^([0-9]{2})/([0-9]{2})/([0-9]{4})$");
        boolean validDate;
        if (pattern.matcher(date).matches())
        {
            validDate = true;
        } else
        {
            validDate = false;
        }
        return validDate;
    }
    public static void reserving(Date checkIn, Date checkOut, String email)
    {

        try
        {
            Scanner keyboard = new Scanner(System.in);
            String bookYesOrNo;
            String roomNumber;
            boolean found = false;
            if(!hotelResource.findARoom(checkIn,checkOut).isEmpty())
            {
                System.out.println(hotelResource.findARoom(checkIn,checkOut));
                System.out.println("What room would you like to reserve?");
                roomNumber = keyboard.nextLine();
                for (IRoom room : adminResource.getAllAvailableRooms())
                {
                    if (room.getRoomNumber().equalsIgnoreCase(roomNumber))
                    {
                        hotelResource.bookARoom(email,room,checkIn,checkOut);//books a room and return a reservation
                        System.out.println("Thank you for booking with us!");
                        System.out.println(hotelResource.getCustomersReservations(email));
                        found = true;
                        printMenuOptions();
                    }
                }if(!found){System.out.println("Invalid room number");}
            }else if(hotelResource.findARoom(checkIn,checkOut).isEmpty())
            {
                Date newCheckIn = new Date(checkIn.getTime()+Duration.ofDays(7).toMillis());
                Date newCheckOut = new Date(checkOut.getTime()+Duration.ofDays(7).toMillis());
                if(!hotelResource.findARoom(newCheckIn,newCheckOut).isEmpty())
                {
                    System.out.println("Recommended rooms from "+newCheckIn+" through "+newCheckOut);
                    System.out.println(hotelResource.findARoom(newCheckIn,newCheckOut));
                    System.out.println("Would you like to book a room for these dates? y/n");
                    bookYesOrNo = keyboard.nextLine();
                    if(bookYesOrNo.equalsIgnoreCase("y"))
                    {
                        reserving(newCheckIn,newCheckOut,email);
                    }else if(bookYesOrNo.equalsIgnoreCase("n"))
                    {
                        System.out.println("Maybe next time!");
                        printMenuOptions();
                    }else{throw new Exception("Wrong Input");}
                }else{ System.out.println("No Rooms available");
                    printMenuOptions();}
            }

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            reserving(checkIn,checkOut,email);
        }
    }
    public static boolean memberValidation(String email)
    {
        boolean validMember;
            for(Customer customer : adminResource.getAllCustomers())
            {
                if(customer.getEmail().equalsIgnoreCase(email))
                {
                    validMember = true;
                    return validMember;
                }
            }
            validMember = false;
            if(validMember == false)
            {
                System.out.println("Email address not found");
                System.out.println("Please create an account first");
                validMember = false;
                printMenuOptions();
            }
        else
        {
            System.out.println("Please enter a valid option");
            validMember = false;
            findAndReserve();
        }
        return validMember;
    }
    public static void createNewCustomer()
    {
        try
        {
            String email,firstName,lastName;
            System.out.println("Enter your email address: ");
            Scanner keyboard = new Scanner(System.in);
            email = keyboard.nextLine();
            for(Customer customer: adminResource.getAllCustomers())
            {
                if(customer.getEmail().equalsIgnoreCase(email))
                {
                    System.out.println("Email address is already registered");
                    printMenuOptions();
                }
            }
            System.out.println("Enter your first name: ");
            firstName = keyboard.nextLine();
            System.out.println("Enter your last name: "  ) ;
            lastName = keyboard.nextLine();
            hotelResource.createACustomer(email, firstName,lastName);
            printMenuOptions();

        }catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            createNewCustomer();
        }
    }
    public static void lookUpReservation()
    {
        Scanner keyboard = new Scanner(System.in);
        String email;
        System.out.println("Please enter your registerd email address: ");
        email = keyboard.next();
        if(memberValidation(email) && hotelResource.getCustomersReservations(email).isEmpty())
        {
            System.out.println("No reservations under that email.");
            printMenuOptions();
        }
        else if(memberValidation(email))
        {
            System.out.println(hotelResource.getCustomersReservations(email));
        }
    }
}


