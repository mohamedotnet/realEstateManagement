package com.RealEstate.controller;

import com.RealEstate.model.*;
import com.RealEstate.service.CustomerService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected Customer customer;
    private Sale sale;

    private CustomerService getCustomerService() {
        return customerService;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @GetMapping("/Customer/login")
    public String login(Model m) {
        m.addAttribute("customer", new Customer());
        return "Customer/login";
    }

    @GetMapping("/Customer/register")
    public String register(Model m) {
        m.addAttribute("customer", new Customer());
        return "Customer/register";
    }

    @GetMapping("/Customer/changeProfilePicture")
    public String changeProfilePicture(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            m.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        return "Customer/changeProfilePicture";
    }

    @GetMapping("/Customer/fixAppointment")
    public String fixAppointment(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            m.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        List<Appointment> list = customerService.getAppointmentsList();
        Appointment[] array = new Appointment[list.size()];
        array = list.toArray(array);
        m.addAttribute("customer", this.customer);
        m.addAttribute("list", array);
        m.addAttribute("size", list.size());
        m.addAttribute("appointment", new Appointment());
        return "Customer/fixAppointment";
    }

    @GetMapping("/Customer/sellApartment")
    public String sellApartment(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            m.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        m.addAttribute("customer", this.customer);
        m.addAttribute("sale", new Sale());
        return "Customer/sellApartment";
    }

    @GetMapping("/Customer/myPurchases")
    public String myPurchases(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            m.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        List<Sale> list = customerService.getSalesList(customer.getUsername());
        Sale[] array = new Sale[list.size()];
        array = list.toArray(array);
        m.addAttribute("list", array);
        m.addAttribute("size", list.size());
        m.addAttribute("customer", this.customer);
        m.addAttribute("sale", new Sale());
        return "Customer/myPurchases";
    }

    @GetMapping("/Customer/customerSpace")
    public String customerSpace(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            m.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        m.addAttribute("customer", this.customer);
        return "Customer/customerSpace";
    }


    @GetMapping("/Customer/addPaymentReceipt")
    public String addPaymentReceipt(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            m.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        return "Customer/addPaymentReceipt";
    }

    @PostMapping ("/registerSuccess")
    public String registerSuccess(@Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "Customer/register";
        }
        model.addAttribute("customer", customer);
        if (getCustomerService().userExists(customer.getUsername()) ||
            getCustomerService().emailExists(customer.getEmail()) ||
            getCustomerService().idExists(customer.getIdNumber())
            ){
            if(getCustomerService().userExists(customer.getUsername()))
                model.addAttribute("user","UTILISATEUR EXISTANT!");

            if(getCustomerService().emailExists(customer.getEmail()))
                model.addAttribute("email","EMAIl EXISTANT!");

            if(getCustomerService().idExists(customer.getIdNumber()))
                model.addAttribute("id","NUMERO DE CARTE EXISTANT!");
            return "Customer/register";
        }
        getCustomerService().registerCustomer(customer);
        model.addAttribute("err","Inscription avec success");
        return "Customer/login";
    }

    @PostMapping("/loginSuccess")
    public String loginSuccess(@Valid @ModelAttribute("SpringWeb")Customer customer, BindingResult bindingResult, Model model,
                               HttpSession session) {
        if(bindingResult.hasErrors())
            return "Customer/login";
        if(getCustomerService().checkCustomerLogin(customer.getUsername(), customer.getPassword())){
            if (getCustomerService().isCustomerValide(customer.getUsername())){
                this.customer = customerService.getCustomerByUsername(customer.getUsername());
                session.setAttribute("username", this.customer.getUsername());
                session.setAttribute("role", this.customer.getRole());
                model.addAttribute("customer", this.customer);
                return "Customer/customerSpace";
            }else{
                model.addAttribute("customer", this.customer);
                model.addAttribute("active", "VOTRE COMPTE N'EST PAS ACTIVE!");
                return "Customer/login";
            }
        }
        model.addAttribute("customer", new Customer());
        model.addAttribute("err", "Nom d'utilisateur ou mot de passe incorrect");
        return "Customer/login";
    }

    @GetMapping("/Customer/cancelAppointment")
    public String cancelAppointment(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            m.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        List<Appointment> list = customerService.getAppointmentsListByCustomer(customer.getUsername());
        Appointment[] array = new Appointment[list.size()];
        array = list.toArray(array);
        m.addAttribute("list", array);
        m.addAttribute("size", list.size());
        m.addAttribute("appointment", new Appointment());
        return "Customer/cancelAppointment";
    }

    @PostMapping("/fixAppointmentSuccessC")
    public String fixAppointmentSuccess(@Valid @ModelAttribute("SpringWeb")Appointment appointment, BindingResult bindingResult, Model model,
                                        HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            model.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        if(bindingResult.hasErrors())
            return "Customer/fixAppointment";
        System.out.println(appointment.getCustomer());
        System.out.println(appointment.getReference());
        System.out.println(appointment.getAgent());
        customerService.addAppointment(appointment);
        model.addAttribute("appointment", appointment);
        return "Customer/customerSpace";
    }

    @PostMapping("/sellApartmentSuccess")
    public String sellApartmentSuccess(@Valid @ModelAttribute("SpringWeb")Sale sale, BindingResult bindingResult, Model model,
                                       HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            model.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        if(bindingResult.hasErrors())
            return "Customer/sellApartment";
        getCustomerService().makeSale(sale);
        model.addAttribute("sale", sale);
        return "userspace";
    }

    @PostMapping("/Customer/validatePayment")
    public String validatePayment(@Valid @ModelAttribute("SpringWeb")Sale sale, BindingResult bindingResult, Model model,
                                  HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            model.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        if(bindingResult.hasErrors())
            return "Customer/myPurchases";
        this.sale = sale;
        model.addAttribute("apartment", sale.getApartment());
        model.addAttribute("customer", customer);
        model.addAttribute("paymentReceipt",new PaymentReceipt());
        return "Customer/validatePayment";
    }

    @PostMapping("/cancelAppointmentSuccessC")
    public String cancelAppointmentSuccess(@Valid @ModelAttribute("SpringWeb")Appointment appointment, BindingResult bindingResult, Model model,
                                           HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            model.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        if(bindingResult.hasErrors())
            return "Customer/cancelAppointment";
        customerService.cancelAppointment(appointment.getReference());
        return "Customer/customerSpace";
    }


    @PostMapping("/validatePaymentSuccess")
    public String validatePaymentSuccess(@RequestParam("file") MultipartFile file, @RequestParam("value") int value,
                                         @RequestParam("bank") String bank, @RequestParam("date") String date,
                                         @RequestParam("time") String time, @RequestParam("customer") String customer, Model m,
                                         HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            m.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        PaymentReceipt paymentReceipt = customerService.createPaymentReceipt(file, value, bank, date, time, customer);
        customerService.validateSale(sale.getReference(), paymentReceipt);
        m.addAttribute("customer", this.customer);
        return "Customer/customerSpace";
    }

    @PostMapping("/changePictureSuccessC")
    public String changePictureSuccessC(@RequestParam("file") MultipartFile file, Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("customer")){
            m.addAttribute("customer", new Customer());
            return "Customer/login";
        }
        String picture = customerService.changeProfilePicture(file, this.customer.getUsername());
        this.customer.setPicture(picture);
        m.addAttribute("customer", this.customer);
        return "Customer/customerSpace";
    }


}
