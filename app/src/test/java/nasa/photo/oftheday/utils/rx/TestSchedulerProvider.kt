package nasa.photo.oftheday.utils.rx

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.TestScheduler

/**
 * Created by Pallab Banerjee on 7/3/2020.
 */

/**
 *  Scheduler provider for testing
* */
class TestSchedulerProvider(private val testScheduler: TestScheduler) : SchedulerProvider {

    override fun io(): Scheduler = testScheduler

    override fun computation(): Scheduler = testScheduler

    override fun ui(): Scheduler = testScheduler
}