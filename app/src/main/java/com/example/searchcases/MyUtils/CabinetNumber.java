package com.example.searchcases.MyUtils;

import com.example.searchcases.PensionInfo;

import java.util.List;

public class CabinetNumber {
    public static int getCabinetNumber(PensionInfo person) {
        int numberRegistry = Integer.parseInt(person.getNumberRegistry());

        int cabinetNumber = 0;

        if (checkCabinet510(numberRegistry)) {
            cabinetNumber = 510;
        }
        if (checkCabinet510(numberRegistry)) {
            cabinetNumber = 510;
        }
        if (checkCabinet521(numberRegistry)) {
            cabinetNumber = 521;
        }
        if (checkCabinet524(numberRegistry)) {
            cabinetNumber = 524;
        }
        if (checkCabinet525(numberRegistry)) {
            cabinetNumber = 525;
        }
        return cabinetNumber;
    }

    private static boolean cabinetIsCorrect(int numberRegistry, int cabinetMinNumber, int cabinetMaxNumber) {
        return (numberRegistry >= cabinetMinNumber) && (numberRegistry <= cabinetMaxNumber);
    }

    private static boolean checkCabinet510(int numberRegistry) {
        int cabinetMinNumber = 16282;
        int cabinetMaxNumber = 23994;
        return cabinetIsCorrect(numberRegistry, cabinetMinNumber, cabinetMaxNumber);
    }

    private static boolean checkCabinet521(int numberRegistry) {
        int cabinetMinNumber = 10275;
        int cabinetMaxNumber = 16281;
        return cabinetIsCorrect(numberRegistry, cabinetMinNumber, cabinetMaxNumber);
    }

    private static boolean checkCabinet524(int numberRegistry) {
        // TODO: работает только со второй половиной 2005 года, добавить первую
        int cabinetMinNumber = 5288;
        int cabinetMaxNumber = 10274;
        return cabinetIsCorrect(numberRegistry, cabinetMinNumber, cabinetMaxNumber);
    }

    private static boolean checkCabinet525(int numberRegistry) {
        int cabinetMinNumber = 23995;
        int cabinetMaxNumber = 37110;
        return cabinetIsCorrect(numberRegistry, cabinetMinNumber, cabinetMaxNumber);
    }
}
