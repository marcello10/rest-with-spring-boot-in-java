package br.com.marcelo.restwithspringbootinjava.controllers;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.PersonVO;
import br.com.marcelo.restwithspringbootinjava.services.PersonServices;
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
@RequestMapping("/person/v1")
@Tag(name="People",
        description = "Endpoints for Mapping People")
public class PersonController {

    @Autowired
    private PersonServices services;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Person", description = "Finds a person by id",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
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
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
        return services.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People", description = "Finds all People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array=@ArraySchema(
                                            schema = @Schema(implementation = PersonVO.class))
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
    public List<PersonVO> findAll() {
        return services.findByAll();
    }

    @CrossOrigin(origins = {"http://localhost:8080","https://erudio.com.br"})
    @PostMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Add a new Person", description = "Create a new person using JSON, YAML and XML",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
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
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        return services.create(person);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Update a Person", description = "Update a Person using a id by XML,JSON or YAML",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
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
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Person", description = "Delete a Person using a id by XML,JSON or YAML",
            tags = {"People"},
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
