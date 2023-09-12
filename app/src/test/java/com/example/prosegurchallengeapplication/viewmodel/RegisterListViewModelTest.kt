package com.example.prosegurchallengeapplication.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.prosegurchallengeapplication.CoroutinesTestRule
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.domain.usecase.RegisterListUseCase
import com.example.prosegurchallengeapplication.presentation.viewmodel.RegisterListViewModel
import com.example.prosegurchallengeapplication.registerModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class RegisterListViewModelTest {

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    private lateinit var registerListViewModel: RegisterListViewModel

    private val registerListUseCase: RegisterListUseCase = mock()

    @Before
    fun setup() {
        registerListViewModel = RegisterListViewModel(registerListUseCase)
    }

    @Test
    fun `get all purchase`() = runTest {
        val registerList = listOf(registerModel)
        Mockito.`when`(registerListUseCase.getAllPurchase()).thenReturn(registerList)
        registerListViewModel.getAllPurchase()
        val registerListUiState = registerListViewModel.uiState.value

        assertEquals(registerListUiState.registerList, registerList)
    }

    @Test
    fun `delete register`() = runTest {
        val registerList = listOf(registerModel)
        Mockito.`when`(registerListUseCase.getAllPurchase()).thenReturn(registerList)
        registerListViewModel.deleteRegister(registerModel)
        verify(registerListUseCase, times(1)).deleteRegister(registerModel)
    }




}