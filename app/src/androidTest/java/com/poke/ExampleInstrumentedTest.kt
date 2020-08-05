package com.poke

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.poke.ui.main.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.poke", appContext.packageName)
    }

    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.et_input)).perform(
            ViewActions.typeText("pikachu"),
            ViewActions.closeSoftKeyboard()
        )

        SystemClock.sleep(1500)


//        onData(anything())
//            .inAdapterView(withId(R.id.rv_list))
//            .atPosition(0)
//            .onChildView(withId(R.id.tv_name))
//            .atPosition(0)
//            .onChildView(withId(R.id.tv_name)).perform(click())

//        val recyclerView: ViewInteraction  = onView(
//                allOf(withId(R.id.rv_list),
//                    childAtPosition(
//                        withClassName("android.support.constraint.ConstraintLayout"),
//                    0)))
//        recyclerView.perform(actionOnItemAtPosition(0, click()))
    }




}

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {

                view?.let {
                    val parent: ViewParent = view.getParent()
                    return (parent is ViewGroup && parentMatcher.matches(parent)
                            && view.equals(parent.getChildAt(position)))
                } ?: return false

            }

        }
    }

}