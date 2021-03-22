package kurs.dao;

import kurs.models.Bron;
import kurs.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BronMapper implements RowMapper<Bron> {

    @Override
    public Bron mapRow(ResultSet resultSet, int i) throws SQLException {
        Bron bron = new Bron();

        bron.setId(resultSet.getInt("id"));
        bron.setU_id(resultSet.getInt("u_id"));
        bron.setR_id(resultSet.getInt("r_id"));
        bron.setData1(resultSet.getString("data1"));
        bron.setData2(resultSet.getString("data2"));
        bron.setPrice(resultSet.getInt("price"));

        return bron;
    }
}
