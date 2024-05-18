package com.example.highperformancespringdatajpa.dto;

import jakarta.validation.constraints.NotEmpty;

public record PersonDto(@NotEmpty String id, @NotEmpty(message = "Name cannot be empty")  String name) {

}
