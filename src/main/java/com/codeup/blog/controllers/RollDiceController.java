package com.codeup.blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model viewModel) {

        int diceRoll = (int) (Math.random() * 6 + 1);

        viewModel.addAttribute("number", n);
        viewModel.addAttribute("diceRoll", diceRoll);

        return "roll-dice";

    }

}