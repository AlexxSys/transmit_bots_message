package ru.alexxsys.transmit_bots_message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import ru.alexxsys.transmit_bots_message.repository.HistoryRepository;

import java.util.Map;


@Controller
public class HistoryController {
    private final HistoryRepository repository;

    @Autowired
    private HistoryController(HistoryRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/show-jurnal", method = RequestMethod.GET)
    public String showHistory(Model model){
//                              @RequestParam Map<String, String> parameters){

        return "history";
    }
}
