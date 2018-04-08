package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BulbServiceImpl;

//обработчик запросов

@Controller
public class InterToRoomController {

    @Autowired
    private BulbServiceImpl bulbService;

    //при отправлении  гет запроса редиректит на /
    @RequestMapping(value = "/interToRoom", method = RequestMethod.GET)
    public String interToRoom() {
        return "redirect:/";
    }

    //для входа в комнату
    @RequestMapping(value = "/interToRoom", method = RequestMethod.POST)
    public String interToRoom(@RequestParam(name = "roomNumber") Integer roomNumber,
                              Model model){
        String statusBulb = bulbService.getStatus(roomNumber);
        statusBulb = setDefaultStatusIfNecessary(statusBulb);
        sendInformationToPage(roomNumber,statusBulb, model);
        return "room";
    }

    //обработчик включения и выключения лампочки
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

    //проверяет состояние лампочки, реализовано через аякс запросы , в идеале можно сделать через WebSocket
    @ResponseBody
    @RequestMapping(value = "/updateBulbStatus", method = RequestMethod.GET)
    public String updateBulbStatus(@RequestParam(name = "roomNumber") Integer roomNumber,
                                 Model model){
        String statusBulb = bulbService.getStatus(roomNumber);
        statusBulb = setDefaultStatusIfNecessary(statusBulb);
        return statusBulb;
    }

    //поумолчанию, если зашли вперые и состояние лампочки в нашей имитациооной базе данных непрописано, тоустанавливаем
    // дефолтное значение, а именно, лампочка выключена
    private String setDefaultStatusIfNecessary(String statusBulb) {
        if ( statusBulb == null){
            statusBulb = "lamp-off";
        }
        return statusBulb;
    }

    //общий для всех предыдущих методов, для отправления данных на клиент
    private void sendInformationToPage(Integer roomNumber, String statusBulb, Model model) {
        bulbService.saveBulbStatus(roomNumber, statusBulb);
        model.addAttribute("roomNumber", roomNumber);
        model.addAttribute("className",statusBulb);
    }


}
