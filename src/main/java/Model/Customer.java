package Model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Customer {
    private String customerId;
    private String title;
    private String customerName;
    private LocalDate DOB;
    private double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}
