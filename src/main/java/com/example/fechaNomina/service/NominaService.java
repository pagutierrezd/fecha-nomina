package com.example.fechaNomina.service;

import java.time.LocalDate;

public interface NominaService {

    LocalDate calculateNextPayDate(String date);
    LocalDate getNextPayDate(LocalDate date);
    LocalDate adjustForNonWorkingDays(LocalDate date);
    boolean isNonWorkingDay(LocalDate date);
}
