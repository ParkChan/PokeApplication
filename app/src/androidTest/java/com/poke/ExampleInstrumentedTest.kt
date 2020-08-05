package com.poke

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
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
    var mainActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.poke", appContext.packageName)
    }

    @Test
    fun runApp() {
        mainActivityRule.launchActivity(Intent())
        testCase("pikachu")
        onView(withId(R.id.et_input)).perform(ViewActions.clearText())
        mainActivityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        testCase("Bulbasaur")
        mainActivityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun testCase(pokemonName: String) {

        Thread.sleep(1500)      //TODO : 보완이 필요한 코드

        onView(withId(R.id.et_input)).perform(
            ViewActions.typeText(pokemonName),
            ViewActions.closeSoftKeyboard()
        )

        Thread.sleep(1500)

        onView(withId(R.id.rv_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        Thread.sleep(1500)

        onView(withId(R.id.ib_map))
            .perform(click())

        Thread.sleep(3000)
        pressBack()

        onView(withId(R.id.btn_confirm))
            .perform(click())
    }
}