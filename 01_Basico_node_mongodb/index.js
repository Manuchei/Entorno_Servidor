const express = require("express")
const app = express();
const bodyParser = require("body-parser")
const mongoose = require("mongoose")
const rutaCoche = require("./rutas/cocheRuta")
const rutaUsuario = require("./rutas/usuarioRuta")


//middleware
app.use(express.json())
app.use(bodyParser.urlencoded({extended: true}))
app.use("/api/coches", rutaCoche)
app.use("/api/admon/usuarios", rutaUsuario)

// conexion a mongoose
mongoose.connect("mongodb://127.0.0.1:27017/bbdd-coches_2025")
.then(()=> console.log("Base de datos mongo conectada"))
.catch(() => console.log("Base de datos NOOO conectada"))

app.listen(3000, ()=> console.log("arrancando por el puerto 3000"))