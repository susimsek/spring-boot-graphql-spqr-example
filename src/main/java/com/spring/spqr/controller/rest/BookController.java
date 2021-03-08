package com.spring.spqr.controller.rest;


import com.spring.spqr.model.request.BookRequestDto;
import com.spring.spqr.model.response.BookResponseDto;
import com.spring.spqr.resolver.BookResolver;
import com.spring.spqr.util.GraphqlUtil;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.spring.spqr.util.GraphqlUtil.GRAPHQL_MUTATION;
import static com.spring.spqr.util.GraphqlUtil.GRAPHQL_QUERY;

@Tag(name = "books", description = "Retrieve and manage books")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
@RequestMapping("/versions/1")
public class BookController implements ApplicationContextAware {

    GraphQL graphQL;

    @PageableAsQueryParam
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok",content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Get all Books")
    @GetMapping(value = "/books" )
    @ResponseStatus(HttpStatus.OK)
    public Object listBooks(@RequestParam(required = false) String query, Pageable page) throws Throwable {
        query = (query!=null) ? query : "{ id, title, author, releaseDate}";
        String qraphqlMethod = "getAllBook";
        String graphlQueryText = GRAPHQL_QUERY + "{" + qraphqlMethod + "(after:\"" + page.getPageNumber() + "\",first:" + page.getPageSize() + ")" + "{ edges { node " + query + " cursor } pageInfo { hasNextPage, hasPreviousPage, startCursor, endCursor } } }";

        ExecutionResult result = graphQL.execute(graphlQueryText);
        return GraphqlUtil.getData(result).get(qraphqlMethod);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",content = @Content(schema = @Schema(implementation = BookResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Create new Book")
    @PostMapping(value = "/books" )
    @ResponseStatus(HttpStatus.CREATED)
    public Object createBook(@Valid @RequestBody BookRequestDto bookRequestDto,@RequestParam(required = false) String query) throws Throwable {
        query = (query!=null) ? query : "{ id, title, author, releaseDate}";
        String qraphqlMethod = "createBook";
        String graphlQueryText = GRAPHQL_MUTATION + "{" + qraphqlMethod+ "(details: {title: \""+bookRequestDto.getTitle()+"\",author: \""+bookRequestDto.getAuthor()+"\", releaseDate: \""+bookRequestDto.getReleaseDate()+"\"}" + ")" + query + "}";
        ExecutionResult result = graphQL.execute(graphlQueryText);
        return GraphqlUtil.getData(result).get(qraphqlMethod);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok",content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Update existing Book")
    @PutMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Object updateBook(@PathVariable String bookId, @Valid @RequestBody BookRequestDto bookRequestDto,@RequestParam(required = false) String query) throws Throwable {
        query = (query!=null) ? query : "{ id, title, author, releaseDate}";
        String qraphqlMethod = "updateBook";
        String graphlQueryText = GRAPHQL_MUTATION + "{" + qraphqlMethod+ "(bookId: \""+bookId+"\",details: {title: \""+bookRequestDto.getTitle()+"\",author: \""+bookRequestDto.getAuthor()+"\", releaseDate: \""+bookRequestDto.getReleaseDate()+"\"}" + ")" + query + "}";
        ExecutionResult result = graphQL.execute(graphlQueryText);
        return GraphqlUtil.getData(result).get(qraphqlMethod);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok",content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Get existing Book")
    @GetMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Object getBook(@PathVariable String bookId,@RequestParam(required = false) String query) throws Throwable {
        query = (query!=null) ? query : "{ id, title, author, releaseDate}";
        String qraphqlMethod = "getBook";
        String graphlQueryText = GRAPHQL_QUERY + "{" + qraphqlMethod + "(bookId: \""+bookId+"\"" + ")" + query + "}";
        ExecutionResult result = graphQL.execute(graphlQueryText);
        return GraphqlUtil.getData(result).get(qraphqlMethod);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content",content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Delete existing Book")
    @DeleteMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String bookId) throws Throwable {
        String qraphqlMethod = "deleteBook";
        String graphlQueryText = GRAPHQL_MUTATION + "{" + qraphqlMethod + "(bookId: \""+bookId+"\"" + ")" + "}";
        ExecutionResult result = graphQL.execute(graphlQueryText);
        GraphqlUtil.getData(result);
    }

    @Override
    public void setApplicationContext(final @NonNull ApplicationContext applicationContext) throws BeansException {
        BookResolver bookResolver = applicationContext.getBean(BookResolver.class);
        GraphQLSchema schema = new GraphQLSchemaGenerator().withResolverBuilders(
                // Resolve by annotations
                new AnnotatedResolverBuilder(),
                // Resolve public methods inside root package
                new PublicResolverBuilder("com.graphql.bookPoc"))
                .withOperationsFromSingleton(bookResolver, BookResolver.class)
                .withValueMapperFactory(new JacksonValueMapperFactory()).generate();
        graphQL = GraphQL.newGraphQL(schema).build();

    }
}
