package kurs.dao;

import kurs.models.Hotel;
import kurs.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelMapper implements RowMapper<Hotel> {

    @Override
    public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {
        Hotel hotel = new Hotel();

        hotel.setId(resultSet.getInt("id"));
        hotel.setName(resultSet.getString("name"));
        hotel.setStars(resultSet.getInt("stars"));
        hotel.setPicture(resultSet.getString("pikch"));

        return hotel;
    }

}
