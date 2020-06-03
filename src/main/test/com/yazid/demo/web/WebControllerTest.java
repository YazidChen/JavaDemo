package com.yazid.demo.web;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.JRE.JAVA_14;
import static org.junit.jupiter.api.condition.JRE.JAVA_8;
import static org.junit.jupiter.params.provider.Arguments.arguments;


/**
 * @author Yazid
 * @date 2020/6/2 10:03
 */
@DisplayName("The Web Test!")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WebControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(WebControllerTest.class);

    @BeforeEach
    @DisplayName("BeforeEach")
    void setUp(TestInfo testInfo) {
        LOG.info("Before Method {} start!", testInfo.getDisplayName());
    }

    @AfterEach
    @DisplayName("AfterEach")
    void tearDown(TestInfo testInfo) {
        LOG.info("After Method {} finish!", testInfo.getDisplayName());
    }

    @Disabled("Disabled until bug #12 fixed!")
    @Test
    void ip() {
    }

    @Test
    @EnabledForJreRange(min = JAVA_8, max = JAVA_14)
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "dev")
    @Order(1)
    void standardAssertions() {
        assertEquals(5, 1 + 4);
        assertEquals(5, 1 + 4, "AssertEquals Failure Message!");
        assertTrue(1 > 0, () -> "AssertTrue Failure Message! " + "Support lambda!");
        assertNotNull(1);
    }

    @Test
    @Order(2)
    void groupedAssertions() {
        assertAll("The group check!",
                () -> assertEquals(6, 1 + 5),
                () -> assertEquals(6, 2 + 4));
    }

    @Test
    void throwAssertions() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            throw new NullPointerException();
        });
    }

    @Test
    void timeoutAssertions() {
        //超过2s算超时，报错
        assertTimeout(Duration.ofSeconds(2), () -> Thread.sleep(1000));
    }

    @RepeatedTest(5)
    void repeatedTest() {
        int a = RandomUtil.randomInt();
        LOG.info("repeatedTest->Param:[{}]", a);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"apple", "bank", "bird", "red", "haha", " ", "   ", "\t", "\n"})
    void parameterizedTest(String param) {
        if (StrUtil.isBlank(param)) {
            LOG.error("Error!->Param:[param:{}]", param);
            return;
        }
        LOG.info("Method start->Param:[param:{}]", param);
    }

    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertNotEquals(9, argument);
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >= 1 && num <= 2);
        assertEquals(2, list.size());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("apple", 1, Arrays.asList("a", "b")),
                arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "apple,         1",
            "banana,        2",
            "'lemon, lime', 0xF1"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest
    @ValueSource(strings = {"01.01.2020", "31.12.2020"})
    void testWithExplicitJavaTimeConverter(
            @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {

        assertEquals(2020, argument.getYear());
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void failsIfExecutionTimeExceeds100Milliseconds() {
        LOG.info("Method failsIfExecutionTimeExceeds100Milliseconds start");
        assertEquals(3, 0 + 1 + 2);
    }
}