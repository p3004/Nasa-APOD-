package nasa.photo.oftheday.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import nasa.photo.oftheday.R
import nasa.photo.oftheday.di.component.ApplicationTestComponent
import nasa.photo.oftheday.rule.ApplicationTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Created by Pallab Banerjee on 7/5/2020.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val appComponent = ApplicationTestRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val mainScenario  = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @get : Rule
    val chain: RuleChain = RuleChain.outerRule(appComponent).around(mainScenario)


    @Test
    fun checkIfViewsDisplayedOrNot(){
        onView(withId(R.id.tvMainTitleText)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMainDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMainDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.ivMainApodBackgroundImage)).check(matches(isDisplayed()))
        onView(withId(R.id.ivMainDatePicker)).check(matches(isDisplayed()))
        
    }


}