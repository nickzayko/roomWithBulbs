package dao;

public interface DataBaseImitation {
    String getStatus(Integer roomNumber);
    void saveBulbStatus(Integer roomNumber, String statusBulb);
}
