const express = require("express")
const ruta = express.Router()
const Coche = require("../modelo/Coche")
const coches = require("../bbdd/coches")

const eliminar = async (req, res) => {
    try{
         
         result = await Coche.findByIdAndDelete(req.params.id)

         if(!result)
            return res.status(404).send("Coche no existe")

         return   res.status(200).send("Coche eliminado");

     }catch(err){
         return res.status(500).json({mensaje: "Coche no se ha podido eliminar", err})
 }
 }
const modificar = async (req, res) => {
    try{
         const coche = req.body;
         result = await Coche.findByIdAndUpdate(req.params.id, coche, {new:true, runValidators: true})

         if(!result)
            return res.status(404).send("Coche no existe")


         return   res.status(200).send(result);
     }catch(err){
         return res.status(500).json({mensaje: "error interno", err})
 }
 }


const alta = async (req, res) => {
   try{
        const coche = req.body;
        cocheNuevo = new Coche(coche);
        result = await cocheNuevo.save();
        res.status(200).send(result);
    }catch(err){
        return res.status(500).json({mensaje: "error interno", err})
}
}


const findByPrecioMayor = async (req, res) => {
    const precioBuscado = req.params.precio
    const coches = await Coche.find({precio: {$gt:precioBuscado}})
    return res.status(200).send(coches);
}

const findByMarca = async (req, res) => {
    const coches = await Coche.find({marca: req.params.marca})
    return res.status(200).send(coches);
}
const findAll = async (req, res) => {
    result = await Coche.find()

    return res.status(200).send(result);
   

}

const findById = async (req, res) => {
  try{
    const coche = await Coche.findById(req.params.id)

    if (!coche)
        return res.status(404).send("Coche no existe")


    return res.status(200).send(coche);
}catch(err){
    return res.status(500).json({mensaje: "error interno", err})
}

}

const cargaInicial = async (req, res) => {

    await Coche.insertMany(coches)
    return res.status(200).send("Carga finalizada");

}





module.exports = {
    findAll,
    cargaInicial,
    findById,
    findByMarca,
    findByPrecioMayor,
    alta,
    modificar,
    eliminar
}