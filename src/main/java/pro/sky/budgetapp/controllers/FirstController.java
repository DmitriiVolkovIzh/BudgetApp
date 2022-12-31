package pro.sky.budgetapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FirstController {

    @GetMapping
    public String getApp(){
        return "Приложение запущено!";
    }

    @GetMapping("/info")
    public String page(){
        return "ФИО: Волков Дмитрий Петрович.**** \n" +
                "Название проекта: BudgetApp.****\n " +
                "Дата создания: 31.12.2022.**** \n" +
                "Приложение для формирования бюджета";
    }
}
