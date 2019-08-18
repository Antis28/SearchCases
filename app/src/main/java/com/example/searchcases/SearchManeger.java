package com.example.searchcases;

public class SearchManeger {
   private PensionInfo person;
   private FindInRegistr finder;

    public SearchManeger(){
        person = new PensionInfo();
        finder = new FindInRegistr();
    }

    public void SearchExecute(int idInBase){
        // TODO: сделать проверку является ли число шестизначным
        finder.FindNumberInBase(idInBase);
    }
}
