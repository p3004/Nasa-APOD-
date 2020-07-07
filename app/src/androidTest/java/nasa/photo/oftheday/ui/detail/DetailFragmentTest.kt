package nasa.photo.oftheday.ui.detail

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnitRunner
import kotlinx.android.synthetic.main.fragment_description.view.*
import nasa.photo.oftheday.R
import nasa.photo.oftheday.di.component.ApplicationTestComponent
import nasa.photo.oftheday.rule.ApplicationTestRule
import nasa.photo.oftheday.ui.MainSharedViewModel
import nasa.photo.oftheday.ui.description.DescriptionFragment
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Created by Pallab Banerjee on 7/7/2020.
 */

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    private val appComponent =
        ApplicationTestRule(InstrumentationRegistry.getInstrumentation().targetContext)


    @get : Rule
    val chain: RuleChain = RuleChain.outerRule(appComponent)


    @Test
    fun checkIfViewsAreDisplayed(){

        launchFragmentInContainer<DescriptionFragment>()
        onView(withId(R.id.tvDescTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescDate)).check(matches(isDisplayed()))
    }



    @Test
    fun givenSeverResponse200_checkIfViewsAreDisplayingCorrectData() {

        launchFragmentInContainer<DescriptionFragment>()
        onView(withId(R.id.tvDescTitle)).check(matches(withText("M43: Dust, Gas, and Stars in the Orion Nebula")))
        onView(withId(R.id.tvDescDescription)).check(
            matches(
                withText(
                    "Unspeakable beauty and unimaginable bedlam can be found together in the Orion Nebula Arguably the most famous of all astronomy nebulas, the Great Nebula in Orion is an immense interstellar molecular cloud only 1500 light-years away.  In the featured deep image shown in assigned colors, the part of the nebula's center known as M43 is shown as taken by the Hubble Space Telescope. The Great Nebula in Orion can be found with the unaided eye near the easily identifiable belt of three stars in the popular constellation Orion.  The entire Orion Nebula," +
                            " including both M42 and M43 spans about 40 light years and is located in the same spiral arm of our Galaxy as the Sun."
                )
            )
        )
        onView(withId(R.id.tvDescDate)).check(matches(withText("2020-07-06")))

    }

}