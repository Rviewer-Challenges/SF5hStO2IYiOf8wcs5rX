package com.esaudev.hackathonmoure.data.local

import com.esaudev.hackathonmoure.R
import com.esaudev.hackathonmoure.domain.model.SingleContentTopic

object ContentTopics {
    val contentTopicList = listOf(
        SingleContentTopic(
            title = "Constraint layout basics",
            description = "Veamos que !@#%^ es el constraint layout, que usos podemos darle y como implementarlo de la mejor manera.",
            iconRes = R.drawable.ic_beginner
        ),
        SingleContentTopic(
            title = "Layout animations",
            description = "¿Sabias que puedes agregar animaciones a los cambios de visibilidad, o de constraints en tus vistas de una manera facilisima?",
            iconRes = R.drawable.ic_beginner
        ),
        SingleContentTopic(
            title = "ViewModels",
            description = "En esta seccion veremos que son los view model y la mejor forma de implementarlos.",
            iconRes = R.drawable.ic_intermediate
        ),
        SingleContentTopic(
            title = "RecyclerView animations",
            description = "Basicamente como hacer la animacion que acabas de ver.",
            iconRes = R.drawable.ic_intermediate
        ),
        SingleContentTopic(
            title = "Android & Firebase 100% amor",
            description = "En esta seccion veremos que necesitamos para conectar nuestra aplicacion android con Firebase y que usos podemos darle.",
            iconRes = R.drawable.ic_advance
        ),
        SingleContentTopic(
            title = "La debuggeacion",
            description = "Nos volveremos cirujanos android (dejaremos de imprimir en consola).",
            iconRes = R.drawable.ic_advance
        ),
    )
}