package com.restapi.superhero.controller;

import java.util.List;

import com.restapi.superhero.entity.Superhero;

public record ResponseObject(boolean success, List<String> errorList, Superhero superhero) {
}
