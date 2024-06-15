package com.example.fechaNomina.controller;

import com.example.fechaNomina.service.NominaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class NominaController {

    private final NominaService nominaService;

    /**
     * Este endPoint se encarga de retornar la fecha de la nómina, según corresponda en el mes.
     * Teniendo en cuenta que si el (15 o 30) es festivo, devolverá el día habil anterior al día.
     * @param inputDate example 2024-06-30
     * @return
     */
    @GetMapping("/getPayrollDate")
    public String getPayrollDate(@RequestParam String inputDate) {
        LocalDate nextPayDate = nominaService.calculateNextPayDate(inputDate);
        return "La fecha de pago de tu nómina es: " + nextPayDate;
    }

}
