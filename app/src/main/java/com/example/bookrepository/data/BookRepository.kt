package com.example.bookrepository.data

class BookRepository {
    companion object{
        val listOfBooks = mutableListOf(
            Book("Cuerpo a tierra", "Martin Kohan", "Ficción", "Un soldado de a poco comprende hasta qué punto admira al hombre al que tiene que fusilar."),
            Book("Rayuela", "Julio Cortázar", "Ficción", "Un clásico de la literatura mundial, que narra la historia de Horacio Oliveira y la Maga."),
            Book("El túnel", "Ernesto Sabato", "Ficción", "Un hombre relata en primera persona el asesinato que cometió y la obsesión que lo llevó a ello."),
            Book("Sobre héroes y tumbas", "Ernesto Sabato", "Ficción", "Una compleja trama que aborda temas de política, psicología y filosofía."),
            Book("El Aleph", "Jorge Luis Borges", "Ficción", "Una colección de cuentos que incluye algunos de los relatos más famosos del autor."),
            Book("Fervor de Buenos Aires", "Jorge Luis Borges", "Poesía", "El primer libro de poemas de Borges, que refleja su amor por la ciudad de Buenos Aires."),
            Book("Don Segundo Sombra", "Ricardo Güiraldes", "Ficción", "Una novela que relata la vida de un gaucho y la cultura rural argentina."),
            Book("La invención de Morel", "Adolfo Bioy Casares", "Ficción", "Una novela de ciencia ficción y fantasía que explora temas de realidad y percepción."),
            Book("Martín Fierro", "José Hernández", "Poesía", "Un poema épico que es un hito de la literatura argentina."),
            Book("Respiración artificial", "Ricardo Piglia", "Ficción", "Una novela que explora la historia reciente de Argentina a través de una correspondencia ficticia."),
            Book("Los siete locos", "Roberto Arlt", "Ficción", "Una obra que sigue las peripecias de Erdosain, un hombre común que se ve envuelto en conspiraciones políticas."),
            Book("Zama", "Antonio Di Benedetto", "Ficción", "La historia de un funcionario colonial en Paraguay que espera un traslado que nunca llega."),
            Book("Santa Evita", "Tomás Eloy Martínez", "Ficción", "Una novela que mezcla realidad y ficción sobre la vida y muerte de Eva Perón."),
            Book("El Eternauta", "Héctor Germán Oesterheld", "Cómic", "Una novela gráfica que narra una invasión alienígena en Buenos Aires."),
            Book("El secreto de sus ojos", "Eduardo Sacheri", "Ficción", "Una novela policial que inspiró la película ganadora del Oscar.")
        )

        fun addBook(book: Book) {
            listOfBooks.add(book)
        }
        fun updateBook(updatedBook: Book) {
            val index = listOfBooks.indexOfFirst { it.title == updatedBook.title }
            if (index != -1) {
                listOfBooks[index] = updatedBook
            }
        }

        fun deleteBook(title: String) {
            val index = listOfBooks.indexOfFirst { it.title == title }
            if (index != -1) {
                listOfBooks.removeAt(index)
            }
        }
    }
}