package sia.tacocloud.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TacoDto {

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;
}
