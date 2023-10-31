package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDtoLombok {
    String name;
    String lastName;
    String email;
    String password;

}
