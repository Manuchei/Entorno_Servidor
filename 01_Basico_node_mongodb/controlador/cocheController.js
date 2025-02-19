const express = require("express")
const ruta = express.Router()
const Coche = require("../modelo/Coche")

const findAll = async (req, res) => {

    cocheList = await Coche.find()
    return res.status(200).send(cocheList)
}

module.exports = {
    findAll
}