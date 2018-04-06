package service;

import dao.DataBaseImitationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulbServiceImpl implements BulbService {

    @Autowired
    private DataBaseImitationImpl dataBaseImitation;

    public String getStatus(Integer roomNumber) {
        return dataBaseImitation.getStatus(roomNumber);
    }

    public void saveBulbStatus(Integer roomNumber, String statusBulb) {
        dataBaseImitation.saveBulbStatus(roomNumber, statusBulb);
    }
}
