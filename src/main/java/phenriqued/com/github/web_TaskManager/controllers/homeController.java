package phenriqued.com.github.web_TaskManager.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("taskManager")
public class homeController {

    @GetMapping
    public ResponseEntity homePage(){
        return ResponseEntity.ok().build();
    }


}
