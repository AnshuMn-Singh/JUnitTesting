package mathutils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class MathUtils_Advance_Test {

    @ParameterizedTest
    @ValueSource(strings = {"A", "ad", "adc"})
    void ValueSourceAnnotation(String firstName){
        System.out.println(firstName);
    }

    @DisplayName("Test integer Subtraction [first, second, result]")
    @ParameterizedTest
//    @CsvSource({
//            "33, 1, 32",
//            "24, 4, 20",
//            "1, 1, 0"
//    })
    @CsvFileSource(resources = "/subtractData.csv")
    @MethodSource()
    void subtract(int first, int second, int result) {
        Assertions.assertEquals(result, first - second);
    }
//    public static Stream<Arguments> subtract() {
//        return Stream.of(
//                Arguments.of(33, 1, 32),
//                Arguments.of(1, 1, 0)
//        );
//    }

//    @ParameterizedTest
//    @MethodSource("integerSubtractsInputs")
//    void subtract(int first, int second, int result) {
//        Assertions.assertEquals(result, first - second);
//    }
//    public static Stream<Arguments> integerSubtractsInputs() {
//         return Stream.of(
//                 Arguments.of(33, 1, 32),
//                 Arguments.of(1, 1, 0)
//         );
//    }


    @DisplayName("repeat tests")
    @RepeatedTest(value = 3, name = "{displayName} . Repetition {currentRepetition} of {totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo, TestInfo testInfo){
        System.out.println("Repetition # " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        System.out.println("Running " + testInfo.getDisplayName());

        System.out.println("hello");
    }

}