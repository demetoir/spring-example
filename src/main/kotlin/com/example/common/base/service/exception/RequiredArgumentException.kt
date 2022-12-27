package com.example.common.base.service.exception

import com.example.common.advice.exception.BadRequestException

class RequiredArgumentException(argName: String) : BadRequestException("$argName 값은 필수 값 입니다.")
