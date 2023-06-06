package com.salvador.artapp.ui.common_comps



val menuList = listOf("Exhibits", "Artworks", "Random Art", "Search", "Favorites")

class MenuSelectionOptions(var index: Int, var name: String) {}

fun MenuOptions(): List<MenuSelectionOptions> {
    val menuOptions = mutableListOf<MenuSelectionOptions>()
    for(i in 0..menuList.size -1){
        menuOptions.add(MenuSelectionOptions(i, menuList[i]))
    }
    return menuOptions
}

