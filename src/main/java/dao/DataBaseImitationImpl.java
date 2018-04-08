package dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

//имитировал базу данных, коллекция мапа, она одна для всех пользователей, ключ - комната, значение - состояние лампочки

@Repository
public class DataBaseImitationImpl implements DataBaseImitation {

    private Map storeInformation = new HashMap<Integer, String>();


    public String getStatus(Integer roomNumber) {
        return (String) storeInformation.get(roomNumber);
    }

    public void saveBulbStatus(Integer roomNumber, String statusBulb) {
        storeInformation.put(roomNumber, statusBulb);
    }
}
