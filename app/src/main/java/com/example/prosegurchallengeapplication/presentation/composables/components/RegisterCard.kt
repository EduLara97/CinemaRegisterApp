package com.example.prosegurchallengeapplication.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prosegurchallengeapplication.R
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.ui.theme.ProsegurChallengeApplicationTheme

@Composable
fun RegisterCard(
    modifier: Modifier = Modifier,
    register: RegisterModel,
    deleteRegister: (RegisterModel) -> Unit
){

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            RegisterField(title = "Room number: ", value = register.roomNumber.toString())
            RegisterField(title = "Day: ", value = register.day)
            RegisterField(title = "Ticket price: ", value = register.ticketPrice.toString())
            RegisterField(title = "Tickets amount: ", value = register.ticketsAmount.toString())
            RegisterField(title = "Total Price: ", value = register.totalPrice.toString())
            RegisterField(title = "DNI: ", value = register.userDNI)
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Button(
                    modifier = Modifier.size(35.dp),
                    onClick = {
                              deleteRegister(register)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = null,
                        tint = colorResource(id = R.color.black)
                    )
                }
            }


        }

    }
}

@Preview
@Composable
fun PreviewRegisterCard() {
    ProsegurChallengeApplicationTheme {
        Surface (modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            RegisterCard(
                register = RegisterModel(id = 1, roomNumber = 1, day = "Lunes", ticketPrice = 32.50, totalPrice = 65.00, ticketsAmount = 2, userDNI = "87654321")
            ){}
        }
    }
}