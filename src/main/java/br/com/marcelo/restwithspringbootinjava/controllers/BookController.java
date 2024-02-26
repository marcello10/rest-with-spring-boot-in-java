package br.com.marcelo.restwithspringbootinjava.controllers;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.BookVO;
import br.com.marcelo.restwithspringbootinjava.services.BookServices;
import br.com.marcelo.restwithspringbootinjava.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book/v1")
@Tag(name="Books",
        description = "Endpoints for Mapping Books")
public class BookController {

    @Autowired
    private BookServices services;

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Book", description = "Finds a book by id",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "No Content",
                            responseCode = "204",
                            content = @Content),
                    @ApiResponse(description = "Bad Request",
                            responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized",
                            responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Internal Error",
                            responseCode = "500",
                            content = @Content),
            })
    public BookVO findById(@PathVariable(value = "id") Long id) throws Exception {
        return services.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all Books", description = "Finds all Books",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array=@ArraySchema(
                                            schema = @Schema(implementation = BookVO.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request",
                            responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized",
                            responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found",
                            responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error",
                            responseCode = "500",
                            content = @Content),
            })
    public List<BookVO> findAll() {
        return services.findByAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Add a new Book", description = "Create a new Book using JSON, YAML and XML",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request",
                            responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized",
                            responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found",
                            responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error",
                            responseCode = "500",
                            content = @Content),
            })
    public BookVO create(@RequestBody BookVO bookVO) throws Exception {
        return services.create(bookVO);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Update a Book", description = "Update a Book using a id by XML,JSON or YAML",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "No Content",
                            responseCode = "204",
                            content = @Content),
                    @ApiResponse(description = "Bad Request",
                            responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized",
                            responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found",
                            responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error",
                            responseCode = "500",
                            content = @Content),
            })
    public BookVO update(@RequestBody BookVO bookVO) throws Exception {
        return services.update(bookVO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Book", description = "Delete a Book using a id by XML,JSON or YAML",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Deleted",
                            responseCode = "204",
                            content = @Content),
                    @ApiResponse(description = "Bad Request",
                            responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized",
                            responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found",
                            responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error",
                            responseCode = "500",
                            content = @Content),
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}
