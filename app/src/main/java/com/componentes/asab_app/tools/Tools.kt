package com.componentes.asab_app.tools


import kotlin.reflect.full.memberProperties
class Tools {

    fun objectToMap(objeto: Any): Map<String, Any?> {
        val mapa = mutableMapOf<String, Any?>()
        for (propiedad in objeto::class.memberProperties) {
            val valor = propiedad.call(objeto)
            mapa[propiedad.name] = valor
        }
        return mapa
    }
}