//package com.salvador.artapp.ui.common_comps
//
//import androidx.compose.material.BottomNavigation
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material.Icon
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.currentBackStackEntryAsState
//import com.salvador.artapp.ui.navigation.NavigationScreens
//
//@Composable
//fun AppBottomNavBar(
//    navController: NavHostController,
//    items: List<NavigationScreens>
//) {
//   BottomNavigation {
////       val currentRoute = currentRoute(navController)
//       items.forEach { screen ->
//           BottomNavigationItem(
//               selected = currentRoute == screen.route ,
//               label = { Text(text = screen.route)},
//               icon = {Icon(screen.icon, contentDescription = "")},
//               alwaysShowLabel = false,
//               onClick = {
//                   if(currentRoute != screen.route) {
//                       navController.navigate(screen.route)
//                   }
//               })
//       }
//   }
//
//}
//
////@Composable
////fun currentRoute(navController: NavHostController): String? {
////    val navBackStackEntry by navController.currentBackStackEntryAsState()
////    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
////}