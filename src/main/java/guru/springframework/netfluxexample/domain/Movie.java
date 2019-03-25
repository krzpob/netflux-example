package guru.springframework.netfluxexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private String id;

    @NonNull
    private String title;
}
