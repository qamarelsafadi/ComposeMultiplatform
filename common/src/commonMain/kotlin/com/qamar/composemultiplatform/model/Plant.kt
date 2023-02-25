package com.qamar.composemultiplatform.model


data class Plant(
    val name: String? = "",
    val properties: List<Properties> =
        listOf(
            Properties("Light"),
            Properties("Food"),
            Properties("Water"),
        ),
    val image: String? = null
) {

    companion object{
        fun getPlantList(): List<Plant> {

            //"Bird of paradise", "Dracaena", "Palm", "Ficus", "Cedar", "Fiddle"
            return listOf(
                Plant("Bird of paradise", image = "plant1.png"),
                Plant("Dracaena", image = "plant2.png"),
                Plant("Palm", image = "plant3.png"),
                Plant("Ficus", image = "plant1.png"),
                Plant("Cedar", image = "plant2.png"),
                Plant("Fiddle", image = "plant3.png"),
            )
        }
    }

}

data class Properties(
    val propertyName: String,
    val percent: Float = (0 until 100).random().toFloat()/100
)
