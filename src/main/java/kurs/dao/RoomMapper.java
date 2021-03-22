package kurs.dao;

import kurs.models.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RoomMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();

        room.setId(resultSet.getInt("id"));
        room.setNumb(resultSet.getInt("numb"));
        room.setQuantity(resultSet.getInt("quantity"));
        room.setBuff(resultSet.getString("buff"));
        room.setPrice(resultSet.getInt("price"));
        room.setH_id(resultSet.getInt("h_id"));

        return room;
    }


}