package com.example.searchcases;

import java.util.List;

public class FindInRegistr {

    private static final String NUMBER_IN_BASE = "numberInBase";
    private static final String LAST_NAME = "lastName";

    public void FindNumberInBase(int idInBase) {

        String attributName = NUMBER_IN_BASE;
        FullSearch(idInBase);
    }

    // поиск по всем описям
    public void FullSearch(int idInBase) {

        String fileName;
        PensionInfo person = null;
        List<PensionInfo> resultList = null;
        List<String> namesList = null;
        int yearsIndex;

        //TODO: реализовать загрузка имени файла из базы имен файлов для поиска ID
        fileName = "export_2014.xml";
        SearchInBase(idInBase,fileName);
    }

    private void SearchInBase(int value, String namesBaseFile) {
        //TODO: Проверить существует ли файл fileName
        // парсим xml на наличие ID
    }

}
