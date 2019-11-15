package fr.lucas.barcodescanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import retrofit2.Call
import retrofit2.mock.Calls
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val foodFacts = FoodFacts("23454345", Product("ZERTRER"))

    private val service = object: FoodFactsService {
        override fun listFoodFacts(barcode: String): Call<FoodFacts> {
            return Calls.response(foodFacts)
        }
    }


    private val serviceFailure = object: FoodFactsService {
        override fun listFoodFacts(barcode: String): Call<FoodFacts> {
            return Calls.failure(IOException())
        }
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `find existing product should yield success state`() {
        val viewModel = MainViewModel(service)
        val state = viewModel.getState()

        viewModel.findProduct("23456434565434")

        Assert.assertEquals(
            MainViewModelState.Success(foodFacts),
            state.value
        )
    }

    @Test
    fun `find missing product should yield failure state`() {
        val viewModel = MainViewModel(serviceFailure)
        val state = viewModel.getState()

        viewModel.findProduct("23456434565434")

        Assert.assertEquals(
            MainViewModelState.Failure("error"),
            state.value
        )
    }
}
