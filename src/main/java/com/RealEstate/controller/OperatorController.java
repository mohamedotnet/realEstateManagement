package com.RealEstate.controller;


import com.RealEstate.model.*;
import com.RealEstate.service.CustomerService;
import com.RealEstate.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class OperatorController {

    @Autowired
    protected OperatorService operatorService;

    @Autowired
    protected Operator operator;

    @Autowired
    protected CustomerService customerService;

    public OperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }


    @GetMapping("/Operator/login")
    public String loginOperator(Model m){
        m.addAttribute("operator", new Operator());
        return "Operator/login";
    }

    @GetMapping("/Operator/addCustomer")
    public String addCustomer(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            m.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        m.addAttribute("operator", new Operator());
        return "Operator/addCustomer";
    }

    @GetMapping("/Operator/operatorSpace")
    public String operatorSpace(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            m.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        m.addAttribute("operator", operator);
        return "Operator/operatorSpace";
    }

    @GetMapping("/Operator/changeProfilePicture")
    public String changeProfilePicture(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            m.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        return "Operator/changeProfilePicture";
    }

    @GetMapping("/Operator/addApartment")
    public String addApartment(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            m.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        m.addAttribute("apartment", new Apartment());
        return "Operator/addApartment";
    }

    @GetMapping("/Operator/addBuilding")
    public String addBuilding(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            m.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        m.addAttribute("building", new Building());
        return "Operator/addBuilding";
    }

    @GetMapping("/Operator/addLocality")
    public String addLocality(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            m.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        m.addAttribute("locality", new Locality());
        return "Operator/addLocality";
    }

    @GetMapping("/Operator/fixAppointment")
    public String fixAppointment(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            m.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        List<Appointment> list = operatorService.createAppointmentsList();
        Appointment[] array = new Appointment[list.size()];
        array = list.toArray(array);
        m.addAttribute("list", array);
        m.addAttribute("size", list.size());
        m.addAttribute("appointment", new Appointment());
        return "Operator/fixAppointment";
    }

    @GetMapping("/Operator/cancelAppointment")
    public String cancelAppointment(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            m.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        List<Appointment> list = operatorService.getAppointmentsList();
        Appointment[] array = new Appointment[list.size()];
        array = list.toArray(array);
        m.addAttribute("list", array);
        m.addAttribute("size", list.size());
        m.addAttribute("appointment", new Appointment());
        return "Operator/cancelAppointment";
    }

    @PostMapping("/cancelAppointmentSuccess")
    public String cancelAppointmentSuccess(@Valid @ModelAttribute("SpringWeb")Appointment appointment, BindingResult bindingResult, Model model,
                                           HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            model.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        if(bindingResult.hasErrors())
            return "Operator/cancelAppointment";

        operatorService.cancelAppointment(appointment.getReference());
        return "Operator/operatorSpace";
    }

    @PostMapping("/fixAppointmentSuccess")
    public String fixAppointmentSuccess(@Valid @ModelAttribute("SpringWeb")Appointment appointment, BindingResult bindingResult, Model model,
                                        HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            model.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        if(bindingResult.hasErrors())
            return "Operator/fixAppointment";

        operatorService.addAppointment(appointment);
        model.addAttribute("appointment", appointment);
        return "Operator/operatorSpace";
    }

    @PostMapping("/addCustomerS")
    public String addCustomerSuccess(@RequestParam("name")String name, @RequestParam("lastName")String lastName,
                                     @RequestParam("username")String username, @RequestParam("sex")String sex,
                                     @RequestParam("birthday")String birthday, @RequestParam("phone")String phone,
                                     @RequestParam("email")String email, @RequestParam("password")String password,
                                     @RequestParam("address")String address,
                                     @RequestParam("idNumber")String idNumber, Model model, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            model.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        Customer customer = operatorService.createCustomer(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
        model.addAttribute("customer", customer);
        if (customerService.userExists(customer.getUsername()) ||
                customerService.emailExists(customer.getEmail()) ||
                customerService.idExists(customer.getIdNumber())
                ){
            if(customerService.userExists(customer.getUsername()))
                model.addAttribute("user","UTILISATEUR EXISTANT!");

            if(customerService.emailExists(customer.getEmail()))
                model.addAttribute("email","EMAIl EXISTANT!");

            if(customerService.idExists(customer.getIdNumber()))
                model.addAttribute("id","NUMERO DE CARTE EXISTANT!");
            return "Operator/addCustomer";
        }
        customerService.registerCustomer(customer);
        model.addAttribute("operator", operator);
        model.addAttribute("customer", customer);
        return "Operator/operatorSpace";
    }

    @PostMapping("/addBuildingSuccess")
    public String addBuildingSuccess(@Valid @ModelAttribute("SpringWeb")Building building, BindingResult bindingResult,
                                     Model model, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            model.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        if(bindingResult.hasErrors())
            return "Operator/addBuilding";

        operatorService.addBuilding(building);
        model.addAttribute("building", building);
        return "Operator/operatorSpace";
    }

    @PostMapping("/addLocalitySuccess")
    public String addLocalitySuccess(@Valid @ModelAttribute("SpringWeb")Locality locality, BindingResult bindingResult,
                                     Model model, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            model.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        if(bindingResult.hasErrors())
            return "Operator/addLocality";

        operatorService.addLocality(locality);
        model.addAttribute("locality", locality);
        return "Operator/operatorSpace";
    }

    @PostMapping("/LoginSuccessO")
    public String loginSuccessO(@Valid @ModelAttribute("SpringWeb")Operator operator, BindingResult bindingResult,
                                Model model, HttpSession session) {
        if(bindingResult.hasErrors())
            return "Operator/login";

        if(operatorService.checkOperatorLogin(operator.getUsername(), operator.getPassword())){
            this.operator = operatorService.getOperatorByUsername(operator.getUsername());
            session.setAttribute("username", this.operator.getUsername());
            session.setAttribute("role", this.operator.getRole());
            model.addAttribute("operator", this.operator);
            return "Operator/operatorSpace";
        }else{
            model.addAttribute("operator", new Operator());
            model.addAttribute("err", "Nom d'utilisateur ou mot de passe incorrect");
            return "Operator/login";
        }
    }

    @PostMapping("/changePictureSuccessO")
    public String changeProfilePicture(@RequestParam("file") MultipartFile file, Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            m.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        String picture = operatorService.changeProfilePicture(file, this.operator.getUsername());
        this.operator.setPicture(picture);
        m.addAttribute("operator", this.operator);
        return "Operator/operatorSpace";
    }

    @PostMapping("/addApartmentSuccess")
    public String addApartmentSuccess(@RequestParam("reference")String reference, @RequestParam("price")String price,
                                      @RequestParam("floor")String floor, @RequestParam("type")String type,
                                      @RequestParam("nbrRoom")String nbrRoom, @RequestParam("building")String building,
                                      @RequestParam("surface")String surface, @RequestParam("nbrBalcony")String nbrBalcony,
                                      @RequestParam("picture")MultipartFile [] pictures, Model model, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("operator")){
            model.addAttribute("operator", new Operator());
            return "Operator/login";
        }
        Apartment apartment = operatorService.createApartment(reference, price, floor, type, nbrRoom, building, surface, nbrBalcony, pictures);
        operatorService.addApartment(apartment);
        model.addAttribute("apartment", apartment);
        return "Operator/operatorSpace";
    }


}
