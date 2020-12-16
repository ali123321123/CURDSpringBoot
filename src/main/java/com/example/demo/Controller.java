package com.example.demo;

import com.example.demo.Model.User;
import com.example.demo.repostory.UserRepostory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(Controller.BASE_URL)
public class Controller {
    public static final String BASE_URL = "/api/v1/User";
    UserRepostory userRepostory;

    public Controller(UserRepostory userRepostory) {
        this.userRepostory = userRepostory;
    }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddUser(@RequestBody User user) throws Exception {

        userRepostory.save(user);
        return "User added";

    }

    @PostMapping("/deleteUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String DeleteUser(@RequestParam("id") Long id) throws Exception {

        userRepostory.deleteById(id);
        return "User deleted";

    }

    @GetMapping("/getAll")
    public List<User> GetAll() throws Exception {

        List<User> userList = userRepostory.findAll();
        System.out.print(Arrays.toString(userList.toArray()));
        return userList;

    }

    @PostMapping("/UpdateUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String UpdateUser(@RequestBody User user) throws Exception {

        User userToEdit = userRepostory.findById(user.getId()).get();
        userToEdit.setFirstName(user.getFirstName());
        userToEdit.setLastName(user.getLastName());

        userRepostory.save(userToEdit);
        return "User Updated";

    }


}
