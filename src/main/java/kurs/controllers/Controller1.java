package kurs.controllers;

import kurs.dao.PersonDAO;
import kurs.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * @author Neil Alishev
 */
@Controller
@SessionAttributes(value = "Session")
@RequestMapping("/ARGAN")
public class Controller1 {

    private final PersonDAO personDAO;

    @Autowired
    public Controller1(PersonDAO personDAO) { this.personDAO = personDAO; }


    @GetMapping()
    public String start(Model model,SessionStatus status) {
        status.setComplete();
        //Базовая страничка с 2 кнопками
        return "ARGAN/start";
    }

    @GetMapping("/registration")
    public String Regis(Model model){
        //кнопка при нажатии добавляет в систему
        model.addAttribute("person",new Person());
        return "ARGAN/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "ARGAN/registration";
        }
        personDAO.addUser(person);
        return "redirect:/ARGAN";
    }


    @GetMapping("/login")
    public String Logis(Model model){
        //кнопка при нажатии авторизация в системе
        model.addAttribute("LoginPassword",new LoginPassword());
        return "ARGAN/login";
    }


    @PostMapping("/login")
    public String Initialization(@ModelAttribute("LoginPassword") LoginPassword loginPassword,Model model){
        Person person = personDAO.CheckUser(loginPassword);
        String URL = "redirect:/ARGAN";
        if(person != null){
            Search search = new Search(person.getId());
            model.addAttribute("Session",search);
            URL = "redirect:/ARGAN/lk";
        }
        return URL;
    }

    @GetMapping("/lk")
    public String Lk(Model model,@ModelAttribute("Session") Search search){
        //инфо о пользователе и кнопка при нажатии в поиск
        model.addAttribute("broninfo",personDAO.getBronInfo(search.getId()));
        model.addAttribute("lkinfo",personDAO.getInfo(search.getId()));
        return "ARGAN/lk";
    }

    @GetMapping("/history")
    public String History(Model model,@ModelAttribute("Session") Search search){
        //инфо о бронях пользователя
        model.addAttribute("broninfoh",personDAO.getBronInfoHistory(search.getId()));
        return "ARGAN/history";
    }

    @PostMapping("/lk/{ID}")
    public String Unbron(@PathVariable int ID,Model model,@ModelAttribute("Session") Search search){
        personDAO.UnBron(ID);

        return "redirect:/ARGAN/lk";
    }


    @GetMapping("/search")
    public String Search(Model model,@ModelAttribute("Session") Search search){
        //Страница поиска кнопка перекида в Отели
        return "ARGAN/search";
    }

    @PostMapping("/search")
    public String Searching(Model model,@ModelAttribute("Session") @Valid Search search, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "ARGAN/search";
        }
        search.setRooms(personDAO.searchHotelID(search));

        return "redirect:/ARGAN/hotels";
    }

    @GetMapping("/hotels")
    public String Hotels(Model model,@ModelAttribute("Session") Search search){
        model.addAttribute("hotels",personDAO.searchHotel(search.getRooms()));

        return "ARGAN/hotels";
    }

    @GetMapping("/rooms/{ID}")
    public String Rooms(@PathVariable int ID,Model model,@ModelAttribute("Session") Search search){
        model.addAttribute("rooms",personDAO.searchRooms(ID,search.getRooms()));

        return "/ARGAN/rooms";
    }

    @PostMapping("/{ID}")
    public String Room(@PathVariable int ID,Model model,@ModelAttribute("Session") Search search){

        personDAO.addBron(search.getId(),ID,search.getData1(),search.getData2());

        return "redirect:/ARGAN/lk";
    }

}
