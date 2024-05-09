package com.servicestationmanagement.advices;

import java.time.LocalDate;

public record ApplicationError (
  String message,
  LocalDate date,
  int status
){}
