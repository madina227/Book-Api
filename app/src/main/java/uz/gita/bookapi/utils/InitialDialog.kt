package uz.gita.bookapi.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import uz.gita.bookapi.data.remote.dto.book.request.PostBookRequest
import uz.gita.bookapi.data.remote.dto.book.request.PutBookRequest
import uz.gita.bookapi.databinding.DialogBinding

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 13/12/2022, Tuesday, 15:54
 **/
class InitialDialog(context: Context) : Dialog(context) {
    private val title by lazy { binding.etTitle }
    private val desc by lazy { binding.etDesc }
    private val author by lazy { binding.etAuthor }
    private val pageCount by lazy { binding.etPageCount }
    private var id = -1

    private lateinit var binding: DialogBinding

    private var okListener: ((PostBookRequest) -> Unit)? = null

    fun setOkListener(block: (PostBookRequest) -> Unit) {
        okListener = block
    }

    private var okPutListener: ((PutBookRequest) -> Unit)? = null

    fun setPutOkListener(block: (PutBookRequest) -> Unit) {
        okPutListener = block
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogBinding.inflate(layoutInflater)
        config(binding)


        binding.btnOk.setOnClickListener {
            if (title.text.length >= 3 && desc.text.length >= 30 && author.text.length >= 10 && pageCount.text.isNotEmpty()) {
                if (id != -1) {
                    okPutListener?.invoke(
                        PutBookRequest(
                            id,
                            title.text.toString(),
                            author.text.toString(),
                            desc.text.toString(),
                            pageCount.text.toString().toInt()
                        )
                    )
                    dismiss()
                } else {
                    okListener?.invoke(
                        PostBookRequest(
                            title.text.toString(),
                            author.text.toString(),
                            desc.text.toString(),
                            pageCount.text.toString().toInt()
                        )
                    )
                    dismiss()
                }
            } else Toast.makeText(context, "ma'lumotlar to'liq emas", Toast.LENGTH_SHORT).show()
        }
    }

    override fun dismiss() {
        super.dismiss()
        title.setText("")
        desc.setText("")
        author.setText("")
        pageCount.setText("")
        id = -1

    }

    fun putBook(data: PutBookRequest) {
        id = data.id
        title.setText(data.title)
        desc.setText(data.description)
        author.setText(data.author)
        pageCount.setText(data.pageCount.toString())
    }
}