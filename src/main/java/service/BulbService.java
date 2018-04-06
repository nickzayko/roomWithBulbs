package service;

public interface BulbService {
    String getStatus(Integer roomNumber);
    void saveBulbStatus(Integer roomNumber, String statusBulb);
}
