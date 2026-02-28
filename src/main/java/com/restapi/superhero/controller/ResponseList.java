package com.restapi.superhero.controller;

import java.util.List;

import com.restapi.superhero.entity.Superhero;

public record ResponseList(boolean success, List<String> errorList, List<Superhero> list) {
}
