const mongoose = require("mongoose")

const cocheSchema = new mongoose.Schema({
    marca: {
        type: String,
        required: true
    },
    modelo: {
        type:String,
        required: true
    },
    precio: Number,
    anio: {
        type: Number,
        required: true,
        min: 2000
    },
    isVendido: {
        type: Boolean,
        default: false
    } ,
    extras : [String],

},
{
   versionKey: false,
   timestamps: true 
})

const Coche = mongoose.model('coche',cocheSchema)

module.exports = Coche;