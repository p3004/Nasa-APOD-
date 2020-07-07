package nasa.photo.oftheday.di.component

import dagger.Component
import nasa.photo.oftheday.di.FragmentScope
import nasa.photo.oftheday.di.module.FragmentModule
import nasa.photo.oftheday.ui.description.DescriptionFragment

/**
 * Created by Pallab Banerjee on 7/2/2020.
 */

@FragmentScope
@Component(dependencies = [ApplicationComponent::class],
           modules = [FragmentModule::class]
    )
interface FragmentComponent {


        fun inject(fragment: DescriptionFragment)

}