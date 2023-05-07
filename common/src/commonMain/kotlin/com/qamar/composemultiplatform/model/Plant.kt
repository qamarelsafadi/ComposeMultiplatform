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

            return listOf(
                Plant(image = "Plants1.jpg", name = "Bird of paradise", ),
                Plant("Dracaena", image = "Plants2.jpg"),
                Plant("Palm", image = "Plants3.jpg"),
                Plant("Ficus", image = "Plants1.jpg"),
                Plant("Cedar", image = "Plants2.jpg"),
                Plant("Fiddle", image = "Plants3.jpg"),
            )
        }
    }

}

data class Properties(
    val propertyName: String,
    val percent: Float = (0 until 100).random().toFloat()/100
)
