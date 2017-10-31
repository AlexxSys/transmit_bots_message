package ru.alexxsys.transmit_bots_message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.alexxsys.transmit_bots_message.entity.History;
import ru.alexxsys.transmit_bots_message.repository.HistoryRepository;

import java.util.List;

@RestController
public class HistoryController {
    private final HistoryRepository repositoryHistory;

    @Autowired
    private HistoryController(HistoryRepository repositoryHistory) {
        this.repositoryHistory = repositoryHistory;
    }

    @RequestMapping(path = "/show-jurnal", method = RequestMethod.GET)
    public List<History> showHistory(Model model){
//                              @RequestParam Map<String, String> parameters){

        return repositoryHistory.findAll();
    }
}
