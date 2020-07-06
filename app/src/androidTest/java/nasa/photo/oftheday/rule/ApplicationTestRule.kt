package nasa.photo.oftheday.rule

import android.content.Context
import nasa.photo.oftheday.NasaApplication
import nasa.photo.oftheday.di.component.ApplicationTestComponent
import nasa.photo.oftheday.di.component.DaggerActivityComponent
import nasa.photo.oftheday.di.component.DaggerApplicationTestComponent
import nasa.photo.oftheday.di.module.ApplicationModuleForTest
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by Pallab Banerjee on 7/6/2020.
 */

class ApplicationTestRule(private val context: Context) : TestRule {

    var applicationTestComponent : ApplicationTestComponent? = null

    fun getContext() = context


    fun setUpApplicationDaggerForTest(){
        val application = context.applicationContext as NasaApplication
        applicationTestComponent = DaggerApplicationTestComponent.builder()
            .applicationModuleForTest(ApplicationModuleForTest(application))
            .build()
        application.setApplicationComponentForTest(applicationTestComponent!!)

    }

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement(){
            override fun evaluate() {
                    try {
                        setUpApplicationDaggerForTest()
                        base?.evaluate()
                    }finally {
                        applicationTestComponent = null
                    }


                   }


        }


    }



}