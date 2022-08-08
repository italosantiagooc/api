package br.com.devjunior.api.controllers.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}
