package ucne.edu.imanol_acosta_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ucne.edu.imanol_acosta_ap2_p2.presentation.navigation.NavHost
import ucne.edu.imanol_acosta_ap2_p2.ui.theme.Imanol_Acosta_AP2_P2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Imanol_Acosta_AP2_P2Theme {
                val navController = rememberNavController()
                NavHost(navHostController = navController)
            }
        }
    }
}