package com.example.aux

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class AuxController() {
    @RequestMapping("")
    fun index(): AuxIndexResponse {
        return AuxIndexResponse()
    }
}

class AuxIndexResponse {
    var openApiSpec = "/swagger-ui/index.html"
}
