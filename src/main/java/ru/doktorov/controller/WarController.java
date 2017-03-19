package ru.doktorov.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class WarController {
    @RequestMapping(value = "/war/{warNumber}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody int getContract(@PathVariable final int warNumber) {
        return warNumber;
    }
}