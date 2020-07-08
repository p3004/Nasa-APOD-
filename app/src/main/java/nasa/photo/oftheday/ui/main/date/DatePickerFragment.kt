package nasa.photo.oftheday.ui.main.date

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import nasa.photo.oftheday.ui.main.MainActivity
import java.util.*

/**
 * Created by Pallab Banerjee on 7/7/2020.
 */
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        const val TAG = "Date"
    }

    internal lateinit var dateSetProvider: DateSetProvider

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
          dateSetProvider =  context as DateSetProvider
        }catch (e : ClassCastException){
            throw java.lang.ClassCastException((context.toString()) + "must implement DateSetProvider")
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(requireActivity(), this, year, month, day)
        dialog.datePicker.maxDate = System.currentTimeMillis()
        return dialog
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val date  = "$year-$month-$day"
        dateSetProvider.getDate(date)
    }

    interface DateSetProvider{
        fun getDate(date : String)

    }

}