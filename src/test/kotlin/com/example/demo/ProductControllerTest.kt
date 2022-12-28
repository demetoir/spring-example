package com.example.demo

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.example.domain.brand.controller.BrandController
import com.example.domain.brand.controller.dto.ReadBrandDto
import com.example.domain.product.controller.ProductController
import com.example.domain.product.controller.dto.ReadProductDto
import com.example.domain.product.entity.constant.ProductStatus
import com.example.domain.product.service.ProductService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.snippet.Attributes.key
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

// ! todo move this config to outside
@MockBean(JpaMetamodelMappingContext::class)
@WebMvcTest(ProductController::class)
@AutoConfigureRestDocs
class ProductControllerTest {
    @MockBean
    private lateinit var service: ProductService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `get many product 200`() {
        ChangeSetPersister.NotFoundException
        BrandController

        val dto = ReadProductDto(
            id = 1L,
            name = "name",
            createdAt = LocalDateTime.now(),
            brand = ReadBrandDto(
                id = 1L,
                name = "name",
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now()
            ),
            price = 1000,
            modifiedAt = LocalDateTime.now(),
            status = ProductStatus.ACTIVE
        )
        given(service.findAll()).willReturn(listOf(dto))

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/product"))
            .andExpect(status().isOk)
            .andDo(
                document(
                    identifier = "product-get-many/200",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("get many product ")
                            .summary("shit")
                            .responseFields(
                                fieldWithPath("timestamp").type(JsonFieldType.STRING).description("타임스탬프"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("결과메시지"),

                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("data"),
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("data[].price").type(JsonFieldType.NUMBER).description("price"),
                                fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("createdAt"),
                                fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("modifiedAt"),

                                // * enum type 사용할 수있는 몇 안되는 방법
                                fieldWithPath("data[].status").type("ENUM").description("status").attributes(
                                    key("enumValues").value(ProductStatus.values().map { it.name })
                                ),
                                fieldWithPath("data[].brand.id").type(JsonFieldType.NUMBER).description("brand id"),
                                fieldWithPath("data[].brand.name").type(JsonFieldType.STRING).description("brand name"),
                                fieldWithPath("data[].brand.createdAt").type(JsonFieldType.STRING)
                                    .description("brand createdAt"),
                                fieldWithPath("data[].brand.modifiedAt").type(JsonFieldType.STRING)
                                    .description("brand modifiedAt"),

                            )
                            .build()
                    )
                )
            )
    }
}
