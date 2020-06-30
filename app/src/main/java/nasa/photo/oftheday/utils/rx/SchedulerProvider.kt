package nasa.photo.oftheday.utils.rx

import io.reactivex.rxjava3.core.Scheduler

/**
 * Created by Pallab Banerjee on 6/30/2020.
 */
interface SchedulerProvider {

    fun io() : Scheduler

    fun computation() : Scheduler

    fun ui() : Scheduler

}