package academy.devdojo.springboot2essentials.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnimePostRequestBody {

    // @NotNull(message = "The anime name cannot be null")
    @NotEmpty(message = "The anime name cannot be empty")
    private String name;
}
