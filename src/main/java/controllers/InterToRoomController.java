package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BulbServiceImpl;

@Controller
public class InterToRoomController {

    @Autowired
    private BulbServiceImpl bulbService;

    @RequestMapping(value = "/interToRoom", method = RequestMethod.GET)
    public String interToRoom() {
        return "redirect:/";
    }

    @RequestMapping(value = "/interToRoom", method = RequestMethod.POST)
    public String interToRoom(@RequestParam(name = "roomNumber") Integer roomNumber,
                              Model model){
        String statusBulb = bulbService.getStatus(roomNumber);
        statusBulb = setDefaultStatusIfNecessary(statusBulb);
        sendInformationToPage(roomNumber,statusBulb, model);
        return "room";
    }

    @RequestMapping(value = "/bulbSwitch", method = RequestMethod.POST)
    public String bulbSwitch(@RequestParam(name = "switch") String param,
                             @RequestParam(name = "room") int roomNumber,
                             Model model) {
        String statusBulb;
        if ("On".equals(param)) {
            statusBulb = "lamp-on";
        } else {
            statusBulb = "lamp-off";
        }
        sendInformationToPage(roomNumber,statusBulb, model);
        return "room";
    }

    @ResponseBody
    @RequestMapping(value = "/updateBulbStatus", method = RequestMethod.GET)
    public String updateBulbStatus(@RequestParam(name = "roomNumber") Integer roomNumber,
                                 Model model){
        String statusBulb = bulbService.getStatus(roomNumber);
        statusBulb = setDefaultStatusIfNecessary(statusBulb);
        return statusBulb;
    }

    private String setDefaultStatusIfNecessary(String statusBulb) {
        if ( statusBulb == null){
            statusBulb = "lamp-off";
        }
        return statusBulb;
    }

    private void sendInformationToPage(Integer roomNumber, String statusBulb, Model model) {
        bulbService.saveBulbStatus(roomNumber, statusBulb);
        model.addAttribute("roomNumber", roomNumber);
        model.addAttribute("className",statusBulb);
    }


}
