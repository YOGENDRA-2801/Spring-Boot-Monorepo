package com.yogendrayadav.codingshuttle.SpringBootWeb.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yogendrayadav.codingshuttle.SpringBootWeb.ValidationAnnotation.RoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id ;

    @NotEmpty(message = "Empty(length is zero) strings/collection/array are not allowed")
    @Size(min = 3, max = 18, message = "Please input in range of 3 to 18")
    private String name ;

    @Email
    private String email ;

    @Max(value = 100, message = "Maximum value can be 100")
    @Min(value = 21, message = "Minimum value can be 21")
    @NotNull(message = "This field cannot be null")
    private Integer age ;

    @PastOrPresent
    private LocalDate joiningDate ;

    @AssertTrue
    @JsonProperty("isEmployee")
    private Boolean isEmployee ;

    @Positive
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "99.99")
    @DecimalMax(value = "99999.99")
    private Double salary;

//    @Pattern(regexp = "^(ADMIN|USER)$")
    @RoleValidation
    @NotBlank(message = "After trimming the string is empty hence invalid")
    private String role;
}