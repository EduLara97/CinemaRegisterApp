package com.example.prosegurchallengeapplication.presentation.composables.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.helpers.BASIC_PRICE
import com.example.prosegurchallengeapplication.helpers.DAYS
import com.example.prosegurchallengeapplication.helpers.DOMINGO
import com.example.prosegurchallengeapplication.helpers.JUEVES
import com.example.prosegurchallengeapplication.helpers.LUNES
import com.example.prosegurchallengeapplication.helpers.MARTES
import com.example.prosegurchallengeapplication.helpers.MIERCOLES
import com.example.prosegurchallengeapplication.helpers.MOVIES_BY_ROOM
import com.example.prosegurchallengeapplication.helpers.ROOM_LIST
import com.example.prosegurchallengeapplication.helpers.SABADO
import com.example.prosegurchallengeapplication.helpers.VIERNES
import com.example.prosegurchallengeapplication.presentation.composables.components.GenericDropDown
import com.example.prosegurchallengeapplication.presentation.viewmodel.RegisterViewModel
import com.example.prosegurchallengeapplication.ui.theme.ProsegurChallengeApplicationTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel,
    navController: NavHostController
) {

    var textPrice by remember { mutableStateOf("") }
    var textMovie by remember { mutableStateOf("") }
    var textTicketAmount by remember { mutableStateOf("") }
    var textDNI by remember { mutableStateOf("") }
    var roomNumber by remember { mutableStateOf(0) }
    var day by remember { mutableStateOf("") }
    var messageError by remember { mutableStateOf("") }
    var openDialog by remember { mutableStateOf(false) }

    val uiState by registerViewModel.uiState.collectAsState()

    Column (modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        Text (
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleLarge,
            text = "Register Purchase Form"
        )
        Text(
            text = "* Precio base de las entradas $BASIC_PRICE soles",
            style = MaterialTheme.typography.bodySmall
        )
        GenericDropDown(optionList = ROOM_LIST, placeHolder = "Room Number") {
            roomNumber = it.toInt()
            textMovie = MOVIES_BY_ROOM[it] ?: ""
        }
        GenericDropDown(optionList = DAYS, placeHolder = "Day") {
            day = it
            textPrice = calculateFinalPrice(it).toString()
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next),
            value = textPrice,
            onValueChange = { textPrice = it},
            readOnly = true,
            placeholder = { Text(text = "Ticket Price")})
        TextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next),
            value = textMovie,
            onValueChange = { textMovie = it},
            readOnly = true,
            placeholder = { Text(text = "Movie")})
        TextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            value = textTicketAmount,
            onValueChange = { if (it.length <= 2) textTicketAmount = it},
            singleLine = true,
            placeholder = { Text(text = "Ticket Amount")})
        TextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            value = textDNI,
            onValueChange = { if (it.length <= 8) textDNI = it},
            singleLine = true,
            placeholder = { Text(text = "DNI")}
        )
        if(uiState.onErrorCountRoom) {
            Text(text = uiState.errorMessage)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val messageCheck = checkForm(textTicketAmount, roomNumber, day, textDNI)
                if (messageCheck.isEmpty()) {
                    registerViewModel.registerErrorMessage("")
                    registerViewModel.registerPurchase(
                        RegisterModel(
                            id = 0,
                            roomNumber = roomNumber,
                            day = day,
                            ticketPrice = textPrice.toDouble(),
                            ticketsAmount = textTicketAmount.toInt(),
                            totalPrice = textPrice.toDouble() * textTicketAmount.toInt(),
                            userDNI = textDNI
                        )
                    )
                } else {
                    registerViewModel.registerErrorMessage(messageCheck)
                }
            }
        ) {
            Text(text = "Register")
        }
    }

    if (openDialog) {
        Dialog(onDismissRequest = { }) {
            Card (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)){
                Text(modifier = Modifier.padding(24.dp), text = "Registro completado")
            }
        }
    }

    if (uiState.onCompleteSave) {
        LaunchedEffect(
            key1 = Unit, block = {
                openDialog = true
                delay(1500)
                openDialog = false
                registerViewModel.resetUiState()
                navController.popBackStack()
            }
        )
    }

    BackHandler(true) {
        registerViewModel.resetUiState()
        navController.popBackStack()
    }
}

private fun calculateFinalPrice(day: String) : Double{
    return when(day){
        LUNES, MIERCOLES, JUEVES -> BASIC_PRICE * 0.7
        MARTES -> BASIC_PRICE * 0.5
        VIERNES, SABADO, DOMINGO -> BASIC_PRICE * 1.4
        else -> BASIC_PRICE.toDouble()
    }
}

private fun checkForm(textTicketAmount: String, roomNumber: Int, day: String, dni: String) : String {
    if (roomNumber == 0){
        return "Debe seleccionar una sala"
    }
    if(day.isEmpty()){
        return "Debe seleccionar un dia"
    }
    if (textTicketAmount.isEmpty()){
        return "Debe ingresar una cantidad de entradas"
    }
    if (textTicketAmount.toInt() < 0 || textTicketAmount.toInt() > 60 ) {
        return "La cantidad de entradas debe estar entre 1 y 60"
    }
    if (dni.isEmpty() || dni.length != 8) {
        return "Debe ingresar un DNI correcto"
    }
    return ""
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    ProsegurChallengeApplicationTheme {
        Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            RegisterScreen(registerViewModel = viewModel(), navController = rememberNavController())
        }
    }

}