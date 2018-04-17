package com.RealEstate.controller;

import com.RealEstate.model.*;
import com.RealEstate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class AdminController{

    @Autowired
    protected AdminService adminService;

    @Autowired
    protected Admin admin;

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected AgentService agentService;

    @Autowired
    protected ManagerService managerService;

    @Autowired
    protected OperatorService operatorService;


    private AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @GetMapping("/Admin/login")
    public String login(Model m){
        m.addAttribute("admin", new Admin());
        return "Admin/login";
    }

    @PostMapping("/logout")
    public String logout(Model m, HttpSession session){
        session.invalidate();
        System.out.println(session);
        //m.addAttribute("admin", new Admin());
        return "index";
    }

    @GetMapping("/Admin/addAgent")
    public String addAgent(Model m, HttpSession session) {
        System.out.println(session.getAttribute("role"));
        System.out.println(session.getAttribute("username"));
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            System.out.println("Why!!");
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }

        m.addAttribute("agent", new Agent());
        return "Admin/addAgent";
    }

    @GetMapping("/Admin/addCustomer")
    public String addCustomer(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        m.addAttribute("customer", new Customer());
        return "Admin/addCustomer";
    }

    @GetMapping("/Admin/addManager")
    public String addManager(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        m.addAttribute("manager", new Manager());
        return "Admin/addManager";
    }

    @GetMapping("/Admin/addOperator")
    public String addOperator(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        m.addAttribute("operator", new Operator());
        return "Admin/addOperator";
    }

    @GetMapping("/Admin/adminSpace")
    public String adminSpace(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        m.addAttribute("admin", this.admin);
        return "Admin/adminSpace";
    }

    @GetMapping("/Admin/changeProfilePicture")
    public String changeProfilePicture(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        return "Admin/changeProfilePicture";
    }

    @GetMapping("/Admin/editAdmins")
    public String editAdmin(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Admin> list = adminService.getAdminsList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("admin", new Admin());
        return "Admin/editAdmins";
    }

    @GetMapping("/Admin/editCustomers")
    public String editCustomer(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Customer> list = adminService.getCustomersList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("customer", new Customer());
        return "Admin/editCustomers";
    }

    @GetMapping("/Admin/editAgents")
    public String editAgent(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Agent> list = adminService.getAgentsList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("agent", new Agent());
        return "Admin/editAgents";
    }

    @GetMapping("/Admin/editOperators")
    public String editOperator(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Operator> list = adminService.getOperatorsList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("operator", new Operator());
        return "Admin/editOperators";
    }

    @GetMapping("/Admin/editManagers")
    public String editManager(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Manager> list = adminService.getManagersList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("manager", new Manager());
        return "Admin/editManagers";
    }

    @GetMapping("/Admin/registerAdmin")
    public String registerAdmin(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        m.addAttribute("admin", new Admin());
        return "Admin/registerAdmin";
    }

    @GetMapping("/Admin/validateAccount")
    public String validateAccount(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Customer> list = adminService.getInactiveAccount();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("customer", new Customer());
        return "Admin/validateAccount";
    }

    @GetMapping("/Admin/removeCustomer")
    public String removeCustomer(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Customer> array = adminService.getCustomersList();
        m.addAttribute("array", array);
        m.addAttribute("size", array.size());
        m.addAttribute("customer", new Customer());
        return "Admin/removeCustomer";
    }

    @GetMapping("/Admin/removeAdmin")
    public String removeAdmin(Model m, HttpSession session) {
        System.out.println(session.getAttribute("username"));
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            System.out.println(session.getAttribute("role"));
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Admin> list = adminService.getAdminsList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("admin", new Admin());
        return "Admin/removeAdmin";
    }

    @GetMapping("/Admin/removeAgent")
    public String removeAgent(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Agent> list = adminService.getAgentsList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("agent", new Agent());
        return "Admin/removeAgent";
    }


    @GetMapping("/Admin/removeManager")
    public String removeManager(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Manager> list = adminService.getManagersList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("manager", new Manager());
        return "Admin/removeManager";
    }

    @GetMapping("/Admin/removeOperator")
    public String removeOperator(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        List<Operator> list = adminService.getOperatorsList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("operator", new Operator());
        return "Admin/removeOperator";
    }

    @PostMapping("/editCustomerForm")
    public String editCustomerForm(@Valid @ModelAttribute("SpringWeb")Customer customer, BindingResult bindingResult, Model model,
    HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editCustomers";
        model.addAttribute("customer", customer);
        return "Admin/editCustomer";
    }

    @PostMapping("/editCustomerSuccess")
    public String editCustomer(@Valid @ModelAttribute("SpringWeb")Customer customer, @RequestParam("id") int id, BindingResult bindingResult, Model model,
                               HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editCustomers";
        customer.setId(id);
        getAdminService().editCustomer(customer);
        model.addAttribute("customer", customer);
        return "Admin/editCustomer";
    }

    @PostMapping("/editAgentForm")
    public String editAgentForm(@Valid @ModelAttribute("SpringWeb")Agent agent, BindingResult bindingResult, Model model,
                                HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editAgents";
        model.addAttribute("agent", agent);
        return "Admin/editAgent";
    }

    @PostMapping("/editAgentSuccess")
    public String editAgent(@Valid @ModelAttribute("SpringWeb")Agent agent, @RequestParam("id") int id, BindingResult bindingResult, Model model,
                            HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editAgents";

        agent.setId(id);
        getAdminService().editAgent(agent);
        model.addAttribute("agent", agent);
        return "Admin/editAgent";
    }

    @PostMapping("/editAdminForm")
    public String editAdminForm(@Valid @ModelAttribute("SpringWeb")Admin admin, BindingResult bindingResult, Model model,
                                HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editAdmins";
        model.addAttribute("admin", admin);
        return "Admin/editAdmin";
    }

    @PostMapping("/editAdminSuccess")
    public String editAdmin(@Valid @ModelAttribute("SpringWeb")Admin admin, @RequestParam("id") int id, BindingResult bindingResult, Model model,
                            HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editAdmins";
        admin.setId(id);
        getAdminService().editAdmin(admin);
        model.addAttribute("admin", admin);
        return "Admin/editAdmin";
    }

    @PostMapping("/editOperatorForm")
    public String editOperatorForm(@Valid @ModelAttribute("SpringWeb")Operator operator, BindingResult bindingResult, Model model,
                                   HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editOperators";
        model.addAttribute("operator", operator);
        return "Admin/editOperator";
    }
    @PostMapping("/editOperatorSuccess")
    public String editOperator(@Valid @ModelAttribute("SpringWeb")Operator operator, @RequestParam("id") int id, BindingResult bindingResult, Model model,
                               HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editOperators";
        operator.setId(id);
        getAdminService().editOperator(operator);
        model.addAttribute("operator", operator);
        return "Admin/editOperator";
    }

    @PostMapping("/editManagerForm")
    public String editManagerForm(@Valid @ModelAttribute("SpringWeb")Manager manager, BindingResult bindingResult, Model model,
                                  HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editManagers";
        model.addAttribute("manager", manager);
        return "Admin/editManager";
    }

    @PostMapping("/editManagerSuccess")
    public String editManager(@Valid @ModelAttribute("SpringWeb")Manager manager, @RequestParam("id") int id, BindingResult bindingResult, Model model,
                              HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/editManagers";
        manager.setId(id);
        getAdminService().editManager(manager);
        model.addAttribute("manager", manager);
        return "Admin/editManager";
    }

    @PostMapping("/removeCustomerSuccess")
    public String removeCustomerSuccess(@Valid @ModelAttribute("SpringWeb")Customer customer, BindingResult bindingResult, Model model,
                                        HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/removeCustomer";

        getAdminService().removeCustomer(customer.getUsername());
        return "Admin/adminSpace";
    }

    @PostMapping("/removeManagerSuccess")
    public String removeManagerSuccess(@Valid @ModelAttribute("SpringWeb")Manager manager, BindingResult bindingResult, Model model,
                                       HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/removeManager";
        getAdminService().removeManager(manager.getUsername());
        return "Admin/adminSpace";
    }

    @PostMapping("/removeOperatorSuccess")
    public String removeOperatorSuccess(@Valid @ModelAttribute("SpringWeb")Operator operator, BindingResult bindingResult, Model model,
                                        HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/removeOperator";

        getAdminService().removeOperator(operator.getUsername());
        return "Admin/adminSpace";
    }

    @PostMapping("/removeAgentSuccess")
    public String removeAgentSuccess(@Valid @ModelAttribute("SpringWeb")Agent agent, BindingResult bindingResult, Model model,
                                     HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/removeAgent";

        getAdminService().removeAgent(agent.getUsername());
        return "Admin/adminSpace";
    }

    @PostMapping("/removeAdminSuccess")
    public String removeAdminSuccess(@Valid @ModelAttribute("SpringWeb")Admin admin, BindingResult bindingResult, Model model,
                                     HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/removeAdmin";

        getAdminService().removeAdmin(admin.getUsername());
        return "Admin/adminSpace";
    }

    @PostMapping("/validateAccountSuccess")
    public String validateAccountSuccess(@Valid @ModelAttribute("SpringWeb")Customer customer, BindingResult bindingResult, Model model,
                                         HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        if(bindingResult.hasErrors())
            return "Admin/validateAccount";

        getAdminService().validateAccount(customer.getUsername());
        model.addAttribute("customer", customer);
        return "Admin/adminSpace";
    }

    @PostMapping("/addManagerSuccess")
    public String addManagerSuccess(@RequestParam("name")String name, @RequestParam("lastName")String lastName, @RequestParam("username")String username,
                                     @RequestParam("sex")String sex, @RequestParam("birthday")String birthday, @RequestParam("phone")String phone,
                                     @RequestParam("email")String email, @RequestParam("password")String password,
                                     @RequestParam("address")String address, @RequestParam("idNumber")String idNumber, Model model, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        Manager manager = adminService.addManager(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
        if (managerService.userExists(manager.getUsername()) ||
                managerService.emailExists(manager.getEmail()) ||
                managerService.idExists(manager.getIdNumber())
                ){
            model.addAttribute("admin", admin);
            if(managerService.userExists(manager.getUsername())) {
                model.addAttribute("user","UTILISATEUR EXISTANT!");
            }

            if(managerService.emailExists(manager.getEmail()))
                model.addAttribute("email","EMAIl EXISTANT!");

            if(managerService.idExists(manager.getIdNumber()))
                model.addAttribute("id","NUMERO DE CARTE EXISTANT!");
            return "Admin/addManager";
        }
        adminService.addManager(manager);
        model.addAttribute("admin", admin);
        return "Admin/adminSpace";
    }

    @PostMapping("/addOperatorSuccess")
    public String addOperatorSuccess(@RequestParam("name")String name, @RequestParam("lastName")String lastName, @RequestParam("username")String username,
                                     @RequestParam("sex")String sex, @RequestParam("birthday")String birthday, @RequestParam("phone")String phone,
                                     @RequestParam("email")String email, @RequestParam("password")String password,
                                     @RequestParam("address")String address, @RequestParam("idNumber")String idNumber, Model model, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        Operator operator = adminService.addOperator(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
        if (operatorService.userExists(operator.getUsername()) ||
                operatorService.emailExists(operator.getEmail()) ||
                operatorService.idExists(operator.getIdNumber())
                ){
            model.addAttribute("operator", operator);
            if(operatorService.userExists(operator.getUsername()))
                model.addAttribute("user","UTILISATEUR EXISTANT!");

            if(operatorService.emailExists(operator.getEmail()))
                model.addAttribute("email","EMAIl EXISTANT!");

            if(operatorService.idExists(operator.getIdNumber()))
                model.addAttribute("id","NUMERO DE CARTE EXISTANT!");
            return "Admin/addOperator";
        }
        adminService.addOperator(operator);
        model.addAttribute("admin", this.admin);
        return "Admin/adminSpace";
    }

    @PostMapping("/addCustomerSuccess")
    public String addCustomerSuccess(@RequestParam("name")String name, @RequestParam("lastName")String lastName, @RequestParam("username")String username,
                                  @RequestParam("sex")String sex, @RequestParam("birthday")String birthday, @RequestParam("phone")String phone,
                                  @RequestParam("email")String email, @RequestParam("password")String password,
                                  @RequestParam("address")String address, @RequestParam("idNumber")String idNumber, Model model,
                                  HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        Customer customer = adminService.addCustomer(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
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
            return "Admin/addCustomer";
        }
        customerService.registerCustomer(customer);
        model.addAttribute("admin", session.getAttribute("username"));
        return "Admin/adminSpace";
    }

    @PostMapping("/registerAdminSuccess")
    public String registerAdminSuccess(@RequestParam("name")String name, @RequestParam("lastName")String lastName, @RequestParam("username")String username,
                                  @RequestParam("sex")String sex, @RequestParam("birthday")String birthday, @RequestParam("phone")String phone,
                                  @RequestParam("email")String email, @RequestParam("password")String password,
                                  @RequestParam("address")String address, @RequestParam("idNumber")String idNumber, Model model,
                                       HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        Admin admin = adminService.registerAdmin(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);

        model.addAttribute("admin", admin);
        if (adminService.userExists(admin.getUsername()) ||
                adminService.emailExists(admin.getEmail()) ||
                adminService.idExists(admin.getIdNumber())
                ){
            if(adminService.userExists(admin.getUsername()))
                model.addAttribute("user","UTILISATEUR EXISTANT!");

            if(adminService.emailExists(admin.getEmail()))
                model.addAttribute("email","EMAIl EXISTANT!");

            if(adminService.idExists(admin.getIdNumber()))
                model.addAttribute("id","NUMERO DE CARTE EXISTANT!");
            return "Admin/registerAdmin";
        }
        adminService.registerAdmin(admin);
        model.addAttribute("admin", session.getAttribute("username"));
        return "Admin/adminSpace";
    }

    @PostMapping("/addAgentSuccess")
    public String addAgentSuccess(@RequestParam("name")String name, @RequestParam("lastName")String lastName, @RequestParam("username")String username,
                                      @RequestParam("sex")String sex, @RequestParam("birthday")String birthday, @RequestParam("phone")String phone,
                                      @RequestParam("email")String email, @RequestParam("password")String password,
                                  @RequestParam("address")String address, @RequestParam("idNumber")String idNumber, Model model,
                                  HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            model.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        Agent agent = adminService.addAgent(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
        model.addAttribute("admin", admin);
        if (agentService.userExists(agent.getUsername()) ||
                agentService.emailExists(agent.getEmail()) ||
                agentService.idExists(agent.getIdNumber())
                ){
            if(agentService.userExists(agent.getUsername()))
                model.addAttribute("user","UTILISATEUR EXISTANT!");

            if(agentService.emailExists(agent.getEmail()))
                model.addAttribute("email","EMAIl EXISTANT!");

            if(agentService.idExists(agent.getIdNumber()))
                model.addAttribute("id","NUMERO DE CARTE EXISTANT!");
            return "Admin/addAgent";
        }
        adminService.addAgent(agent);
        model.addAttribute("admin", this.admin);
        return "Admin/adminSpace";
    }

    @PostMapping("loginSuccessAd")
    public String loginSuccess(@Valid @ModelAttribute("SpringWeb")Admin admin, BindingResult bindingResult, Model model,
                               HttpSession session){
        if(bindingResult.hasErrors())
            return "Admin/login";
        if(getAdminService().checkAdminLogin(admin.getUsername(), admin.getPassword())){
            this.admin = adminService.getAdminByUsername(admin.getUsername());
            session.setAttribute("username", this.admin.getUsername());
            System.out.println(this.admin.getRole());
            session.setAttribute("role", this.admin.getRole());
            model.addAttribute("admin", this.admin);
            return "Admin/adminSpace";
        }else{
            model.addAttribute("admin", new Admin());
            model.addAttribute("err", "Nom d'utilisateur ou mot de passe incorrect");
            return "Admin/login";
        }
    }

    @PostMapping("/changePictureSuccess")
    public String changePictureSuccess(@RequestParam("file") MultipartFile file, Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("admin")){
            m.addAttribute("admin", new Admin());
            return "Admin/login";
        }
        String picture = adminService.changeProfilePicture(file, this.admin.getUsername());
        this.admin.setPicture(picture);
        m.addAttribute("admin", this.admin);
        return "Admin/adminSpace";
    }
}
