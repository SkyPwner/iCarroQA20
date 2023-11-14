package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AddCarDto {
    String city;
    String manufacture;
    String model;
    Integer year;
    String fuel;
    Integer seats;
    String carClass;
    String serialNumber;
    Double pricePerDay;
    String about;
    String photo;
}
