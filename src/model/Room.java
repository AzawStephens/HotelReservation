package model;

import java.util.Objects;

public class Room implements IRoom{


    private String roomNumber;
    Double price;
    RoomType enumeration;
    public Room(){super();}
    public Room(String roomN, Double price, RoomType enumeration)
    {
        this.roomNumber = roomN;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString()
    {
        return "Room Number: "+getRoomNumber() +" Price $"+getRoomPrice() + " Room Type: "+getRoomType();
    }
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return true;
    }

}
