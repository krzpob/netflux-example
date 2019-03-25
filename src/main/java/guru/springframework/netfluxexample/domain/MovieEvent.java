package guru.springframework.netfluxexample.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieEvent {

    private String movieId;
    private Date date;
}
