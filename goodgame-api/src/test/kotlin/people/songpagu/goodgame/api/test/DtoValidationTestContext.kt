package people.songpagu.goodgame.api.test

import org.assertj.core.api.Assertions
import java.util.stream.Collectors
import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

open class DtoValidationTestContext {
    private var factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
    private var validator: Validator = factory.validator

    protected fun <T> getErrorMessagesFromDto(t: T): List<String> {
        return validator.validate(t).stream()
            .map { obj: ConstraintViolation<T> -> obj.message }
            .collect(Collectors.toList())
    }

    protected fun <T> assertValidDto(t: T) {
        val errorMessages = getErrorMessagesFromDto(t)
        Assertions.assertThat(errorMessages).isEmpty()
    }
}