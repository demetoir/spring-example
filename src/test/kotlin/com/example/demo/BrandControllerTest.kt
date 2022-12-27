package com.example.demo

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName
import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.example.common.base.service.exception.NotFoundEntityException
import com.example.domain.brand.controller.BrandController
import com.example.domain.brand.controller.dto.ReadBrandDto
import com.example.domain.brand.service.BrandService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

// done ascii docs template 설정 해보기
// https://jayhooney.github.io/rest%20docs/Restdocs/
// ! 되기는 하는데 생성된 snippet 을 직접 하는 방식이다
// ! 사실상 테스트 후 추가적인 문서 조립 과정에서 휴먼 에러 발생 가능성이 높다

// done ascii docs 말고 open api spec 으로 변경해보기
// 이러지말고 open api spec 으로 뽑아낼수 있으니까 그걸로 해보자
// ! 스프링 부트 3.0 은 아직 지원안된다.. 2.7 버전으로 내리니 그나마 돌아간다
// https://dezang.net/blog/2021/09/05/spring-rest-docs-oas-02
// https://dezang.net/blog/2021/08/22/spring-rest-docs-oas-01
// https://github.com/OpenAPITools/openapi-generator
// https://helloworld.kurly.com/blog/spring-rest-docs-guide/
// https://lhwn.tistory.com/entry/16-2-Spring-REST-Docs-%EC%8B%A4%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EA%B8%B0
// https://catsbi.oopy.io/edcaed06-6df9-4f19-a6f4-05902ad9878d

// done 에러요청 어디까지 처리되는지 확인해보기
// ! restdocs 는 성공요청만 문서를 만들어준다, 실패요청은 지원을 고려하지 않았다..?
// ! 다만 restdocs api spec 은 실패 요청도 만들어는 주는데 뭔가 이상한다..
// ! https://github.com/ePages-de/restdocs-api-spec/issues/140
// ! 이전 이슈상으로 앞에먼 document identifier 같은거 끼리 붂여서 처리되는식으로 구현됨

// done swagger ui generate 이후 static resource 로 자동으로 복사 되도록 처리하기

// done 공통 코드 처리해보기
// https://github.com/ePages-de/restdocs-api-spec/issues/146#issue-741336062
// ! 일단 되기는 하는데 정식 기능으로 지원되는 방법이 아닌 직접 집어는 넣는 방식이라 좀 불편하다

// todo 속도 개선 버전 적용하기
// https://techblog.woowahan.com/2678/

// todo snippet 으로 생성된 OAS 조각들로 OAS 만드는거라 이전에 삭제 처리 제대로 안되는 이슈가 있다 이거 고치기

// todo H2ServerConfig 랑 자꾸 충돌나서 테스트는것 처리하기 이거 처리해야함

// todo swagger ui 상에서 try out 시 cors 터지는거 해결하기

// todo request query parameter, body 예제 만들어보기

// todo security 쪽도 예제 만들어보기

// todo junit 말고 kotest 형식으로 변경해보기

// todo 생성된 OAS 에서 component shchema에 postfix 숫자 붙는거 땔수있는지 알아보기
// ! osa component 에서 schema 에 postfix 로 hashNumber 붙인으는것으로 추정됨

// todo DSL 적용해보고 간편한 버전으로 만들어보기

// ! todo JpaMetamodel 빈관련 터지는것 처리하기
@MockBean(JpaMetamodelMappingContext::class)
@WebMvcTest(BrandController::class)
@AutoConfigureRestDocs
class BrandControllerTest {
    @MockBean
    private lateinit var service: BrandService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `get one brand 200`() {
        val dto = ReadBrandDto(
            id = 1L,
            name = "name",
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )
        given(service.findById(1L)).willReturn(dto)

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/brand/{id}", dto.id))
            .andExpect(status().isOk)
            .andDo(
                document(
                    identifier = "brand-get-one/200",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("get brand by id")
                            .summary("shit")
                            .pathParameters(
                                parameterWithName("id").description("brand id")
                            )
                            .responseFields(
                                fieldWithPath("timestamp").type(JsonFieldType.STRING).description("타임스탬프"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("결과메시지"),

                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("data"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("createdAt"),
                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("modifiedAt"),
                            )
                            .build()
                    )
                )
            )
    }

    @Test
    fun `get one brand 404`() {
        val dto = ReadBrandDto(
            id = 2L,
            name = "name",
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )
        given(service.findById(2L)).willThrow(NotFoundEntityException("brand"))

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/brand/{id}", dto.id))
            .andExpect(status().isNotFound)
            .andDo(
                document(
                    identifier = "brand-get-one/404",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("get brand by id but not found")
                            .summary("ssefsefse")
                            .pathParameters(
                                parameterWithName("id").description("brand id")
                            )
                            .responseFields(
                                fieldWithPath("timestamp").type(JsonFieldType.STRING).description("타임스탬프"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("결과메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL).description("data")
                            )
                            .build()
                    )
                )
            )
    }

    @Test
    fun `get many api-v1-brand 200 ok document`() {
        val dummyBrand = ReadBrandDto(
            name = "dummy",
            id = 1L,
            createdAt = LocalDateTime.parse("2021-01-01T00:00:00"),
            modifiedAt = LocalDateTime.parse("2021-01-01T00:00:00"),
        )

        val response = listOf(dummyBrand)

        given(service.findAll()).willReturn(response)

        // When
        val result = mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/brand", 2))

        // Then
        result.andExpect(status().isOk()).andDo(
            document(
                identifier = "brand-get-all",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .description("get brand by id but not found")
                        .summary("ssefsefse")
                        .responseFields(
                            fieldWithPath("timestamp").type(JsonFieldType.STRING).description("타임스탬프"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과코드"),
                            fieldWithPath("status").type(JsonFieldType.NUMBER).description("결과메시지"),
                            fieldWithPath("data").type(JsonFieldType.ARRAY).description("데이터"),
                            fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("id"),
                            fieldWithPath("data[].name").type(JsonFieldType.STRING).description("name"),
                            fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("createAt"),
                            fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("modifiedAt"),
                        )
                        .build()
                )

            )
        )
    }
}
