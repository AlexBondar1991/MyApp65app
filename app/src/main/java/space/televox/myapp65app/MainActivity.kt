package space.televox.myapp65app

import android.os.Bundle
import androidx.fragment.app.*
import space.televox.myapp65app.data.Worker

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<SpecialityListFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }
    }

    fun openWorkersList(specialityId: Long) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, WorkersListFragment.newInstance(specialityId))
            addToBackStack(null)
        }
    }

    fun openDetailedInfo(worker: Worker) {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_container_view,
                DetailedWorkerInfoFragment.newInstance(worker.workerId)
            )
            addToBackStack(null)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}