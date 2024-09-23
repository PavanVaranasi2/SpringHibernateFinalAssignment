package hotel_app.dto;

import lombok.Getter;
import lombok.Setter;

public class TravelerDTO {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    private String phoneNumber;

    public TravelerDTO() {
    }

    public TravelerDTO(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = maskPhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = maskPhoneNumber(phoneNumber);
    }

    // Method to mask phone number: show first 2 and last 2 digits, hide the rest
    private String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() == 10) {
            return phoneNumber.substring(0, 2) + "******" + phoneNumber.substring(8);
        }
        return "****";
    }
}