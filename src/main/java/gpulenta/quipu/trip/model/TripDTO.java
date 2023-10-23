package gpulenta.quipu.trip.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {
    private Long id;
    private String origin;
    private String destination;
    private String date;
    private String username;
    private String firstName;
    private String lastName;
}
