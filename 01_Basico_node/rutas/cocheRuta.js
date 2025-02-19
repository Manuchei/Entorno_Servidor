const express = require("express");

const ruta = express.Router();

let coches = [
  { _id: 0, marca: "Renault", modelo: "R5", year: "1978" },
  { _id: 1, marca: "Renault", modelo: "R4", year: "1968" },
  { _id: 2, marca: "Seat", modelo: "600-D", year: "1972" },
];

ruta.get("/todos", (req, res) => {
  res.status(200).send(coches);
});

ruta.get("/marca/:marca", (req, res) => {
  const listaCoches = coches.filter((coche) => coche.marca == req.params.marca);
  res.status(200).send(listaCoches);
});

ruta.get("/uno/:id", (req, res) => {
  const coche = coches.find((coche) => coche._id == req.params.id);
  if (!coche) {
    res.status(404).send("Este coche no existe");
  } else {
    res.status(200).send(coche);
  }
});

module.exports = ruta;
