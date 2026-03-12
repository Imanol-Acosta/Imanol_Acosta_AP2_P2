package ucne.edu.imanol_acosta_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ucne.edu.imanol_acosta_ap2_p2.presentation.jugador_list.JugadorListScreen
import ucne.edu.imanol_acosta_ap2_p2.presentation.jugador_form.JugadorFormScreen

@Composable
fun NavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.JugadorList
    ) {
        composable<Screen.JugadorList> {
            JugadorListScreen(
                createJugador = {
                    navHostController.navigate(Screen.JugadorForm(id = 0))
                },
                goToJugador = { id ->
                    navHostController.navigate(Screen.JugadorForm(id = id))
                }
            )
        }

        composable<Screen.JugadorForm> {
            JugadorFormScreen(
                navigateBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}
