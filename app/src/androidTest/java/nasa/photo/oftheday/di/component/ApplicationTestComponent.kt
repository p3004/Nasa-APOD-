package nasa.photo.oftheday.di.component

import dagger.Component
import nasa.photo.oftheday.di.module.ApplicationModuleForTest
import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 7/6/2020.
 */

@Singleton
@Component(modules = [ApplicationModuleForTest::class])
interface ApplicationTestComponent : ApplicationComponent {
}