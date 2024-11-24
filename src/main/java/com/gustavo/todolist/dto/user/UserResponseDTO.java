package com.gustavo.todolist.dto.user;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;
}
