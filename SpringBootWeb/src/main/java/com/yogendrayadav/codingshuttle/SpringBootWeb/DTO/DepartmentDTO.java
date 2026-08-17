package com.yogendrayadav.codingshuttle.SpringBootWeb.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yogendrayadav.codingshuttle.SpringBootWeb.ValidationAnnotation.Password;
import com.yogendrayadav.codingshuttle.SpringBootWeb.ValidationAnnotation.Prime;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id ;

    @NotBlank(message = "String cannot be blank")
    @Size(min = 3, max = 12, message = "should be between range of 3-12")
    private String title ;

    @AssertTrue(message = "Can only be true")
    @JsonProperty("isActive")
    private Boolean isActive ;

    @PastOrPresent(message = "Date can be past or present")
    private LocalDate createdAt ;

    @Min(value = 3, message = "Minimum digit should be 3")
    @Max(value = 100, message = "Maximum digit can be 100")
    @Prime
    private Integer empId ;

    @NotNull
    @Password
    private String deptPassCode ;
}
