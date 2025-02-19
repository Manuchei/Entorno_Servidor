const express = require("express");
const app = express();

const rutaCoches = require("./rutas/cocheRuta")

//middleware
app.use(express.json());
app.use("/api/coches", rutaCoches)

app.get("/saludo", (request, res) => {
  res.send("Hola me llamo Manuel y soy un alumno");
});

app.get("/saludo/:nombre/:edad", (request, res) => {
  let nombre = request.params.nombre;
  let edad = request.params.edad;

  res.send(`Hola me llamo ${nombre} y tengo ${edad} años`);
});

app.get("/saludo/profesor", (request, res) => {
  let nombre = request.query.nombre;
  let edad = request.query.edad;

  res.send(`Hola me llamo ${nombre} y tengo ${edad} años`);
});

app.listen(3000, () => console.log("Arrancando por el puerto 3000"));
