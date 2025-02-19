const express = require("express");
const app = express();
const mongoose = require("mongoose")

const rutaCoches = require("./rutas/cocheRuta")

//middleware
app.use(express.json());
app.use("/api/coches", rutaCoches)

//conexion a mongoose
mongoose.connect("mongodb://127.0.0.1:27017/bbdd-coches_2025")
.then(() => console.log("Base de datos conectada"))
.catch(() => console.log("Base de datos NO conectada"))



app.listen(3000, () => console.log("Arrancando por el puerto 3000"));
