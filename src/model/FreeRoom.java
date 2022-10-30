package model;

public class FreeRoom extends Room{

    String roomNum;
    RoomType enumeration;
    public FreeRoom(){super();}
    public FreeRoom(String roomNum,RoomType enumeration)
    {
        this.roomNum = roomNum;
        this.enumeration = enumeration;
        price = 0.00;
    }
    public String getRoomNum() {
        return roomNum;
    }

    public RoomType getRoomType() {
        return enumeration;
    }


    @Override
    public String toString()
    {
        return "Room Number: "+getRoomNum()+" price: $"+price+" Room Type "+getRoomType();
    }
}
