package com.spring.spqr.bootstrap;


import com.spring.spqr.domain.Book;
import com.spring.spqr.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Log4j2
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class BookLoader implements CommandLineRunner {

    final BookRepository bookRepository;

    @Override
    @Transactional
    public void run(String... args){
        if(bookRepository.count() == 0) {
            loadBookObjects();
        }

    }

    private void loadBookObjects()  {

        Book book = Book.builder()
                .title("Elasticsearch Basics")
                .author("Rambabu Posa")
                .releaseDate("23-FEB-2017")
                .build();

        bookRepository.save(book);

        book = Book.builder()
                .title("Apache Lucene Basics")
                .author("Rambabu Posa")
                .releaseDate("13-MAR-2017")
                .build();

        bookRepository.save(book);

        book = Book.builder()
                .title("Apache Solr Basics")
                .author("Rambabu Posa")
                .releaseDate("21-MAR-2017")
                .build();

        bookRepository.save(book);

        log.info("Books inserted");

    }
}
