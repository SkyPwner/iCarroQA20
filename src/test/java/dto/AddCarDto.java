package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AddCarDto {
    String location;
    String manufacture;
    String model;
    Integer year;
    String fuel;
    Integer seats;
    String carClass;
    String serialNumber;
    Double price;
    String about;
    String photo;
}