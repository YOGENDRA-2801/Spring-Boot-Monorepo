package com.yogendrayadav.codingshuttle.SpringBootWeb.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T>{
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data ;
    private ApiError error ;
}
