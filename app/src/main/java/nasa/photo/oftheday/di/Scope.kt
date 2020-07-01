package nasa.photo.oftheday.di

import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by Pallab Banerjee on 7/1/2020.
*/

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityScope


@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentScope