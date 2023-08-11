package com.android.picsumphotos.photolist.viewmodel

import com.android.domain.usecase.GetPictureListUseCase
import com.android.picsumphotos.util.MainDispatcherRule
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = MainDispatcherRule()

    @Mock
    lateinit var mockUseCase: GetPictureListUseCase

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {}

    @Test
    fun test_Toggle_Dark_Mode() = runTest {
        viewModel = MainViewModel(mockUseCase)
        Truth.assertThat(viewModel.darkMode.value).isEqualTo(false)
        viewModel.toggleDarkMode()
        Truth.assertThat(viewModel.darkMode.value).isEqualTo(true)
    }

}