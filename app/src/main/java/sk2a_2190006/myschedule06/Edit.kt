package sk2a_2190006.myschedule06

import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
//import kotlinx.android.synthetic.main.activity_edit.*


class Edit : AppCompatActivity() {

    private var mDate: Long = 0
    private var mPrefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val intent = intent
        if(intent != null) {
            if (intent.hasExtra(Defines.KEY_DATE)) {
                mDate = intent.getLongExtra(Defines.KEY_DATE, 0)
            }
        }

        val date = Defines.sFmt.format(mDate)
        val textView1 = findViewById<TextView>(R.id.textView1)
        textView1.text = date

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val content = mPrefs!!.getString(date, "")
        val txtcontent: EditText = findViewById(R.id.editText1)
        txtcontent.setText(content)

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener{
            val dlg = AlertDialog.Builder(this)

            dlg.setTitle(getString(R.string.SaveTitle))//保存確認

            dlg.setMessage(R.string.DialogMsg1)

            dlg.setPositiveButton(getString(R.string.Yes), DialogInterface.OnClickListener { _, _ ->
                val toast = Toast.makeText(applicationContext, R.string.YesMsg, Toast.LENGTH_SHORT)
                toast.show()

                val editText1 = findViewById<EditText>(R.id.editText1)
                val content = editText1.text.toString()
                val editor = mPrefs!!.edit()
                editor.putString(date, content)

                editor.commit()

            })
            dlg.setNegativeButton(getString(R.string.No), DialogInterface.OnClickListener { _, _ ->
                val toast = Toast.makeText(applicationContext, R.string.NoMsg, Toast.LENGTH_LONG)
                toast.show()
            })
            dlg.show()
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener{
            val dlg = AlertDialog.Builder(this)

            dlg.setTitle(getString(R.string.FinishTitle))

            dlg.setMessage(R.string.DialogMsg2)

            dlg.setPositiveButton(getString(R.string.Yes)) { _, _ ->
                val toast = Toast.makeText(applicationContext, R.string.YesMsg, Toast.LENGTH_SHORT)
                toast.show()
                finish()
            }
            dlg.setNegativeButton(getString(R.string.No)) { _, _ ->
                val toast = Toast.makeText(applicationContext, R.string.NoMsg, Toast.LENGTH_LONG)
                toast.show()
            }
            dlg.show()
        }
    }
}