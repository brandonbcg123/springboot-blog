package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MathController {

    @ResponseBody
    @GetMapping("/add/{numberOne}/and/{numberTwo}")
    public Double add(@PathVariable Double numberOne, @PathVariable Double numberTwo) {
        return numberOne + numberTwo;
    }

    @ResponseBody
    @GetMapping("/subtract/{numberTwo}/from/{numberOne}")
    public Double subtract(@PathVariable Double numberOne, @PathVariable Double numberTwo) {
        return numberOne - numberTwo;
    }

    @ResponseBody
    @GetMapping("/multiply/{numberOne}/and/{numberTwo}")
    public Double multiply(@PathVariable Double numberOne, @PathVariable Double numberTwo) {
        return numberOne * numberTwo;
    }

    @ResponseBody
    @GetMapping("/divide/{numberOne}/by/{numberTwo}")
    public Double  divide(@PathVariable Double numberOne, @PathVariable Double numberTwo) {
        return numberOne / numberTwo;
    }
}

