package com.example.fechaNomina.service.impl;

import com.example.fechaNomina.service.NominaService;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class NominaServiceImpl implements NominaService {
    @Override
    public LocalDate calculateNextPayDate(String date) {
        //Parseamos el String a tipo fecha.
        LocalDate inputDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Obtener la siguiente fecha de pago (15 o el 30 del mes)
        LocalDate nextPayDate = getNextPayDate(inputDate);

        // Ajustar la fecha de pago si es necesario, si el día es día no habil.
        nextPayDate = adjustForNonWorkingDays(nextPayDate);

        return nextPayDate;    }
    @Override
    public LocalDate getNextPayDate(LocalDate date) {
        //Obtenemos el día de la fecha
        int day = date.getDayOfMonth();
        LocalDate nextPayDate;

        if (day <= 15) {
            //Nos dará la fecha del día 15 de ese mes.
            nextPayDate = date.withDayOfMonth(15);
        } else if (day >15 && day <=30 && 30 <= date.withDayOfMonth(date.lengthOfMonth()).getDayOfMonth()) {
            //Nos dará la fecha del día 30 de ese mes.
            nextPayDate = date.withDayOfMonth(30);
        } else {
            //validamos hasta que día llega el mes.
            nextPayDate = date.withDayOfMonth(date.lengthOfMonth());
        }


        //calculará la fecha que sigue de ese mes, ejemplo si es 31 de enero, la fecha sería 15 de febrero.
        if (nextPayDate.isBefore(date) || nextPayDate.equals(date) && day != 15 && day != 30 && date.getMonth().getValue() != 02) {
            nextPayDate = nextPayDate.plusMonths(1).withDayOfMonth(15);
        }

        return nextPayDate;    }
    @Override
    public LocalDate adjustForNonWorkingDays(LocalDate date) {
        //Invoca al método donde utilizamos la librería que tiene todo los festivos 2024 de Colombia, si la fecha a pasar
        //tiene festivo, se le asigna la fecha correspondiente.

        /* Evalua la fecha, si el día seleccionado es un festivo, se disminuye 1 día, se vuelve a recoorrer el mismo método
        para evaluar si la resta deja un día festivo, si es así, disminuye otro día, hasta llegar al día hábil.*/
        while (isNonWorkingDay(date)) {
            date = date.minusDays(1);
        }
        return date;
    }

    /**
     * Metodo para evaluar en la libraría que días no son habiles en el calendario 2024
     * @param date
     * @return
     */
    @Override
    public boolean isNonWorkingDay(LocalDate date) {
        if (date.equals(LocalDate.of(2024, 5, 30))) {
            return false;
        }
        return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                HolidayManager.getInstance(HolidayCalendar.COLOMBIA).getHolidays(date.getYear()).stream()
                        .anyMatch(holiday -> holiday.getDate().equals(date));
    }
}
