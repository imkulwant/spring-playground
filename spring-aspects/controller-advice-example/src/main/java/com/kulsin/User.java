package com.kulsin;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

}
