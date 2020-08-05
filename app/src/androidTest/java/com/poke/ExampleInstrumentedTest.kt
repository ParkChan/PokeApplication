package com.poke

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.poke.ui.main.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.poke", appContext.packageName)
    }

    @Test
    fun runApp() {
        activityRule.launchActivity(Intent())
        testCase("pikachu")
        testCase("Bulbasaur")
    }

    private fun testCase(pokemonName: String){

        onView(withId(R.id.et_input)).perform(
            ViewActions.typeText(pokemonName),
            ViewActions.closeSoftKeyboard()
        )

        Thread.sleep(1500)          //TODO : 개선필요

        onView(withId(R.id.rv_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.ib_map))
            .perform(click())

        Thread.sleep(3000)          //TODO : 개선필요

        activityRule.finishActivity()

        onView(withId(R.id.btn_confirm))
            .perform(click())
    }
}