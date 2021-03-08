package com.spring.spqr.resolver;

import com.spring.spqr.model.request.BookRequestDto;
import com.spring.spqr.model.response.BookResponseDto;
import com.spring.spqr.service.BookService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.relay.generic.PageFactory;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@GraphQLApi
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@RequiredArgsConstructor
public class BookResolver {

    final BookService bookService;

    @GraphQLQuery(name="getAllBook")
    public io.leangen.graphql.execution.relay.Page<BookResponseDto> getAllBook(@GraphQLArgument(name="first") int first, @GraphQLArgument(name="after") String after) {
        int offset = Integer.valueOf(after);
        Page<BookResponseDto> page = bookService.findAll(PageRequest.of(offset,first));
        return PageFactory.createOffsetBasedPage(page.getContent(), page.getTotalElements(), offset);
    }

    @GraphQLQuery(name="getBook")
    public BookResponseDto getBook(@GraphQLArgument(name="bookId") String id) {
        return bookService.getById(id);
    }

    @GraphQLMutation(name="createBook")
    public BookResponseDto createBook(@GraphQLArgument(name="details") BookRequestDto book) {
        return bookService.save(book);
    }


    @GraphQLMutation(name="updateBook")
    public BookResponseDto updateBook(@GraphQLArgument(name="bookId") String id,@GraphQLArgument(name="details") BookRequestDto book) {
        return bookService.update(id,book);
    }

    @GraphQLMutation(name="deleteBook")
    public Boolean deleteBook(@GraphQLArgument(name="bookId") String id) {
        bookService.deleteById(id);
        return true;
    }
}
