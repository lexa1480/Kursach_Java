package kurs.dao;


import kurs.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import kurs.models.Person;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(Person person){
        jdbcTemplate.update("INSERT INTO Person (name,surname,login,password) VALUES(?,?,?,?)",person.getName(),person.getSurname(),person.getLogin(),person.getPassword());
    }


    public Person CheckUser(LoginPassword loginPassword){
        return jdbcTemplate.query("SELECT * FROM Person WHERE login=? and password=?", new Object[]{loginPassword.getLogin(),loginPassword.getPassword()}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public Person getInfo(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id},new PersonMapper()).stream().findAny().orElse(null);//если лежит такой чел то вернет ео если нет ничего не вернет(BeanPropertyRollMapper тоже  самое что и наш)
    }

    public List<Bron> getBronInfo(int id){
        List<Bron> bron = jdbcTemplate.query("SELECT * FROM Bron WHERE u_id= ?",new Object[]{id}, new BronMapper());
        if(bron.isEmpty()){
            return null;
        }
        for(int i = 0; i < bron.size(); i++) {
            //Удаляем все устаревшие брони
            Date dateN = new Date();
            Date dateB = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateB = formatter.parse(bron.get(i).getData1());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateB.getTime() < dateN.getTime()) {
                bron.remove(i);
                i--;
            } else {
                bron.get(i).setNumR(jdbcTemplate.queryForObject("SELECT numb FROM Rooms WHERE id = ?", new Object[]{bron.get(i).getR_id()}, Integer.class));
                bron.get(i).setNameH(jdbcTemplate.queryForObject("SELECT name FROM Hotels WHERE id = ?", new Object[]{jdbcTemplate.queryForObject("SELECT h_id FROM Rooms WHERE id = ?", new Object[]{bron.get(i).getR_id()}, Integer.class)}, String.class));
            }
        }
        return bron;
    }

    public void UnBron(int id){
        jdbcTemplate.update("DELETE FROM Bron WHERE id = ?", id);
    }

    public List<Bron> getBronInfoHistory(int id){
        List<Bron> bron = jdbcTemplate.query("SELECT * FROM Bron WHERE u_id= ?",new Object[]{id}, new BronMapper());
        List<Bron> bronH = new ArrayList<Bron>();
        int k = 0;
        if(bron.isEmpty()){
            return null;
        }
        for(int i = 0; i < bron.size(); i++) {
            //Записываем используемые брони
            Date dateN = new Date();
            Date dateB = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateB = formatter.parse(bron.get(i).getData1());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateB.getTime() < dateN.getTime()) {
                //дата была в прошлом
                bronH.add(bron.get(i));

                bronH.get(k).setNumR(jdbcTemplate.queryForObject("SELECT numb FROM Rooms WHERE id = ?", new Object[]{bron.get(i).getR_id()}, Integer.class));
                bronH.get(k).setNameH(jdbcTemplate.queryForObject("SELECT name FROM Hotels WHERE id = ?", new Object[]{jdbcTemplate.queryForObject("SELECT h_id FROM Rooms WHERE id = ?", new Object[]{bron.get(i).getR_id()}, Integer.class)}, String.class));
                k++;
            }
        }
        return bronH;
    }

    public List<Integer> searchHotelID(Search search){

        List<Integer> rooms = jdbcTemplate.queryForList("SELECT id FROM Rooms WHERE quantity = ?",new Object[]{search.getQuantity()},Integer.class);


        List<Bron> roomsD = jdbcTemplate.query("SELECT * FROM Bron", new BronMapper());


        Date date1 = null;
        Date date2 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = formatter.parse(search.getData1());
            date2 = formatter.parse(search.getData2());
        }
        catch(ParseException e){
            e.printStackTrace();
        }

        for(int i = 0;i<roomsD.size();i++){
            try {
                Date date3 = formatter.parse(roomsD.get(i).getData1());
                Date date4 = formatter.parse(roomsD.get(i).getData2());
                if((date3.getTime() < date1.getTime() && date4.getTime() < date1.getTime()) || (date3.getTime() > date2.getTime() && date4.getTime() > date2.getTime())){

                }else {
                    rooms.remove((Integer) roomsD.get(i).getR_id());
                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(rooms.size() == 0){
            //retern null и далее что нет таких свободных отлей
            return null;
        }
        return rooms;
    }

    public  List<Hotel> searchHotel(List<Integer> index){

        List<Hotel> hotels = new ArrayList<Hotel>();
        List<Integer> hotelsId = new ArrayList<Integer>();
        int id = 0;

        if(index == null){
            return null;
        }

        for(int i = 0; i<index.size(); i++) {
            id = index.get(i);
            id = jdbcTemplate.queryForObject("SELECT h_id FROM Rooms WHERE id = ?", new Object[]{id}, Integer.class);
            if (!hotelsId.contains(id)) {
                hotelsId.add(id);
                hotels.add((Hotel) jdbcTemplate.query("SELECT * FROM Hotels WHERE id = ?", new Object[]{id}, new HotelMapper()).stream().findAny().orElse(null));//если лежит такой чел то вернет ео если нет ничего не вернет(BeanPropertyRollMapper тоже  самое что и наш)
            }
        }

        return hotels;
    }

    public List<Room> searchRooms(int h_id,List<Integer> index){

        List<Room> rooms = new ArrayList<Room>();
        int id = 0;
        for(int i = 0; i<index.size(); i++) {
            id = index.get(i);
            Room room = (Room) jdbcTemplate.query("SELECT * FROM Rooms WHERE h_id = ? and id = ?", new Object[]{h_id,id}, new RoomMapper()).stream().findAny().orElse(null);
            if(room != null){
                rooms.add(room);
            }
        }

        return rooms;
    }

    public void addBron(int u_id,int r_id,String data1,String data2){

        int priceOne = jdbcTemplate.queryForObject("SELECT price FROM Rooms WHERE id = ?", new Object[]{r_id}, Integer.class);

        int price = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = formatter.parse(data1);
            Date date2 = formatter.parse(data2);

            int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24)) + 1;
            price = days * priceOne;

        } catch (ParseException e) {
            e.printStackTrace();
        }


        jdbcTemplate.update("INSERT INTO Bron (u_id,r_id,data1,data2,price) VALUES(?,?,?,?,?)",u_id,r_id,data1,data2,price);
    }

}