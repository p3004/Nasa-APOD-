package nasa.photo.oftheday.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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

    @Test
    fun givenServerResponse200_checkIfTextViewsHasExpectedTexts(){

        onView(withId(R.id.tvMainTitleText)).check(matches(withText("M43: Dust, Gas, and Stars in the Orion Nebula")))
        onView(withId(R.id.tvMainDescription)).check(matches(withText("Unspeakable beauty and unimaginable bedlam can be found together in the Orion Nebula Arguably the most famous of all astronomy nebulas, the Great Nebula in Orion is an immense interstellar molecular cloud only 1500 light-years away.  In the featured deep image shown in assigned colors, the part of the nebula's center known as M43 is shown as taken by the Hubble Space Telescope. The Great Nebula in Orion can be found with the unaided eye near the easily identifiable belt of three stars in the popular constellation Orion.  The entire Orion Nebula," +
                " including both M42 and M43 spans about 40 light years and is located in the same spiral arm of our Galaxy as the Sun.")))
        onView(withId(R.id.tvMainDate)).check(matches(withText("2020-07-06")))

    }


    @Test
    fun givenNoInternet_checkIfErrorShowing(){
        onView(withId(R.id.tvMainTitleText)).check(matches(withText("No Internet Connection!")))
    }





}