package io.roadmaps.core.swagger;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameters({
        @Parameter(in = ParameterIn.QUERY
                , description = "Page you want to retrieve (0..N)"
                , name = "page"
                , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
        @Parameter(in = ParameterIn.QUERY
                , description = "Number of records per page."
                , name = "size"
                , content = @Content(schema = @Schema(type = "integer", defaultValue = "20")))
})
public @interface ApiPageable {

}
