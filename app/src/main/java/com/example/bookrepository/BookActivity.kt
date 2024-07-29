package com.example.bookrepository

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookrepository.ViewModel.BookViewModel
import com.example.bookrepository.data.Book
import com.example.bookrepository.data.BookRepository
import com.example.bookrepository.databinding.BookItemBinding
import org.w3c.dom.Text

class BookActivity : AppCompatActivity() {

    private lateinit var book: Book
    private lateinit var tvTitle: TextView
    private lateinit var tvWriter: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvSynopsis: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button
    private lateinit var editView: LinearLayout
    private lateinit var etEditTitle: EditText
    private lateinit var etEditWriter: EditText
    private lateinit var etEditGenre: EditText
    private lateinit var etEditSynopsis: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDiscard: Button
    private lateinit var bookViewModel: BookViewModel
    private lateinit var binding: BookItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        tvTitle = findViewById(R.id.tv_detailTitle)
        tvWriter = findViewById(R.id.tv_detailAuthor)
        tvGenre = findViewById(R.id.tv_detailGenre)
        tvSynopsis = findViewById(R.id.tv_detailSynopsis)
        btnEdit = findViewById(R.id.btn_edit)
        btnDelete = findViewById(R.id.btn_delete)
        editView = findViewById(R.id.editView)
        etEditTitle = findViewById(R.id.et_editTitle)
        etEditWriter = findViewById(R.id.et_editAuthor)
        etEditGenre = findViewById(R.id.et_editGenre)
        etEditSynopsis = findViewById(R.id.et_editSynopsis)
        btnSave = findViewById(R.id.btn_save)
        btnDiscard = findViewById(R.id.btn_discard)

        binding = BookItemBinding.inflate(layoutInflater)
        bookViewModel = BookViewModel()

        book = intent.getSerializableExtra("book") as? Book ?: return
        setBookValues()

        btnEdit.setOnClickListener {
            modoEdicion(true)
        }
        btnSave.setOnClickListener {
            confirmDialog()
        }
        btnDelete.setOnClickListener {
            deleteDialog()
        }
        btnDiscard.setOnClickListener{
            modoEdicion(false)
        }

    }

    private fun deleteDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("¿Estás seguro de que quieres eliminar este libro?")
            .setPositiveButton("Si", DialogInterface.OnClickListener{dialog, id ->
                book?.let {
                    bookViewModel.deleteBook(it)
                    finish()
                }
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()
            })
        builder.create().show()
    }

    private fun confirmDialog() {
        val changes = mutableListOf<String>()
        if (etEditTitle.text.toString() != book.title) {
            changes.add("el título \"${book.title}\" por \"${etEditTitle.text}\"")
        }
        if (etEditWriter.text.toString() != book.writer) {
            changes.add("el autor \"${book.writer}\" por \"${etEditWriter.text}\"")
        }
        if (etEditGenre.text.toString() != book.genre) {
            changes.add("El género \"${book.genre}\" por \"${etEditGenre.text}\"")
        }
        if (etEditSynopsis.text.toString() != book.synopsis) {
            changes.add("La sinopsis \"${book.synopsis}\" por \"${etEditSynopsis.text}\"")
        }

        if (changes.isEmpty()) {
            guardarLibroEditado()
            modoEdicion(false)
            return
        }

        val message = "Has modificado " + changes.joinToString("\n") + "\n¿Deseas guardar los cambios?"

        // Show confirmation dialog
        val dialog = AlertDialog.Builder(this)
            .setTitle("Confirmación")
            .setMessage(message)
            .setPositiveButton("Guardar") { _, _ ->
                guardarLibroEditado()
                modoEdicion(false)
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }

    private fun guardarLibroEditado() {
        val updatedBook = book.copy(
            title = etEditTitle.text.toString(),
            writer = etEditWriter.text.toString(),
            genre = etEditGenre.text.toString(),
            synopsis = etEditSynopsis.text.toString()
        )
        BookRepository.updateBook(updatedBook)
        book = updatedBook
        setBookValues()
    }

    private fun modoEdicion(modoEdicion: Boolean) {
        if(modoEdicion){
            tvTitle.visibility = TextView.GONE
            tvWriter.visibility = TextView.GONE
            tvGenre.visibility = TextView.GONE
            tvSynopsis.visibility = TextView.GONE
            btnEdit.visibility = Button.GONE
            btnDelete.visibility = Button.GONE

            editView.visibility = LinearLayout.VISIBLE
            etEditTitle.setText(book.title)
            etEditWriter.setText(book.writer)
            etEditGenre.setText(book.genre)
            etEditSynopsis.setText(book.synopsis)
        }else{
            tvTitle.visibility = TextView.VISIBLE
            tvWriter.visibility = TextView.VISIBLE
            tvGenre.visibility = TextView.VISIBLE
            tvSynopsis.visibility = TextView.VISIBLE
            btnEdit.visibility = Button.VISIBLE
            btnDelete.visibility = Button.VISIBLE

            editView.visibility = LinearLayout.GONE
        }
    }

    private fun setBookValues() {
        tvTitle.text = book.title
        tvWriter.text = book.writer
        tvGenre.text = book.genre
        tvSynopsis.text = book.synopsis
    }

}