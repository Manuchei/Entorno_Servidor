const express = require("express");

const ruta = express.Router();

const cocheCOntroller = require("../controlador/cocheController")

ruta.get("/todos", cochceController.findAll);

// ruta.post("/alta", cochceController.alta);

// ruta.get("/uno/:id", cochceController.findById);

// ruta.get("/marca/:marca", cocheController.findByMarca);

module.exports = ruta;
