package test_framework;

import test_framework.annotations.AfterEach;
import test_framework.annotations.BeforeEach;
import test_framework.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestClassMethodsContainer {

    private Class<?> testClass;
    private List<Method> beforeEachMethods = new ArrayList<>();
    private List<Method> afterEachMethods = new ArrayList<>();
    private List<Method> tests = new ArrayList<>();
    private int totalTestsCount = 0;

    public TestClassMethodsContainer(Class<?> testClass) {
        this.testClass = testClass;
    }

    public void init() {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.getDeclaredAnnotation(BeforeEach.class) != null) {
                beforeEachMethods.add(method);
            } else if (method.getDeclaredAnnotation(AfterEach.class) != null) {
                afterEachMethods.add(method);
            } else if (method.getDeclaredAnnotation(Test.class) != null) {
                tests.add(method);
                totalTestsCount++;
            }
        }
    }

    public List<Method> getBeforeEachMethods() {
        return beforeEachMethods;
    }

    public List<Method> getAfterEachMethods() {
        return afterEachMethods;
    }

    public List<Method> getTests() {
        return tests;
    }

    public int getTotalTestsCount() {
        return totalTestsCount;
    }
}
