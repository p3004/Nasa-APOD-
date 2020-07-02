package nasa.photo.oftheday.di.component

import dagger.Component
import dagger.Subcomponent
import nasa.photo.oftheday.di.ActivityScope
import nasa.photo.oftheday.di.module.ActivityModule
import nasa.photo.oftheday.ui.main.MainActivity

/**
 * Created by Pallab Banerjee on 7/2/2020.
 */

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
            modules = [ActivityModule::class]
    )
interface ActivityComponent {

            fun inject(activity: MainActivity)

}