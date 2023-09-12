package com.example.prosegurchallengeapplication.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.prosegurchallengeapplication.CoroutinesTestRule
import com.example.prosegurchallengeapplication.day
import com.example.prosegurchallengeapplication.domain.usecase.RegisterUseCase
import com.example.prosegurchallengeapplication.idRoom
import com.example.prosegurchallengeapplication.presentation.viewmodel.RegisterViewModel
import com.example.prosegurchallengeapplication.registerModel
import com.example.prosegurchallengeapplication.registerModelError
import com.example.prosegurchallengeapplication.roomModel
import com.example.prosegurchallengeapplication.roomModelError
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class RegisterViewModelTest {

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    private lateinit var registerViewModel: RegisterViewModel

    private val registerUseCase: RegisterUseCase = mock()

    @Before
    fun setup() {
        registerViewModel = RegisterViewModel(registerUseCase)
    }

    @Test
    fun `register purchase`() = runTest {
        Mockito.`when`(registerUseCase.getRoom(idRoom, day)).thenReturn(roomModel)
        registerViewModel.registerPurchase(registerModel)
        val registerUiState = registerViewModel.uiState.value

        TestCase.assertTrue(registerUiState.onCompleteSave)
    }

    @Test
    fun `register purchase error`() = runTest {
        Mockito.`when`(registerUseCase.getRoom(idRoom, day)).thenReturn(roomModelError)
        registerViewModel.registerPurchase(registerModelError)
        val registerUiState = registerViewModel.uiState.value

        TestCase.assertTrue(registerUiState.onErrorCountRoom)
    }

    @Test
    fun `register error message`() = runTest {
        val errorMessage = "ErrorMessage"
        registerViewModel.registerErrorMessage(errorMessage)
        val registerUiState = registerViewModel.uiState.value
        TestCase.assertTrue(registerUiState.onErrorCountRoom)
        TestCase.assertTrue(registerUiState.errorMessage == errorMessage)
    }

    @Test
    fun `not register error message`() = runTest {
        val errorMessage = ""
        registerViewModel.registerErrorMessage(errorMessage)
        val registerUiState = registerViewModel.uiState.value
        TestCase.assertTrue(!registerUiState.onErrorCountRoom)
        TestCase.assertTrue(registerUiState.errorMessage == errorMessage)
    }

    @Test
    fun `reset values uiState`() = runTest {
        Mockito.`when`(registerUseCase.getRoom(idRoom, day)).thenReturn(roomModel)
        registerViewModel.registerPurchase(registerModel)
        val registerUiState = registerViewModel.uiState.value

        TestCase.assertTrue(registerUiState.onCompleteSave)

        registerViewModel.resetUiState()

        val newRegisterUiState = registerViewModel.uiState.value
        TestCase.assertFalse(newRegisterUiState.onCompleteSave)
    }

}